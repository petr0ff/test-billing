cd target\surefire-reports\html
ERASE *.html
cd ..\..\..
mvn test -DsuiteXmlFile=test-suites/integration-test-suite.xml