from util import *
from Assignment import *
from Testable import *

class SML (Testable):

    def __init__ (self, course, csvRow):
        super(SML, self).__init__(course, csvRow)
        self.compilable = []
        self.subdirectoriesAllowed = False
        self.allowedExtensions = ["sml", "txt", "pdf"]
        self.commandLine = ["sml.bat"]

    def link(self):
            return