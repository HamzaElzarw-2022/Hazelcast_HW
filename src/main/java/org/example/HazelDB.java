package org.example;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Random;

public class HazelDB {

    private ClientConfig clientConfig;
    private HazelcastInstance client;
    private IMap<String, Integer> numbersMap;


    public HazelDB() {

        clientConfig = new ClientConfig();
        clientConfig.setClusterName("dev");
        clientConfig.getNetworkConfig().addAddress("localhost:5701");

        client = HazelcastClient.newHazelcastClient(clientConfig);

        numbersMap = client.getMap("numbers1");

    }

    public long putRandom(int numberOfOperations) {

        System.out.println("started putting " + numberOfOperations + " entries to HazelCast");
        long startTime = System.currentTimeMillis();

        for(int i=0; i<numberOfOperations; i++) {
            numbersMap.put(Integer.toString(i), NumberGenerator.getRandomNumber(numberOfOperations));
        }

        long timeResult = ((System.currentTimeMillis() - startTime)/1000);

        System.out.println("finished putting " + numberOfOperations + " entries to HazelCast");
        return timeResult;
    }

    public long getRandom(int numberOfOperations) {

        System.out.println("started getting " + numberOfOperations + " entries from HazelCast");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfOperations; i++) {

            String randomKey = "" + NumberGenerator.getRandomNumber(numberOfOperations);
            Integer number = numbersMap.get(randomKey);

            //System.out.println("number with key " + randomKey + ": " + number);
        }

        long timeResult = ((System.currentTimeMillis() - startTime)/1000);

        System.out.println("finished getting " + numberOfOperations + " entries from HazelCast");
        return timeResult;
    }

}
