#!/bin/bash

# Start the first process
node app.js &
  
# Start the second process
/usr/sbin/apache2ctl -D FOREGROUND &
  
# Wait for any process to exit
wait -n
  
# Exit with status of process that exited first
exit $?