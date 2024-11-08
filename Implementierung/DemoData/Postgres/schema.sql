CREATE TABLE Person(PID INT8 NOT NULL PRIMARY KEY, Name VARCHAR(50) NOT NULL, Email VARCHAR(50));
CREATE TABLE Project(PrID INT8 NOT NULL PRIMARY KEY, Name VARCHAR(50) NOT NULL,CreatedAt date NOT NULL, PID INT8 NOT NULL,CONSTRAINT "FK1.PID" FOREIGN KEY (PID) REFERENCES Person (PID) ON DELETE CASCADE);
CREATE TABLE Issue(IID INT8 NOT NULL PRIMARY KEY, Titel VARCHAR(50) NOT NULL,CreatedAt date NOT NULL, State VARCHAR(50) NOT NULL , StateReason VARCHAR(50) NOT NULL , PID INT8 NOT NULL,CONSTRAINT "FK1.PID" FOREIGN KEY (PID) REFERENCES Person (PID) ON DELETE CASCADE, PrID INT8 NOT NULL,CONSTRAINT "FK2.PrID" FOREIGN KEY (PrID) REFERENCES Project (PrID) ON DELETE CASCADE)