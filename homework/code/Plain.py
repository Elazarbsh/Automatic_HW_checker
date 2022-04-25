from Assignment import *
from util import *

class Plain (Assignment):
    """The class of non-programming assignments."""

    def __init__ (self, course, csvRow):
        super(Plain, self).__init__(course, csvRow)
        self.subdirectoriesAllowed = True
        self.allowedExtensions = ["txt", "pdf", "doc", "java", "c", "cpp", "sml", "py"]

    def runTests(self):
        return {}, 0

    def compile(self):
        return "", False