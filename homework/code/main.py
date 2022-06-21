from distutils.log import WARN
import os.path
import argparse
import re
import zipfile
import datetime
import shutil

from util import *
from data import *
from Assignment import *
from submission import *

# =======================================================
# from collections import defaultdict
# import json
# output = defaultdict(list)

from output_handle import *


def parseFileName (name):
    """Verify that: the file exists, it is a zip file , and its name matches one of the allowed formats,
    i.e. the student submission format, or the moodle format.
    Return a triple (courseId, hwName, ids), where ids is the list of IDs of the students
     or empty if it is a moddle file """
    if not os.path.isfile(name):
        raise FileNotFoundError(name)
    if not zipfile.is_zipfile(name):
        raise AssertionError("File", name, "not in zip format")
    baseName = os.path.basename(name)
    studentPattern = re.compile("([0-9]+)_([^_]+)((_[0-9A-Z]*)+).zip")
    checkerPattern = re.compile("([0-9]+)_([^_]+)_all.zip")
    studentMatch = studentPattern.fullmatch(baseName)
    checkerMatch = checkerPattern.fullmatch(baseName)
    if studentMatch:
        courseId = eval(studentMatch.group(1))
        hwName = studentMatch.group(2).upper()
        ids = re.split("_",studentMatch.group(3))[1:]
        [checkId(id) for id in ids]
        if (len(set(ids)) != len(ids)):
            raise ValueError("Duplicate student id")
        return courseId, hwName, ids
    elif checkerMatch:
        courseId = eval(checkerMatch.group(1))
        hwName = checkerMatch.group(2).upper()
        return courseId, hwName, []
    else:
        raise NameError("Wrong name: " + name + ", should be courseId_HwId_StudentId1_StudentId2...")

def parseDirName (name):
    """Return a triple (courseId, hwName, ids), where ids is the list of IDs of the students"""
    baseName = os.path.basename(name)
    pattern = re.compile("([0-9]+)_([^_]+)((_[0-9A-Z]*)+)")
    match = pattern.fullmatch(baseName)
    if match:
        courseId = eval(match.group(1))
        hwName = match.group(2).upper()
        ids = re.split("_",match.group(3))[1:]
        return courseId, hwName, ids
    else:
        raise NameError("Wrong name: " + name + ", should be courseId_HwId_StudentId1_StudentId2...")


def getCourse(id):
    """Check whether id is the course Id of an active course"""
    try:
        course = Course.byId[id]
        if course.active:
            print(course)
            print(type(course))
            output[HEADER].append(str(course))
            return course
    except KeyError:
        pass
    printErr("Course not found or not active: ", id)
    printErr("")
    printErr("Available Courses")
    compile_obj.error += "Course not found or not active: " + str(id) + "\n"
    compile_obj.error += "Available Courses" + "\n"
    for course in Course.byId.values():
        printErr(course)
        compile_obj.error += str(course) + "\n"

    return None

def getAssignment(course, assignmentName):
    """Check whether assignmentName is the short name of an assignment of course"""
    l = [assignment for assignment in course.getAssignments() if assignment.shortName.upper() == assignmentName.upper()]
    if len(l) != 1:
        printErr("Assignment not found: ", assignmentName)
        printErr()
        printErr("List of Assignments")
        compile_obj.error += "Assignment not found: " + assignmentName + "\n"
        compile_obj.error += "List of Assignments" + "\n"
        for assignment in course.getAssignments():
            printErr(assignment)
            compile_obj.error += assignment + "\n"

        return None
    else:
        assignment = l[0]
        print(assignment)
        output[HEADER].append(str(assignment))
        if (assignment.deadline < datetime.datetime.today()):
            printErr ("Warning: Deadline is due !!")
            output[WARNING].append( "Warning: Deadline is due !!" )
        assignment.loadProperties()
        assignment.loadTests()
        return assignment

def studentMain(assignment, fileName, ids):
    """Main function for a student submission:
       Create a temporary directory.
       Extract the zip file fileName into the temporary directory.
       let assignment check the structure of the directory, compile and test its contents"""
    dirName = fileName.split(".")[0]
    if os.path.exists(dirName):
        printErr("Directory", dirName, "exists. Aborting..")
        printErr("Please CLEAR RESULTS DIRECTORY for a second check")
        compile_obj.error += "Directory " + dirName + " exists. Aborting.." + "\n"
        compile_obj.error += "Please CLEAR RESULTS DIRECTORY for a second check" + "\n"

        return False
    try:
        extractZipFile(fileName, dirName)
        submission = Submission(assignment, dirName, ids)
        return submission.checkCompileTest()
    except Exception as err:
        errStr = "bad submission file:" + fileName + err.__str__()
        printErr(errStr)
        output[EXCEPTION].append(errStr)

def handleSubmissionFile (file, assignment, afterExtract):
    try:
        ids=[] ### to cover the case where the next line fails
        (courseId, hwName, ids) = parseFileName(file)
        if not (courseId == assignment.course.id and hwName == assignment.shortName.upper()):
            assignment.logUnextractable("bad submission file " + file + ": wrong course id or homework name", ids)
            return
        dirName = file[:-4]
        extractZipFile(file, dirName)
    except Exception as err:
        assignment.logUnextractable("bad submission file:" + file + err.__str__(), ids)
        return
    finally:
        os.remove(file)

    if afterExtract:
        handleSubmissionDirectory(dirName, assignment)


def handleSubmissionDirectory (dirName, assignment):
    """DirName should be an existing and valid directory name"""
    try:
        ids=[] ### to cover the case where the next line fails
        (courseId, hwName, ids) = parseDirName(dirName)
        submission = Submission(assignment, dirName, ids)
    except Exception as err:
        assignment.logUnextractable("bad submission dir:" + dirName + err.__str__(), ids)
        return
    submission.checkCompileTest()
    submission.writeResults()
    submission.writeGrades()

def renameSubmissionFile(file):
    pattern = re.compile(".*assignsubmission_file_(.*)")
    match = pattern.fullmatch(file)
    if not match:
        errStr = "Bad moodle file name:" + file + " ignored."
        printErr(errStr)
        output[EXCEPTION].append(errStr)
        os.remove(file)
    else:
        try:
            newName = match.group(1)
            os.rename(file, newName)
        except Exception as e:
            os.remove(file)
            printErr(e)
            printErr("Ignoring duplicate Submission File:", newName)
            output[EXCEPTION].append(e + ", Ignoring duplicate Submission File:" + newName) 

def extractFromSubmissionDirectory(dirName):
    for root, dirs, files in os.walk(dirName):
        if len(dirs) > 0 or len(files) != 1:
            errStr = "Moodle directory should contain exactly one file:" + dirName
            printErr(errStr)
            output[EXCEPTION].append(errStr) 
            return
        try:
            shutil.move(os.path.join(dirName, files[0]), ".")
        except Exception as e:
            os.remove(os.path.join(dirName, files[0]))
            printErr(e)
            printErr("Ignoring duplicate Submission File:", files[0])
            output[EXCEPTION].append(e + ", Ignoring duplicate Submission File:" + files[0]) 
        os.rmdir(dirName)

def extractFromMoodle(fileName):
    """Check that the current directory contains no subdirectories.
    Extract the zip file fileName into the subdirectory 'submissions'.
    Check that the name of every file in 'submissions" is a valid moodle submission
    (this should be always OK, unless something changed in Moodle).
    Remove every file that does not fulfill this requirement.
    Otherwise:
        Rename the file to remove all Moodle prefixes and remain with our format,
        extract its contents to a subdirectory of 'submissions'"""
    for root, dirs, files in os.walk("."):
        if len(dirs) > 0:
            printErr("Make the checking in an empty folder (without subdirectories), you have:")
            printErr(dirs)
            output[EXCEPTION].append("Make the checking in an empty folder (without subdirectories), you have: " + dirs)
            return False
    extractZipFile(fileName, "submissions")
    os.chdir("submissions")
    for dirEntry in os.listdir("."):
        if os.path.isfile(dirEntry):
            renameSubmissionFile(dirEntry)
        elif os.path.isdir(dirEntry):
            extractFromSubmissionDirectory(dirEntry)
    os.chdir("..")


def homeworkCheckerMain(assignment, fileName, options):
    """Main function for a moodle file"""

    if options["extractMoodle"]:
        extractFromMoodle(fileName)

    if options["extractStudent"] or options["afterExtract"]:
        assignment.createResultFiles()
        os.chdir("submissions")
        for dirEntry in os.listdir("."):
            if os.path.isfile(dirEntry):
                assert options["extractStudent"]
                handleSubmissionFile(dirEntry, assignment, options["afterExtract"])
            else:
                assert options["afterExtract"]
                handleSubmissionDirectory(dirEntry, assignment)

    if options["afterExtract"]:
        assignment.checkPlagiarism()

def getCommandLineArguments():
    parser = argparse.ArgumentParser(description='Homework File Checker')
    parser.add_argument('fileName', help='Submission File Name')
    group=parser.add_mutually_exclusive_group()
    group.add_argument('--extractMoodle', action="store_true", help='Extract individual submission files into submissions directory')
    group.add_argument('--extractStudent', action="store_true", help='Extract contents of individual submission files')
    group.add_argument('--noextract', action="store_true", help='Compile and Test')
    return parser.parse_args()

def getOptions(args):
    options = {}
    options["extractMoodle"] = not (args.extractStudent or args.noextract)
    options["extractStudent"] = not (args.extractMoodle or args.noextract)
    options["afterExtract"] = not (args.extractMoodle or args.extractStudent)
    return options;

fn = ""
def main():
    """Parse the arguments. It should contain exactly one argument which is a file name.
    Parse the file name and get the triple (courseId, hwName, ids).
    Load the instructor, course and assignments data.
    Check the course, assignment and student ids.
    Check the file as a student file ot Moodle file depending on whether the list of ids is empty."""

    # global output
    # rc = main()
    # f_output = open("output.json" , "w")
    # output["aaa"].append("31321321")
    # json.dump(output , f_output, indent=4)

    args = getCommandLineArguments()
    options = getOptions(args)
    try:
        (courseId, hwName, ids) = parseFileName(args.fileName)
    except Exception as e:
        printErr(e)
        output[EXCEPTION].append(e)
        return False

    #to remove
    global fn 
    fn = args.fileName
    #########
    
    datadir = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "data")
    Instructor.loadFromCSV(os.path.join(datadir, "instructors.csv"))
    Course.loadFromCSV(os.path.join(datadir, "courses.csv"))
    course = getCourse(courseId)
    if not course:
        return False
    assignment = getAssignment(course, hwName)
    if not assignment:
        return False

    if len(ids) > 0: #student submission file
        return studentMain(assignment, args.fileName, ids)
    else:            #moodle file with all student submission files
        return homeworkCheckerMain(assignment, args.fileName, options)



# def main():
#     global output
#     rc = main()
#     f_output = open("output.json" , "w")
#     output["aaa"].append("31321321")
#     json.dump(output , f_output, indent=4)
import json
if __name__ == "__main__":
    rc = main()
    print("PATH IS " + fn)
    resultsPath = os.path.splitext(fn)[0] + "/TestResults.json"
    export_running_output_to_file(resultsPath)
    # f_output = open("output.json" , "w")
    # json.dump(output , f_output, indent=4)
    exit(rc)
