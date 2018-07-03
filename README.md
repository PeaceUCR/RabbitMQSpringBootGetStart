# SpringBoot-RESTSwaggerAPITest
REST API controller at com/example/demo/controller/ItemController.java

Add Log4j to add the log file for application in pom.xml and log4j2-spring.xml
Log4j Configurarion Reference(Pending for more complex config)
https://springframework.guru/log4j-2-configuration-using-xml/


For CRUD update,
only hibernate provide update with keeping other properties,
For CRUD Repo interface solution just get the object from db then update it,
if use the updateDetailsByName() through @Query, the updateDate will not be update,


SwaggerUI configuration in pom.xml and SwaggerConfig
can open at http://localhost:8080/swagger-ui.html#/
