#Autorepairs project - Spring Security configured with JWT

##This is a demo project for autorepairs shop which maintains a database about parts for vehicles.

* You can register a new user
* You can authenticate/log a user
* Spareparts for the moment are filters, rims and tyres. 
  * Anyone can see public all the spareparts
  * Only authenticated users can create new sparepart items, and update them
  * Only authenticated users with admin role has the authority to delete spareparts from the database
* The database comes with initially generated data based on the OPEN-CLOSE principle


* In order to start testing the project on POSTMAN, you might better look also the documentation of the other project ../JWT-SimpleDemo - 
a bearer JWT token should be added as an Authorization header for all authenticated https requests.


* Sample request info (endpoint and body when requested) are given in comments above each controller in the `web` folder.

//TODO: check too many comments / check exceptions (@ControllerAdvice) / check register / check when with real PasswordEncoder / 


* We save the data in MySQL database using Spring Data and Hibernate