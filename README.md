#job-portal
* Get users 
GET REQUEST - http://localhost:8080/api/v1/user

* Add User
POST - http://localhost:8080/api/v1/user
        body - 
        {
          "name": "User4",
          "email": "user4goodera.com",
          "role": "JOB_SEEKER",
          "mobile": "9999999944",
          "password": "userpw"
        }
        
* Get user by Id 
  GET - http://localhost:8080/api/v1/user/<userId>
  
* Delete User
  DELETE - http://localhost:8080/api/v1/user/<userId>
  
* Verify User
  PUT - http://localhost:8080/api/v1/user/verify
  body - {
            "userId": 4,
            "otp": 4984
         }
  
* Login User 
  POST - http://localhost:8080/api/v1/user/login
  body -  {
    "email": "admin@goodera.com1",
    "password": "adminpw",
    "role": "ADMIN"
}
