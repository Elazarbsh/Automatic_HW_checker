

from collections import defaultdict
from dataclasses import dataclass, field
import dataclasses
# from distutils.log import error
import json

COMPILE="compilation"
WARNING="warning"
TEST="tests"
SUMMARY="summary"
HEADER = "header"
EXCEPTION="exception"

output = {HEADER:{}, COMPILE:{}, TEST:{}}

@dataclass
class CompileObject:
    errors:str = ""
    errors_file:str = ""  # path to error.txt file
    link_errors_file:str = "" # path to link error file - will assign just if has link error  
    log: str = ""
    exceptions: str =""

@dataclass
class TestObject:
    name: str = ""
    grade: str = ""
    warning: str = ""
    input_file: str = ""
    user_output: str = ""     # path to expected output 
    correct_output: str = ""    # path to my output
    error_file: str = ""     # path to my errors
    diff_file: str = ""      # path to diff between input file and output file
    log : str = ""
    result: str = ""         # pass or faild msg
    error: str = ""


"""
        "course": "Principles of Programming Languages, 2021-22, Fall, Shlomo Hoory",
        "assignment" : "Hw2, Homework 2, Deadline:2021-12-14 23:55:00",
        "log" :"",
        "summary": {
            "grade": "85/100",
            "grade_log" : "",
            "warnings": "Warning: Deadline is due !!"
        }
"""
@dataclass
class HeaderObject:
    course: str = ""
    assignment: str = ""
    log: str = ""
    summary: dict = field(default_factory=dict)

@dataclass
class HeaderSummaryObject:
    grade: str = ""
    grade_log: str = ""
    warnings: str = ""

tests_obj  = {}
compile_obj = CompileObject() 
header_obj = HeaderObject()
header_summary_obj = HeaderSummaryObject()

def export_running_output_to_file(path):
    header_obj.summary = header_summary_obj
    output[HEADER] = header_obj
    output[COMPILE] = compile_obj
    output[TEST] = tests_obj
    f_output = open(path , "w")
    json.dump(output , f_output, indent=4, default=vars)
    #json.dump(output, output.json)