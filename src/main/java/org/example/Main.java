package org.example;

import java.sql.SQLOutput;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        System.out.println("program started!!\n");

        HazelDB hazelDB = new HazelDB();

        System.out.println("time taken: " + hazelDB.putRandom(20000) + " seconds\n");
        System.out.println("time taken: " + hazelDB.getRandom(20000) + " seconds\n");

        System.out.println("time taken: " + hazelDB.putRandom(100000) + " seconds\n");
        System.out.println("time taken: " + hazelDB.getRandom(100000) + " seconds\n");

        OracleDB oracleDB = new OracleDB();

        System.out.println("time taken: " + oracleDB.insertRandom(20000) + " seconds\n");
        System.out.println("time taken: " + oracleDB.selectRandom(20000) + " seconds\n");

        //delete all records from oracle before inserting again
        oracleDB.deleteAllRecords();

        System.out.println("time taken: " + oracleDB.insertRandom(100000) + " seconds\n");
        System.out.println("time taken: " + oracleDB.selectRandom(100000) + " seconds\n");
    }
}