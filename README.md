## Spring Boot API </br>
The application create, delete and list tasks.</br>
Each task can be executed by sending start and end time, a count will start from start to end value.
The task executed maybe stopped mid way through before the end value is reached.
---
***To Run***</br>
Run the Spring Boot app. </br>
In a REST development tool </br>
POST http://localhost:8080/tasks/ with JSON headers "id" and "name" to create the task </br>
POST http://localhost:8080/tasks/{id}/execute with JSON headers "start" and "end" for start value and end value to start the timer</br>
GET http://localhost:8080/tasks/{id}/stop to stop the timer

