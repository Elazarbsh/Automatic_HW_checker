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

#
RUN echo 'Yes, do as I say!' | a2dismod deflate
#
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
# COPY donitaco /var/www/html/donitaco
# COPY horovitzmic /var/www/html/horovitzmic

# COPY homework/deploy/assets /var/www/html/assets
COPY homework/deploy/hwcheck /usr/bin
#
COPY front_end_prod/dist/assets /var/www/html/assets
COPY front_end_prod/dist/index.html /var/www/html/homework/index.html
COPY front_end_prod/dist/assets /var/www/html/homework/assets
#

RUN chmod -R 777 /var/www/html
RUN chmod -R 777 /usr/bin

RUN ln -sf /usr/bin/python3 /usr/local/bin/python3

RUN apt-get update
RUN apt-get -y install nodejs
RUN apt-get -y install npm

COPY express_app /app/express_app
WORKDIR /app/express_app
RUN npm install

EXPOSE 80

CMD ./wrapper_script.sh
