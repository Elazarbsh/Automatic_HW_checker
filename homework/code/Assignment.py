import datetime

from util import *
from Test import *

class Assignment:
    def __init__(self, course, csvRow):
        importCSVDict(self, csvRow)
        if self.courseId != course.id :
            raise ImportError
        self.course = course
        self.deadline = datetime.datetime.strptime(self.deadline, "%d/%m/%Y %H:%M")
        self.directory = os.path.join(self.course.instructor.getHomeDir(), str(self.course.id), self.shortName)
        self.jPlagLanguage=""
        self.WindowsOS = False
        self.DisplayGrades = False
        self.AllowedFileNames = []
        self.GradeForExtracting = 20
        self.PenaltyForStructure = 5
        self.PenaltyForWarnings = 5
        self.GradeForCompilation = 40
        self.TimeOut=60

    def loadProperties(self):
        propertyFileName=os.path.join(self.directory, self.shortName + ".properties")
        if os.path.isfile(propertyFileName):
            loadProperties(self, propertyFileName)

    def loadTests(self):
        """Scan tests directory for files whose name start with 'input' or end with '.test'"""
        self.tests = {}
        for dirEntry in sorted(os.listdir(self.directory)):
            if dirEntry.startswith("input") and os.path.isfile(os.path.join(self.directory, dirEntry)):
                test = Test(self, dirEntry[5:])
                self.tests[test.name] = test

    def createResultFiles(self):
        prefix = str(self.course.id) + '_' + self.shortName + '_'
        resultsFile = open(prefix + 'results.csv', 'a+')
        gradesFile = open(prefix + 'grades.csv', 'w')
        self.resultsWriter = csv.writer(resultsFile)
        self.gradesWriter = csv.writer(gradesFile)
        self.gradesWriter.writerow(["ID", "Grade", "Feedback"])

    def logUnextractable(self, errStr, ids):
        self.resultsWriter.writerow([errStr])
        for id in ids:
            self.gradesWriter.writerow([id, 0, '"' + errStr + '"'])
        printErr(errStr)
        compile_obj.error += errStr + "\n"

    def __str__(self):
        return self.shortName + ", " + self.name + ", Deadline:" + str(self.deadline)

    def checkPlagiarism(self):
        if self.jPlagLanguage=="":
            return
        subprocess.call("java -cp $CLASSPATHJP jplag.JPlag -l " + self.jPlagLanguage + " -s . -r ../jplagResults", shell=True)
        url = "file://" + os.path.join(os.getcwd(), "..", "jplagResults", "index.html")
        subprocess.Popen(["google-chrome", url])