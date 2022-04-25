from Assignment import *
from util import *
from Executable import *
from CPPFlat import *

class CPP11Flat (CPPFlat):
    """The class for flat C++ 11 programming language assignments:
    A directory with *.cpp, *.h and *.txt files. The *.cpp files are compiled and linked to generate an executable named 'prog'."""

    def __init__ (self, course, csvRow):
        super(CPP11Flat, self).__init__(course, csvRow)
        self.compileCommand = "g++ -Wall -g -std=c++11 -c "
        self.compileWarningsCommand = "g++ -Wall -Werror -g -std=c++11 -c "
        self.jPlagLanguage = "c/c++"
