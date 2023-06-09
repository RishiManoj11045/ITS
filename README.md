
# Inventory Tracking System

This project aims at developping a inventory tracking system for a department in a college.


## Documentation

The Documentation of the project can be found in the project folder "Documentation". This folders contains the requuired PDF's and their corresponding LateX codes.  

## Authors

- [@anudeep512](https://www.github.com/anudeep512)
- [@Ihafnonaim](https://www.github.com/Ihafnonaim)
- [@RishiManoj11045](https://www.github.com/RishiManoj11045)

## Tech Stack

**Frontend:** JavaFX

**Backend:** Java

**Database:** MySQL

**Tools Used:** Scene Builder, MySQL Workbench



## Installation, Usage and Maintanence

### Installing project

Install ITS project files directory using git clone, Run:

    git clone https://github.com/anudeep512/ITS 
### Prerequisite

Your local Machine must have MySQL installed and MySQL Workbench(preferably for excuting .sql scripts)

Create a Local MySQL connection of SQL server in your machine with :

    username: "root"
    password: "krmrhzb12"
    Hostname: "localhost"
    port: 3306
    Connection name: "MySql80"

Now direct to SQL_codes folder in home directory of the project, then run the scripts:

For setting up of the database to use run:
    
    project_its_create_tables.sql

For loading of data required intially to run the application:

    project_its_data_loading.sql

For resetting the whole database:

    project_its_dbreset.sql

For deleting all the tables in the database:

    project_its_delete_tables.sql

**Note:**

1) When database is resetted we need to run project_its_data_loading.sql script to use the application.

2) When database is deleted we need to run project_its_create_tables.sql and project_its_data_loading.sql scripts to use the application.

Install IntellJ IDEA from the following link based on your machine: 

    https://www.jetbrains.com/idea/download

This is the IDE used for running the project.

Install Oracle OpenJDK version 19 from(or otherwise IntellJ asks for install, install at that time):

    https://www.oracle.com/in/java/technologies/downloads/
    


### Setting up the project 

1) Open the project in IntellJ.
2) Go to File -> Project Structure -> Modules.
3) Now in the right tab go to Dependencies tab, then press "+" icon.
4) Then select "JARs or Directories", now locate to **~/src/dependencies** in project home directory. Select all the JARs in that directory and add them to the dependencies of the project.
5) Then press **Apply** button to add the dependencies to our project.

### Running the application

1) Locate to **~src/main/java/com/example/page12/Application.java**.
2) Now run **Application.java** file to use the Application.

## Using the application 

First we login in as Admin and then we can generate all the required fields info from that to use the application furthur.

Initial Login credentials:

    Username: "AD1"
    password: 1234

**Note:** Username is case insensitive.
    
