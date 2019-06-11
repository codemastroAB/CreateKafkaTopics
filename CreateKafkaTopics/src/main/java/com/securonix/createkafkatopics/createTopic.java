/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securonix.createkafkatopics;


import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.Properties;

public class createTopic {
    public static void main(String[] args) {

        ZkClient zkClient = null;
        ZkUtils zkUtils = null;

        try {
            String zookeeperHosts = "10-0-8-150.securonix.com:2181,10-0-8-151.securonix.com:2181,10-0-8-152.securonix.com:2181";
            int sessionTimeOutInMs = 15 * 1000;
            int connectionTimeOutInMs = 10 * 1000;

            zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);

            String topicName = "testtopicjava12345";
            int noOfPartition = 1;
            int noOfReplication = 1;
            Properties topicConfiguration = new Properties();

            AdminUtils.createTopic(zkUtils, topicName, noOfPartition, noOfReplication, topicConfiguration, RackAwareMode.Enforced$.MODULE$);
            zkClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
