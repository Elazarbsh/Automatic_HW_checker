

from collections import defaultdict
from dataclasses import dataclass
import dataclasses
# from distutils.log import error
import json

COMPILE="compile"
WARNING="warning"
TEST="tests"
SUMMARY="summary"
HEADER = "header"
EXCEPTION="exception"

output = {HEADER:[], COMPILE:[], WARNING:[], TEST:[], EXCEPTION:[], SUMMARY:[]}

@dataclass
class CompileObject:
    error:str = ""
    error_file:str = ""  # path to error.txt file
    link_error_file:str = "" # path to link error file - will assign just if has link error  
    log: str = ""

@dataclass
class TestObject:
    name: str = ""
    grade: str = ""
    warning: str = ""
    input_file: str = ""    # path to excpected output 
    output_file: str = ""    # path to my output
    error_file: str = ""     # path to my errors
    diff_file: str = ""      # path to diff between input file and output file
    log : str = ""
    result: str = ""         # pass or faild msg
    error: str = ""

tests_obj  = {}
compile_obj = CompileObject() 

def export_running_output_to_file():
    output[COMPILE] = compile_obj
    output[TEST] = tests_obj
    f_output = open("output.json" , "w")
    json.dump(output , f_output, indent=4, default=vars)
    #json.dump(output, output.json)