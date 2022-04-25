
import os
import os.path
import shutil
import distutils.dir_util

from util import *
from Assignment import *
from Test import *
 # ===========================================================================
# from main import output
from output_handle import *

class Submission:
    def __init__(self, assignment, dir, ids):
        if len(ids) < assignment.minStudents:
            raise ValueError("Too few students: " + str(assignment.minStudents) + " needed")
        if len(ids) > assignment.maxStudents:
            raise ValueError("Too many students, at most " + str(assignment.maxStudents) + " accepted")
        self._assignment = assignment
        self._parentDir = os.getcwd()
        self._dir = os.path.abspath(dir)
        self.ids = ids;
        self.structureResult = ""
        self.compilationResult = ""
        self.compilationError=False
        self.compilationWarning=False
        self.testResults = {}
        self.grade = 0
        self.testGrade = 0

    def cd(self):
        os.chdir(self._dir)

    def checkCompileTest(self):
        """Recall the current directoy and change to directory dir.
        Check directory structure, compile, link, and test.
        Proceed to the next step only if the previous one succeeds.
        In any case return to the current directory """
        prevdir = os.getcwd()
        try:
            self.checkStructure()
            self.copyTestFiles()
            self.compile()
            if not self.compilationError:
                self.test()
            self.deleteTestFiles()
            self.calcGrade()
        finally:
            os.chdir(prevdir)
        return

    def calcGrade(self):
        assignment = self._assignment
        if self.compilationError:
            self.grade = assignment.GradeForExtracting
        else:
            self.grade = assignment.GradeForCompilation
        if self.compilationWarning:
            self.grade -= assignment.PenaltyForWarnings
        if self.structureResult != "":
            self.grade -= assignment.PenaltyForStructure
        if assignment.DisplayGrades:
            print ("Grade for correct format and compilation:", self.grade)
            output[SUMMARY].append("Grade for correct format and compilation:"+ str(self.grade))
        self.grade += self.testGrade
        if assignment.DisplayGrades:
            print ("OVERALL GRADE:", self.grade)
            print ("This grade DOES NOT ACCURATELY REFLECT the merit of the work which is affected by ADDITIONAL FACTORS such as:")
            print ("code design, algorithmic efficiency, adequacy of solution, code style, plagiarism, etc...")
            output[SUMMARY].append ("OVERALL GRADE:"+ str(self.grade))
            output[SUMMARY].append ("This grade DOES NOT ACCURATELY REFLECT the merit of the work which is affected by ADDITIONAL FACTORS such as:")
            output[SUMMARY].append ("code design, algorithmic efficiency, adequacy of solution, code style, plagiarism, etc...")
            
    def checkStructure(self):
        """Structure is OK only if every file is of an allowed type and subdirectories exist
        only if they are allowed"""
        print("Checking content of ", self._dir)
        output[HEADER].append("Checking content of "+ self._dir)
        self.cd()
        self.structureResult = ""

        hiddenDirs, hiddenFiles = removeHidden()
        badDirs = [] if self._assignment.subdirectoriesAllowed else removeSubdirectories()

        """Remove files with bad extensions"""
        """If WindowsOS then rename h files to lower case and edit c, cpp and h files"""
        badFiles=[]
        for root, dirs, files in os.walk(".", topdown=True):
            for name in files:
                extension = name.rpartition(".")[-1]
                if not extension in self._assignment.allowedExtensions and \
                   not name in self._assignment.AllowedFileNames:
                    badFiles.append(name)
                elif self._assignment.WindowsOS and extension.lower() in ["c", "cpp", "h"]:
                    fullName = os.path.join(root, name)
                    editUserIncludes(fullName)
                    if extension.lower() == "h":
                        newFullName = os.path.join(root, name.lower())
                        os.rename(fullName, newFullName)

        if len(hiddenDirs) > 0:
            self.structureResult += "Ignored hidden directories: " + str(hiddenDirs)
        if len(hiddenFiles) > 0:
            self.structureResult += "Ignored hidden files: " + str(hiddenFiles)
        if len(badDirs) > 0:
            self.structureResult += "Ignored subdirectories: " + str(badDirs) + " (subdirectories not allowed)"
        if len(badFiles) > 0:
            self.structureResult += "Ignored files: " + str(badFiles) + " (bad extension)"
        if self.structureResult != "":
            printErr(self.structureResult)
            output[WARNING].append(self.structureResult)
        return

    def copyTestFiles(self):
        self.cd()
        assignmentDir = self._assignment.directory
        for dirEntry in os.listdir(assignmentDir):
            source = os.path.join(assignmentDir, dirEntry)
            dest = os.path.join(".", dirEntry)
            if os.path.isfile(source) and not Test.isTestFile(dirEntry):
                 shutil.copy(source, dest)
            if os.path.isdir(source):
                distutils.dir_util.copy_tree(source, dest)
        return

    def deleteTestFiles(self):
        self.cd()
        assignmentDir = self._assignment.directory
        for (root, dirs, files) in os.walk(assignmentDir, topdown=False, followlinks=True):
            delDir = os.path.join(".", os.path.relpath(root, assignmentDir))
            for file in files:
                if delDir != os.path.join(".", "."):
                    toDelete = os.path.join(delDir, file)
                    try:
                        os.remove(toDelete)
                    except:  # Some files do not have copies in the submission directory
                        pass
            for dir in dirs:
                toDelete = os.path.join(delDir, dir)
                try:
                    os.rmdir(toDelete)
                except: # A directory may contain files created by the test
                    pass
        return

    def compile(self):
        print("Compiling...")
        output[COMPILE].append("Compiling...")
        self.cd()
        self.compilationResult, self.compilationError, self.compilationWarning = self._assignment.compile()

    def test(self):
        print("Testing...")
        output[COMPILE].append("Testing...")
        self.cd()
        self.testResults, self.testGrade = self._assignment.runTests()

    def writeResults(self):
        row = [self._dir, self.structureResult, self.compilationResult]
        for key in sorted(self.testResults):
            row += [self.testResults[key]]
        self._assignment.resultsWriter.writerow(row);

    def writeGrades(self):
        for id in self.ids:
            row = [id, self.grade, self.structureResult + self.compilationResult + str(self.testResults)]
            self._assignment.gradesWriter.writerow(row);