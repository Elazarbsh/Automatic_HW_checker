import os
import subprocess

from util import *
from Assignment import *
from Executable import *

class CMultipleSmall (Executable):
    """The class for C multiple small programming language assignments:
    A directory with *.c, *.h and *.txt files. The *.c files are compiled and
    each one is linked separately to generate an executable having the same name with
    the source file (with the extension stripped)"""

    def __init__ (self, course, csvRow):
        super(CMultipleSmall, self).__init__(course, csvRow)
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["cpp", "h", "txt", "TXT", "pdf", "PDF"]
        self.compilable = ["cpp"]
        self.compileCommand = "g++ -Wall -g -c"
        self.compileWarningsCommand = "g++ -Wall -Werror -g -c"
        self.linkCommand = "g++ -g"
        self.commandLine = [] # Indicates that scripts are compulsory
        self.jPlagLanguage = "c/c++"

    def link(self):
        error = False
        for file in [dirEntry for dirEntry in os.listdir(".") if os.path.isfile(dirEntry)]:
            parts = file.rpartition(".")
            if len(parts) > 3:
                print ("Warning !! files with multiple dots are not recommended: might fail tests")
                tests_obj[file].warning += "Warning !! files with multiple dots are not recommended: might fail tests" + "\n"
            if parts[-1] == "o":
                linkCommand = self.linkCommand +  " " + file + " -o " + parts[-3]
                print ("Linking " + file)
                tests_obj[file].log += "Linking " + file + "\n"
                with open(file+".linkerrors.txt",'w') as errFile:
                    if subprocess.call(linkCommand, shell=True, stderr=errFile) != 0:
                        error = True
        return error
