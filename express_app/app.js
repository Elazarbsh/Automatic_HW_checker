const express = require('express');
var cors = require('cors');
var fileupload = require("express-fileupload");
const path = require('path');
const { stderr } = require('process');
const fs = require('fs');
const unzip = require('unzip');
var StringDecoder = require('string_decoder').StringDecoder;
var decoder = new StringDecoder('utf8');

const app = express();
const port = 4000;

app.use(cors()); // Use this after the variable declaration
app.use(express.static('public'));
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(fileupload());


const { spawn } = require('child_process');

app.get('/', (req, res) => {
  res.send('Hello Worldddd!');
  console.log("got a GET request");
});

app.post('/', async (req, res) => {
  res.writeHead(200, {
    Connection: "keep-alive",
    "Content-Type": "text/event-stream",
    "Cache-Control": "no-cache",
  });
  const file = req.files.fileName;
  const dirName = path.basename(file.name, '.zip');
  console.log(dirName);
  const formPath = "/var/www/html/homework/deploy/form.py";

  if (fs.existsSync('/var/www/html/homework/deploy/' + dirName)) {
    console.log('file exists');
    res.end();
  } else {
    file.mv('/var/www/html/homework/deploy/' + file.name, function (err) {
      fs.chmodSync('/var/www/html/homework/deploy/' + file.name, 0755);
      const hwcheck = spawn('python3', ['-u', formPath, file.name, "checkHomework"], { cwd: '/var/www/html/homework/deploy/' });

      hwcheck.stdout.on('data', (data) => {
        var message = decoder.write(data);
        res.write(`data: ${message}\n\n`);
        res.flushHeaders();
      });

      hwcheck.stderr.on('data', (data) => {
        console.log(`stderr: ${data}`);
        res.write('event: message\n');
        res.write(`data: ${data}\n\n`);
        res.connection.destroy();
        // res.set("Connection", "close");
        // res.flushHeaders();

      });

      hwcheck.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
        res.write('event: message\n');
        res.write(`data: ${code}\n\n`);
        res.end();
      });
    });
  }

});

app.post('/clear-results', async (req, res) => {
  res.writeHead(200, {
    Connection: "keep-alive",
    "Content-Type": "text/event-stream",
    "Cache-Control": "no-cache",
  });
  const file = req.files.fileName;
  const formPath = "/var/www/html/homework/deploy/form.py";

  const hwcheck = spawn('python3', ['-u', formPath, file.name, "clearResults"], { cwd: '/var/www/html/homework/deploy/' });

  hwcheck.stdout.on('data', (data) => {
    var message = decoder.write(data);
    res.write(`data: ${message}\n\n`);
    res.flushHeaders();
  });

  hwcheck.stderr.on('data', (data) => {
    console.log(`stderr: ${data}`);
    res.write('event: message\n');
    res.write(`data: ${data}\n\n`);
    res.end();
  });

  hwcheck.on('close', (code) => {
    console.log(`child process exited with code ${code}`);
    res.write('event: message\n');
    res.write(`data: ${code}\n\n`);
    res.end();
  });
});


app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});