## System Requirements 
- Java JRE 11 or above
- Maven 
- MySQL 8.0 or above

## Instructions to run

### Step 1 Install dependencies 
- install maven using: sudo apt-get install maven
- install mysql using: sudo apt-get install mysql-server

### Step 2 setup database 
- Open src/main/application.properties 
- Change the username and password to the user name and password of your MySQL database
- Default credentials are: username: root password: admin database: testdb
- Use the schema.sql file to initialize the schema of your database. 


### Running the program
- run the command: mvn package 
    - This should run all the tests, download all dependencies and generate an executable jar file in the target folder
- Run the program: java -jar target/Blood-Donation-0.0.1-SNAPSHOT.jar
- Once the program is running open localhost:8080 to get to the website

### Creating an admin account 
- Since there is no feature to create a new admin account on the front end
of the application it must be done manually
- After creating an account if you want to promote the account to admin enter the following SQL command
  - INSERT INTO account_roles VALUES(2,(SELECT id FROM accounts WHERE email = "you@example.com"));