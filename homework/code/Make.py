from Assignment import *
from util import *
from Executable import *

class Make (Executable):

    def __init__ (self, course, csvRow):
        super(Make, self).__init__(course, csvRow)
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["c", "h", "cpp", "txt", "TXT", "lex", "yacc", "pdf", "PDF"]
        self.compileCommand = "gcc -Wall -g -c"
        self.compileWarningsCommand = "gcc -Wall -Werror -g -c"
        self.linkCommand = "gcc -g *.o -lfl -o prog"

    def checkPlagiarism(self):
        pass
