from util import *
from Executable import *
from Assignment import *

class CPPFlat (Executable):
    """The class for flat C++ programming language assignments:
    A directory with *.cpp, *.h and *.txt files. The *.cpp files are compiled and linked to generate an executable named 'prog'."""

    def __init__ (self, course, csvRow):
        super(CPPFlat, self).__init__(course, csvRow)
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["cpp", "h", "txt", "TXT", "pdf", "PDF"]
        self.compilable = ["cpp"]
        self.compileCommand = "g++ -Wall -g -c"
        self.compileWarningsCommand = "g++ -Wall -Werror -g -c"
        self.linkCommand = "g++ -g *.o -o prog"
        self.jPlagLanguage = "c/c++"
