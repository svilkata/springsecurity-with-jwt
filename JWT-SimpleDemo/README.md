#Simple demo configuring Spring security to be working with the JWT

How to test it:
1. Open the JWT-SimpleDemo project locally, and run the JwtSimpleDemoApplication.

For the sake of simplicity, legacy and testing purposes only(where working with plain text passwords), we use the NoOpPasswordEncoder PasswordEncoder. 

2. In order to first authorize, run on POSTMAN a POST http request on endpoint http://localhost:8083/authenticate
with the below body(we have hardcored our demo with only 1 user which has username "admin" and "pass123")
{
   "username": "admin",
   "password": "pass123"
}

The response returns the JWT token.

We can debug/decode our JWT token on the website https://jwt.io/

3. Next, we will run on POSTMAN a GET http request at REST controller home with endpoint "/" 
by adding header `"Authorization": "Bearer <our current JWT token>"`
and the result should be "Welcome to My JWT Demo".