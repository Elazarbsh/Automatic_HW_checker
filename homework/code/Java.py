import os
import subprocess

from Assignment import *
from Test import *
from Testable import *
from util import *

class Java (Testable):
    """The class of java programs"""

    def __init__ (self, course, csvRow):
        super(Java, self).__init__(course, csvRow)
        self.compilable = ["java"]
        self.subdirectoriesAllowed = True
        self.allowedExtensions = ["java", "class", "txt", "TXT", "pdf", "PDF", "classpath", "project", "gitignore"]
        self.commandLine=["java"]
        self.jPlagLanguage = "java17"


    def compile(self):
        error = False
        warning = False

        sourceFiles = "";
        for (dirName, dirNames, fileNames) in os.walk("."):
            for fileName in fileNames:
                if os.path.splitext(fileName)[1] == ".java":
                    sourceFiles += " ";
                    sourceFiles += os.path.join(dirName, fileName)
        with open(os.devnull, 'w') as nullFile:
            with open("compileerr.txt", 'w') as errFile:
                if subprocess.call("javac -encoding utf8 " + sourceFiles, shell=True, stderr=errFile) != 0:
                    error = True
                elif subprocess.call("javac -encoding utf8 -Xlint:all -Xlint:-processing -Werror " + sourceFiles, shell=True, stderr=errFile) != 0:
                    warning = True
        if error:
            result = "Compilation Error(s)"
            compile_obj.error += result + "\n"
        elif warning:
            result = "Compilation Warning(s)"
            output[WARNING].append(result)
        else:
            result = ""
        if result != "":
            printErr(result)
        return result, error, warning
