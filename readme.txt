This application is a calculator that takes a json as an input and gives a json as a result. This was created using Spring MVC.
An example of json that can be calculated is intpu.json file that it's in the project.
To run this application you need
1. The jar from libs in the build path (or get the CalcEngine application from the same repository 
and do a maven clean install)
2.
 2.1 you can run a mvn tomcat7:run and go to http://localhost:8080/CalcWebApp
 2.2 or go to the directory of the project, open comand prompt and write the following command: 
 		java -jar target/dependency/webapp-runner.jar target/calcWebApp.war 
  	 then go to http://localhost:8080/CalcWebApp
