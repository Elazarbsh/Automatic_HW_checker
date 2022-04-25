from Assignment import *
from util import *
from Executable import *
from CFlat import *

class C99Flat (CFlat):
    """The class for flat C99 programming language assignments:
    A directory with *.cpp, *.h and *.txt files. The *.cpp files are compiled and linked to generate an executable named 'prog'."""

    def __init__ (self, course, csvRow):
        super(C99Flat, self).__init__(course, csvRow)
        self.compileCommand = "gcc -Wall -g -std=c99 -c "
        self.compileWarningsCommand = "gcc -Wall -Werror -g -std=c99 -c "
        self.jPlagLanguage = "c/c++"
