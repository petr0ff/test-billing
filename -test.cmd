cd target\surefire-reports\html
ERASE *.html
cd ..\..\..
mvn test -DsuiteXmlFile=test-suites/test-suite.xml