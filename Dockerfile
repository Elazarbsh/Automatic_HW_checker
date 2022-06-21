FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update
RUN apt-get -y install apache2
RUN apt-get -y install python3
RUN apt-get -y install csh
RUN apt-get update
RUN apt -y install python3-pip
RUN apt-get -y install python3-distutils
RUN apt-get -y install build-essential
RUN apt-get -y install libfl-dev

RUN a2enmod cgi
RUN service apache2 restart

RUN echo "                       \n \
<Directory /var/www/html/homework/deploy>        \n \
   Options +ExecCGI              \n \
   AddHandler cgi-script .py     \n \
   DirectoryIndex index.py       \n \
</Directory>                     \n \
" >> /etc/apache2/apache2.conf

RUN rm -rf /var/www/html
COPY homework /var/www/html/homework
COPY hoorys /var/www/html/hoorys
COPY donitaco /var/www/html/donitaco
COPY horovitzmic /var/www/html/horovitzmic

COPY homework/deploy/assets /var/www/html/assets

COPY homework/deploy/front_end/api /var/www/html/deploy/api

COPY homework/deploy/hwcheck /usr/bin

RUN chmod -R 777 /var/www/html
RUN chmod -R 777 /usr/bin

RUN ln -sf /usr/bin/python3 /usr/local/bin/python3

EXPOSE 80

CMD ["/usr/sbin/apache2ctl",  "-D", "FOREGROUND"]
