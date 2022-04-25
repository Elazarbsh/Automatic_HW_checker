"""Contains classes and utility functions that access the data in the csv files
   Instructors and courses are contained in two dictionnaries, one per class.
   A course object represents an instance of a course given by an instructor. It contains a reference to the
   instructor. A course also contains a list of assignments."""
import os
from Assignment import *
from util import *

class Instructor:
    byName = {}  #A dictionary of all instructors, indexed by name

    @staticmethod
    def loadFromCSV(fileName):
        """Create an instructor object for every line of the csv file fileName"""
        for row in csvLines(fileName):
            Instructor(row)

    def __init__(self, csvRow):
        importCSVDict(self, csvRow)
        Instructor.byName[self.name] = self

    def __str__(self):
        return self.name

    def getHomeDir(self):
        """Return the name of the home directory for instructor"""
        return os.path.join(os.path.expanduser("/var/www/html/" + self.userName),"homework")


class Course:
    byId = {}  #A dictionary of all courses, indexed by Id

    @staticmethod
    def loadFromCSV(fileName):
        """Create a course object for every line of the csv file fileName"""
        for row in csvLines(fileName):
            Course(row)

    def __init__(self, csvRow):
        importCSVDict(self, csvRow)
        self.instructor = Instructor.byName[self.instructor]
        self.assignments = []           # Initialize the assignments
        self.active = eval(self.active) # Convert the string into a boolean
        Course.byId[self.id] = self

    def __str__(self):
        return self.name + ", " + self.year + ", " + self.semester + ", " + str(self.instructor)

    def getAssignments(self):
        """return the list of assignments. If it is empty load it first from the csv file
        in the instructor's home directory. Load only lines with matching course Id.
        Every assignment is an object that inherits from assignment."""
        if len(self.assignments) == 0:
            for csvRow in csvLines(os.path.join(self.instructor.getHomeDir(),"assignments.csv")):
                if int(csvRow['courseId']) == self.id :
                    cls = classForName(csvRow['type'] + "." + csvRow['type'])
                    temp = cls(self, csvRow)
                    self.assignments.append(temp)
        return self.assignments
