-- Create the CarConnect Database
CREATE DATABASE IF NOT EXISTS carconnect;
USE carconnect;

-- Table 1: Customer
CREATE TABLE IF NOT EXISTS Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Address TEXT,
    Username VARCHAR(50) UNIQUE,
    Password VARCHAR(255),
    RegistrationDate DATE
);

-- Table 2: Vehicle
CREATE TABLE IF NOT EXISTS Vehicle (
    VehicleID INT AUTO_INCREMENT PRIMARY KEY,
    Model VARCHAR(50),
    Make VARCHAR(50),
    Year INT,
    Color VARCHAR(30),
    RegistrationNumber VARCHAR(50) UNIQUE,
    Availability BOOLEAN,
    DailyRate DECIMAL(10, 2)
);

-- Table 3: Reservation
CREATE TABLE IF NOT EXISTS Reservation (
    ReservationID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    VehicleID INT,
    StartDate DATETIME,
    EndDate DATETIME,
    TotalCost DECIMAL(10, 2),
    Status VARCHAR(20),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID)
);

-- Table 4: Admin
CREATE TABLE IF NOT EXISTS Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Username VARCHAR(50) UNIQUE,
    Password VARCHAR(255),
    Role VARCHAR(50),
    JoinDate DATE
);
