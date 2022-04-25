from Assignment import *
from Testable import *
from util import *

class Python (Testable):
    """The class of python programs"""

    def __init__ (self, course, csvRow):
        super(Python, self).__init__(course, csvRow)
        self.compilable = []
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["py", "txt", "pdf"]
        self.commandLine = ["python3"]
        self.jPlagLanguage = "python3"

    def link(self):
            return
