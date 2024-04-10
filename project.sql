/*
 Each time this file is executed, it will reset the database to the original state defined below.
 You can import this directly in your database by (a) manually entering the first three lines of
 commands form this file, (b) removing the first three lines of commands from this file, and
 (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).

 During grading, TAs will assume that these two tables exist, but will enter different values.
 Thus you cannot assume that any of the values provided here exist, but you can assume the tables
 exist.

 You may optionally create additional tables in the ensf380project database with demonstration 
 data, provided that you provide the information in a valid SQL file which TAs can import and
 clearly include this information in your instructions.
 */


DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project


CREATE TABLE INQUIRER (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20) NOT NULL
);

INSERT INTO INQUIRER (id, firstName,  lastName, phoneNumber) VALUES
(1, 'Dominik', 'Pflug', '123-456-9831'),
(2, 'Yaa', 'Odei', '123-456-8913'),
(3, 'Cecilia', 'Cobos', '123-456-7891'),
(4, 'Hongjoo', 'Park', '123-456-8912');
INSERT INTO INQUIRER (id, firstName, phoneNumber) VALUES
(5, 'Saartje', '123-456-7234'),
(6, 'Urjoshi', '456-123-4281');

CREATE TABLE INQUIRY_LOG (
    id SERIAL PRIMARY KEY,
    inquirer int NOT NULL,
    callDate DATE NOT NULL,
    details VARCHAR(500) NOT NULL,
    foreign key (inquirer) references INQUIRER(id) ON UPDATE CASCADE
);

INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES
(1, 1, '2024-02-28', 'Theresa Pflug'),
(2, 2, '2024-02-28', 'Offer to assist as volunteer'),
(3, 3, '2024-03-01', 'Valesk Souza'),
(4, 1, '2024-03-01', 'Theresa Pflug'),
(5, 1, '2024-03-02', 'Theresa Pflug'),
(6, 4, '2024-03-02', 'Yoyo Jefferson and Roisin Fitzgerald'),
(7, 5, '2024-03-02', 'Henk Wouters'),
(8, 3, '2024-03-03', 'Melinda'),
(9, 6, '2024-03-04', 'Julius');


-- Create table for LOCATION
CREATE TABLE LOCATION (
    name VARCHAR(100) PRIMARY KEY,
    address VARCHAR(255) NOT NULL
);

INSERT INTO LOCATION (name, address) VALUES
('Location 1', 'Address 1'),
('Location 2', 'Address 2'),
('Location 3', 'Address 3');

CREATE TABLE DISASTERVICTIMS (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    entryDate VARCHAR(20) NOT NULL,
    dateOfBirth VARCHAR(20),
    age INT,
    locationName VARCHAR(100),
    FOREIGN KEY (locationName) REFERENCES LOCATION(name)
);

-- Victims with dateOfBirth specified
INSERT INTO DISASTERVICTIMS (firstName, entryDate, dateOfBirth, locationName)
VALUES ('John', '2024-03-15', '1990-05-20', 'Location 1'),
       ('Jane', '2024-03-16', '1985-10-12', 'Location 1'),
       ('Emily', '2024-03-18', '1999-02-28', 'Location 3');

-- Victims with age specified (calculate dateOfBirth from age and entryDate)
INSERT INTO DISASTERVICTIMS (firstName, entryDate, age, locationName)
VALUES ('Mike', '2024-03-17', 45, 'Location 2'),  -- Age 45
       ('Sarah', '2024-03-19', 30, 'Location 2');  -- Age 30

-- Create table for Supplies
CREATE TABLE SUPPLIES (
    type VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    locationName VARCHAR(100),
    FOREIGN KEY (locationName) REFERENCES LOCATION(name)
);

-- Insert sample data into the Supplies table
INSERT INTO SUPPLIES (type, quantity, locationName)
VALUES ('Water Bottles', 500, 'Location 1'),
       ('Canned Food', 1000, 'Location 1'),
       ('Blankets', 300, 'Location 2'),
       ('Medical Supplies', 200, 'Location 2'),
       ('Flashlights', 150, 'Location 3');


-- Table for Inquirer
CREATE TABLE Inquirer (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(15) NOT NULL,
    info TEXT
);

-- Insert sample Inquirer
INSERT INTO Inquirer (firstName, lastName, phonenumber, info)
VALUES ('John', 'Doe', '123-456-7890', 'Looking for brother'),
 ('Kam', 'Lao', '321-456-7890', 'Looking for Sister');


-- Table for ReliefService
CREATE TABLE RELIEFSERVICE (
    id SERIAL PRIMARY KEY,
    inquirerId INTEGER REFERENCES Inquirer(id),
    missingPersonFirstName VARCHAR(50) NOT NULL,
    missingPersonLastName VARCHAR(50) NOT NULL,
    dateOfInquiry DATE NOT NULL,
    lastKnownLocationName VARCHAR(100) NOT NULL,
    lastKnownLocationAddress TEXT NOT NULL,
    interactionHistory TEXT[]
);

-- Insert sample ReliefService entry
INSERT INTO RELIEFSERVICE (inquirerId, missingPersonFirstName, missingPersonLastName, dateOfInquiry, lastKnownLocationName, lastKnownLocationAddress, interactionHistory)
VALUES (1, 'Jane', 'Smith', '2024-04-10', 'Location Name', 'Location Address', ARRAY['Have not heard back from my brother yet', 'Still looking for my brother']),
(5, 'Holly', 'Jack', '2024-04-12', 'Location Name2', 'Location Address2', ARRAY['Have not heard back from my sister yet', 'Still looking for my sister']);
