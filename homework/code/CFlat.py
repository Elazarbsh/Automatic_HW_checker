from Assignment import *
from util import *
from Executable import *

class CFlat (Executable):
    """The class for flat C programming language assignments:
    A directory with *.c, *.h and *.txt files. The *.c files are compiled and linked to generate an executable named 'prog'."""

    def __init__ (self, course, csvRow):
        super(CFlat, self).__init__(course, csvRow)
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["c", "h", "txt", "TXT", "lex", "yacc", "pdf", "PDF"]
        self.compilable = ["c"]
        self.compileCommand = "gcc -Wall -g -c"
        self.compileWarningsCommand = "gcc -Wall -Werror -g -c"
        self.linkCommand = "gcc -g *.o -lfl -o prog"
        self.jPlagLanguage = "c/c++"
