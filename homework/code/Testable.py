import os
import sys

from util import *
from Assignment import *
from Test import *

class Testable (Assignment):
    """The class of programs that can be tested with inputs in the test directory."""

    def __init__ (self, course, csvRow):
        super(Testable, self).__init__(course, csvRow)

    def runTests(self):
        """Scan tests directory for files whose name start with the 'input'.
        Return true if and only if all passed."""
        from main import output
        results = {}
        totalGrade=0
        for name in sorted(self.tests.keys()):
            test = self.tests[name]
            # print("name = " ,name)
            # name --> excpected output file
            # test --> Test object
            try:
                (numOfTests, failures, grade) = test.runTest()
            except Exception as e:
                printErr(e)
                (numOfTests, failures, grade) = (1,1,0)
            if failures == 0:
                result = "Passed " + str(numOfTests) + " test(s)"
            else:
                result = "Failed " + str(failures) + " out of " + str(numOfTests) + " test(s)"
            results[test.name] = result
            tests_obj[test.name].result = result
            totalGrade += grade
            print(result)
            if self.DisplayGrades:
                print("Grade for this test: ", grade, "/", test.MaxGrade)
                tests_obj[test.name].grade = "Grade for this test: " + str(grade) +  "/" + str(test.MaxGrade) + "\n"
            print(" ")
            print(" ")
            sys.stdout.flush()
            sys.stderr.flush()

        return results, totalGrade

    def compile(self):
        error = False
        warning = False
        for file in [dirEntry for dirEntry in os.listdir(".") if os.path.isfile(dirEntry)]:
            if file.rpartition(".")[-1] in self.compilable:
                errorFileName = file+".errors.txt"
                compile_obj.error_file = errorFileName
                print ("Compiling " + file)
                compile_obj.log += "Compiling " + file + "\n"
                with open(os.devnull, 'w') as nullFile:
                    with open(errorFileName,'w') as errFile:
                        if subprocess.call(self.compileCommand + " " + file, shell=True, stderr=errFile) != 0 :
                            error = True
                        elif subprocess.call(self.compileWarningsCommand + " " + file, shell=True, stderr=errFile) !=0 :
                            warning = True
                        else:
                            os.remove(errorFileName)
        if not error and hasattr(self, "link"):
            error = self.link()
        if error:
            result = "Compilation/Linkage Error(s)"
        elif warning:
            result = "Compilation Warning(s)"
        else:
            result = ""
        if result != "":
            printErr(result)
            compile_obj.error += result + "\n"
        return result, error, warning