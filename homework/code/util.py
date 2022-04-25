"""Contains various utility functions"""
import zipfile
import csv
import os
import re
import shutil
import tempfile
import subprocess

def checkId(id):
    """Check for the validity of the string id as an Israeli ID number"""
    #checkIsraeliId(id)
    #checkTurkishId(id)


def checkIsraeliId(id):
    """Check for the validity of the string id as an Israeli ID number"""
    if (len(id) != 9):
        raise ValueError ("Id should be 9 digits: " + id)
    checkDigit = (10 - sum([sum(divmod(i,10)) for i in [(i % 2 + 1) * eval(id[i]) for i in range(8)]])%10)%10
    if (eval(id[-1]) != checkDigit):
        raise ValueError ("Bad Check Digit in: " + id)


def checkTurkishId(id):
    """Check for the validity of the string id as a Turkish ID number"""
    if (len(id) != 11):
        raise ValueError ("Id should be 11 digits: " + id)
    checkDigit1 = (sum (eval(id[i]) for i in range(0,10,2)) * 7 - sum(eval(id[i]) for i in range(1,9,2)))%10
    if eval(id[-2]) != checkDigit1:
        raise ValueError("Bad Check Digit in: " + id)
    checkDigit2 = (sum(eval(id[i]) for i in range(0, 9)) + checkDigit1) % 10
    if (eval(id[-1]) != checkDigit2):
        raise ValueError("Bad Check Digit in: " + id)

def classForName( name ):
    """Similar to classForName in Java: return the class object of the class whose name is given as parameter"""
    parts = name.split('.')
    module = ".".join(parts[:-1]) #module
    m = __import__( module )
    return getattr(m, parts[-1])

def extractZipFile(fileName, dir):
    """"Extract the contents of the zip file fileName into the directory dir"""
    with zipfile.ZipFile(fileName, mode='r', allowZip64=True) as zip:
        zip.extractall(dir)

def printErr(*args, **kwargs):
    print("ERROR >>>", *args, **kwargs)
    

def csvLines(fileName):
    """yield each time a line of the csv file fileName"""
    with open(fileName) as file:
        for row in csv.DictReader(file):
            yield row

def importCSVDict(obj, csvRow):
    """Import the row of a CSV file given in csvRow as properties into the object obj
    if the value appears to be an integer import it as an integer, otherwise it should be imported as string"""
    for (fieldName, value) in csvRow.items():
        try:
            value = int(value)
        except:
            pass
        finally:
            obj.__dict__[fieldName] = value

def loadProperties(self, fileName, comment_char="#", continuation_char="\\"):
    """Executes a property file line by line and add its values to variables to obj"""
    with open(fileName, "rt") as file:
        command=""
        for line in file:
            if line.strip() and not line.startswith(comment_char):
                command += line.strip()
                if command.endswith(continuation_char):
                    command = command[:-1]
                else:
                    exec(command, self.__dict__)
                    command = ""

def fileContents(fileName):
    if not os.path.isfile(fileName):
        return []
    with open(fileName, "r") as file:
        return file.readlines()

def isHidden(fileName):
    return fileName[0] == "."  or fileName[0:2] == "__"

def removeHidden():
    removedDirs = []
    removedFiles = []
    """Remove hidden files and directories"""
    for root, dirs, files in os.walk(".", topdown=False):
        for name in dirs:
            if isHidden(name):
                fullName = os.path.join(root, name)
                shutil.rmtree(fullName)
                removedDirs.append(fullName)
    for root, dirs, files in os.walk(".", topdown=False):
        for name in files:
            if isHidden(name):
                fullName = os.path.join(root, name)
                os.remove(fullName)
                removedFiles.append(fullName)
    return (removedDirs, removedFiles)

def removeSubdirectories():
    removedDirs = []
    for root, dirs, files in os.walk(".", topdown=False):
        for name in dirs:
            fullName = os.path.join(root, name)
            shutil.rmtree(fullName)
            removedDirs.append(name)
    return removedDirs

def editUserIncludes(fileName):
    pattern = re.compile('#include *"(.*)"')
    with tempfile.NamedTemporaryFile(mode='w',encoding='ISO-8859-1',delete=False) as output:
        with open(fileName, mode='r', encoding='ISO-8859-1') as input:
            for line in input:
                match = pattern.match(line)
                if match:
                    included = match.group(1)
                    line = '#include "' + included.lower() + '"\n'
                output.write(line)
    os.remove(fileName)
    shutil.move(output.name,fileName)

def runCommand(commandLine, stdin=None, stdout=None, stderr=None, timeOut=60):
    try:
        proc = subprocess.Popen(commandLine, stdin=stdin, stdout=stdout, stderr=stderr)
    except Exception as e:
        print (commandLine, e)
        return False
    try:
        proc.wait(timeOut)
        return True
    except subprocess.TimeoutExpired:
        proc.kill()
        printErr("timed out")
        return False