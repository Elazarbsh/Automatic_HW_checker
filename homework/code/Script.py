import os
import stat

from Assignment import *
from Testable import *
from util import *

class Script (Testable):
    """The class of testable programs that consist of a script called prog.sh"""

    def __init__ (self, course, csvRow):
        super(Script, self).__init__(course, csvRow)
        self.compilable = []
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["sh", "txt"]
        fileName = os.path.join(".", "prog.sh")
        self.commandLine = [fileName]
        """TODO: The following is probably buggy(not tested)"""
        st = os.stat(fileName).st_mode
        os.chmod(fileName, st | stat.S_IEXEC)

    def link(self):
        return