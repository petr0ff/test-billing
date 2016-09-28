cd target\surefire-reports\html
ERASE *.html
cd ..\..\..
mvn test -DsuiteXmlFile=test-suites/unit-test-suite.xml