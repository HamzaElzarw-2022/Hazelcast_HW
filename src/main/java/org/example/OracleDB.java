package org.example;

import java.sql.*;

public class OracleDB {


    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521";
    private final String USER = "system";
    private final String PASSWORD = "passHamza";
    private Statement stmt;

    public OracleDB() {


        try {
            // Register the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");

            // Execute a query
            stmt = conn.createStatement();

        } catch (SQLException se) {
            // Handle errors for JDBC
            System.out.println("error while connecting to OracleDB: " + se.getMessage());
        } catch (Exception e) {
            // Handle errors for Class.forName
            System.out.println("error while registering Oracle JDBC driver: " + e.getMessage());
        }
    }

    public long insertRandom(int numberOfOperations) {

        try {

            System.out.println("started inserting " + numberOfOperations + " entries to Oracle");
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < numberOfOperations; i++) {

                String sql = "INSERT INTO numbers (id, numberCol) VALUES ("+ i +", "+ NumberGenerator.getRandomNumber(numberOfOperations) +")";
                stmt.executeQuery(sql);
            }

            long timeResult = ((System.currentTimeMillis() - startTime)/1000);

            System.out.println("finished inserting " + numberOfOperations + " entries to Oracle");
            return timeResult;

        } catch (SQLException e) {
            System.out.println("exception while inserting to oracle " + e.getMessage());
            return -1;
        }

    }
    public long selectRandom(int numberOfOperations) {

        try {

            System.out.println("started selecting " + numberOfOperations + " entries to Oracle");
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < numberOfOperations; i++) {

                String sql = "SELECT numberCol FROM numbers WHERE id = " + NumberGenerator.getRandomNumber(numberOfOperations);
                ResultSet rs = stmt.executeQuery(sql);

//                if(rs.next()) {
//
//                    int column1 = rs.getInt("numberCol");
//                    System.out.println("numberCol: " + column1);
//                }
            }

            long timeResult = ((System.currentTimeMillis() - startTime)/1000);

            System.out.println("finished selecting " + numberOfOperations + " entries to Oracle");
            return timeResult;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception while selecting from oracle " + e.getMessage());
            return -1;
        }
    }

    public void deleteAllRecords() {
        String sql = "DELETE FROM numbers";

        try {
            stmt.executeUpdate(sql);
            System.out.println("All records have been deleted from the Oracle numbers table.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting records from Oracle: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
