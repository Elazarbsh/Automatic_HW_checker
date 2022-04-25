#!/usr/bin/python3
import cgitb
import cgi
import subprocess
import sys
import os
import shutil
import sys

cgitb.enable() 
print("Content-Type:text/html;charset=utf-8")
print()
form = cgi.FieldStorage()
fileItem = form['fileName']
if fileItem.filename:
    if (os.path.isabs(fileItem.filename)):
        print('<br>Absolute file names not allowed<br>')
        exit(1)

    fileName = fileItem.filename
    dirName = fileName[:-4]
    if fileName[-4:].lower() != '.zip':
        print('<br>Only .zip files allowed<br>')
        exit(1)

    if form.getvalue('checkHomework'):
# write the input file to server's disk
        with open(fileName, 'wb') as f:
            s = fileItem.file.read()
            f.write(s)
# run hwcheck
        command = 'hwcheck ' + fileName
        print('<h2>')
        sys.stdout.flush()
        try:
            with subprocess.Popen(command, shell=True,
                                  stdout=subprocess.PIPE,
                                  stderr=subprocess.STDOUT) as proc:
                for line in proc.stdout:
                    print(line.rstrip().decode("utf-8") , '<BR>')
                    sys.stdout.flush()
            os.remove(fileName)
        except Exception as e:
            print(e)
        print('</h2>')
    else:
        if os.path.isfile(fileName):
            os.remove(fileName)
        if os.path.isdir(dirName):
            shutil.rmtree(dirName)
        print('Results Cleared')
else:
    print('<br>No file Chosen<br>')
print('<h1><span style="text-decoration: underline;"><strong>',
      'To get the upload page, press the back button of your browser',
      '</strong></span></h1>')
