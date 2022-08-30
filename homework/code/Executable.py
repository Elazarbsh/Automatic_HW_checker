import os

from Assignment import *
from Testable import *
from util import *

class Executable (Testable):
    """The class of testable programs that consist of one exe file called prog"""

    def __init__ (self, course, csvRow):
        super(Executable, self).__init__(course, csvRow)
        self.commandLine = [os.path.join(".", "prog")]

    def link(self):
        print("Linking...")
        errorFileName="linkageerrors.txt"
        compile_obj.log += "Linking..." + "\n"
        compile_obj.link_errors_file += "linkageerrors.txt" + "\n"
        with open(errorFileName, 'w') as errFile:
            if subprocess.call(self.linkCommand, shell=True, stderr=errFile) == 0:
                os.remove(errorFileName)
                return False  # No Error
            else:
                return True
