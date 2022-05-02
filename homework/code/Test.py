import os
import filecmp
import difflib

from util import *
from Assignment import *

from output_handle import *


class Test:
    def __init__(self, assignment, name):
        self.assignment = assignment
        self.name = name
        self.directory = assignment.directory

        self._initFileNames()
        self._initConfigurableProperties()
        self._setCommandLine()

        self._isJunit = any((arg.upper().find("JUNIT") >= 0) for arg in self.Args)

        for f in self.OutputFiles:
            if not os.path.isfile(os.path.join(self.directory, f)):
                raise AssertionError("File " + f + " not found in assignment directory")

    def _initFileNames(self):
        self._inputBaseName = "input" + self.name
        self._correctOutputBaseName = "output" + self.name
        self._correctErrorBaseName = "error" + self.name
        self._inputFileName = os.path.join(self.directory, self._inputBaseName)
        self._correctOutputFileName = os.path.join(self.directory, self._correctOutputBaseName)
        self._correctErrorFileName = os.path.join(self.directory, self._correctErrorBaseName)
        self._scriptFileName = os.path.join(self.directory, "run" + self.name)
        if (not os.path.isfile(self._correctOutputFileName)):
            self._correctOutputFileName = ""
        if (not os.path.isfile(self._correctErrorFileName)):
            self._correctErrorFileName = ""
        if (not os.path.isfile(self._scriptFileName)):
            self._scriptFileName = ""
        self._outputFileName = "myOutput" + self.name + ".txt"
        self._errorFileName = "myError" + self.name + ".txt"
        tests_obj[self.name] = TestObject(name=self.name, input_file=self._inputFileName, output_file=self._outputFileName, error_file=self._errorFileName)

    def _initConfigurableProperties(self):
        self.Args = []
        self.Descr = []
        self.GradeForPass = 0
        self.GradeForFail = 0
        self.OutputFiles = []
        self.TimeOut = self.assignment.TimeOut
        propertyFileName = os.path.join(self.directory, self.name + ".properties")
        if os.path.isfile(propertyFileName):
            loadProperties(self, propertyFileName)

    def _setCommandLine(self):
        if self._scriptFileName != "":
            if len(self.Args) > 0:
                raise AssertionError("Test: ", self.name, "Arguments and script file cannot coexist")
            else:
                self.commandLine = ["bash", "-c", self._scriptFileName]
        else:
            if len(self.assignment.commandLine) == 0:
                raise AssertionError("This type of assignment requires a script")
            else:
                self.commandLine = self.assignment.commandLine + self.Args

    @staticmethod
    def isTestFile(baseName):
        return baseName.startswith("input") or baseName.startswith("output") or baseName.startswith("error") or \
               (baseName.endswith(".properties") and not baseName.startswith("log"))

    def runTest(self):
        """Test prog by supplying it inputFileName as input,
        and checking whether the standardOutput and standard error match those supplied by the test."""
        
        print("Running Test:", self.name)
        tests_obj[self.name].log += "Running Test:" + self.name + "\n"
        for line in self.Descr:
            print(line)
            tests_obj[self.name].log += line + "\n"


        os.symlink(self._inputFileName, self._inputBaseName)
        if self._correctOutputFileName != "":
            os.symlink(self._correctOutputFileName, self._correctOutputBaseName)
        if self._correctErrorFileName != "":
            os.symlink(self._correctErrorFileName, self._correctErrorBaseName)
        self.removeOutputFiles()

        with open(self._inputFileName, "r") as inputFile:
            with open(self._outputFileName, "w") as outputFile:
                with open(self._errorFileName, "w") as errorFile:
            # self.commandLine = ['./prog']
            # inputFile = <_io.TextIOWrapper name='/var/www/html/hoorys/homework/36/Hw1-1/inputInvalid1' mode='r' encoding='UTF-8'>
            # outputFile = <_io.TextIOWrapper name='myOutputInvalid1.txt' mode='w' encoding='UTF-8'>
            # errorFile = <_io.TextIOWrapper name='myErrorInvalid1.txt' mode='w' encoding='UTF-8'>
                    # tests_obj[self.name] = TestObject(input_file=inputFile, output_file=outputFile, error_file=errorFile)
                    runCommand(self.commandLine, stdin=inputFile, stdout=outputFile,  stderr=errorFile, timeOut=self.TimeOut)
        if self._isJunit:
            tests, failures = self._junitResults(self._outputFileName)
            if os.path.getsize(self._errorFileName) == 0:
                os.remove(self._errorFileName)
        else:
            tests = 1
            failures = 0
            if not self._isSameOrNotExistent(self._errorFileName, self._correctErrorFileName):
                failures = 1
            if not self._isSameOrNotExistent(self._outputFileName, self._correctOutputFileName):
                failures = 1
            for f in self.OutputFiles:
                if not self._isOutputFileCorrect(f):
                    failures = 1
        self.tests = tests # We need this for subsequent calls to max grade
        return tests, failures, self._gradeFor(tests, failures)

    def removeOutputFiles(self):
        for f in self.OutputFiles:
            if os.path.isfile(f):
                os.remove(f)

    def _isOutputFileCorrect(self, outputFile):
        if not os.path.isfile(outputFile):
            print ("File", outputFile, "not found")
            tests_obj[self.name].error += "File", outputFile, "not found" + "\n"
            return False
        else:
            correctFileName = os.path.join(self.directory, outputFile)
            return self._isSame(outputFile, correctFileName)

    def _isSameOrNotExistent(self, fileName, correctFileName):
        if correctFileName == "":
            if os.path.getsize(fileName) > 0:
                print("Please check contents of the file ", fileName, " manually.")
                tests_obj[self.name].error += "Please check contents of the file ", fileName, " manually." + "\n"
            else:
                os.remove(fileName)
            return True
        return self._isSame(fileName, correctFileName)


    def remove_whitespace(self,_list):
        delimiter = '\n'
        result1 = list(filter(lambda val: val != delimiter, _list))
        return [arg.strip() for arg in result1]

    def compare_files(self,fpath1, fpath2):
        with open(fpath1, 'r') as file1, open(fpath2, 'r') as file2:
            file1_list = file1.readlines()
            file2_list = file2.readlines()
            # TODO : add flag to config hw file
            ignore_whitespace = True
            if ignore_whitespace:
                file1_list = self.remove_whitespace(file1_list)
                file2_list = self.remove_whitespace(file2_list)

            for linef1, linef2 in zip(file1_list, file2_list):
                if linef1 != linef2:
                    return False
            return next(file1, None) == None and next(file2, None) == None



    def _isSame(self, fileName, correctFileName):
        fileSize = os.path.getsize(fileName)
        expectedSize = os.path.getsize(correctFileName)
        sizeRatio = fileSize/(expectedSize if expectedSize != 0 else 1)
        if fileSize > 100000 and sizeRatio > 2:
            printErr("file", fileName, "too big, skipping comparison. Please compare against", correctFileName, " manually");
            tests_obj[fileName].error += "file " + fileName+ " too big, skipping comparison. Please compare against "+ correctFileName+ " manually\n"
            return False
        filecmp.clear_cache()
        if self.compare_files(fileName, correctFileName):
            return True

        diffFileName = "diff." + fileName
        ret = True
        with open(diffFileName, "w") as diffFile:
            diff = difflib.Differ()
            print ("Comparing", fileName, "against", correctFileName)
            tests_obj[self.name].diff_file = diffFileName
            tests_obj[self.name].log += "Comparing "+ fileName+ " against " + correctFileName + '\n'
            for diffLine in diff.compare(fileContents(fileName), fileContents(correctFileName)):
                if diffLine[0] != ' ':
                    diffFile.write(diffLine)
                    ret = False
        if not ret:
            printErr("Please inspect the difference file", diffFileName)
            tests_obj[self.name].error +="Please inspect the difference file "+ diffFileName+ "\n"
        return ret


    def _junitResults(self, fileName):
        for line in reversed(fileContents(fileName)):
            if line.strip() != "":
                print(line)
                if line.startswith("OK"):
                    i = line.find("(")
                    line = line[i+1:]
                    tests = int(line.split(" ")[0])
                    return (tests, 0)
                elif line.startswith("Tests"):
                    words=line.split()
                    tests = int(words[2][:-1])
                    failures = int(words[4])
                    return(tests, failures)
                else:
                    printErr("Cannot parse test results")
                    return (100, 100)

    def _gradeFor(self, tests, failures):
        # print("tests", tests)
        # print("failures", failures)
        assert tests != 0
        try:
            gradeForPasses = (tests - failures) * (self.GradeForPass / tests);
        except TypeError:
            gradeForPasses = eval(self.GradeForPass)            
        try:
            gradeForFails = failures * (self.GradeForFail / tests)
        except TypeError:
            gradeForFails = eval(self.GradeForFail)
        grade = gradeForPasses + gradeForFails
        return round(grade,2)

    @property
    def MaxGrade(self):
        t = self.tests
        return max(self._gradeFor(t, t), self._gradeFor(t, 0))