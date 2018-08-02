#!/bin/sh
#defined
TOMCAT_HOME="/home/dd/apache-tomcat-8.5.32/"
ID=`ps -ef | grep java | grep tomcat|awk '{print $2}'`
echo $ID
echo "kill tomcat"
kill -9 $ID
echo "remover war file"
cd "$TOMCAT_HOME"/webapps
#rm -rf JavaWeb-0.0.1-SNAPSHOT
rm -rf dd-mvc.war
echo "copy war to webapp"
cp /root/.jenkins/workspace/test/target/dd-mvc.war "$TOMCAT_HOME"/webapps
cd "$TOMCAT_HOME"/bin
echo "start tomcat"
./startup.sh