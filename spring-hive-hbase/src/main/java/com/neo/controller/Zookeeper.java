package com.neo.controller;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Zookeeper implements Watcher {
	 private static final int TIMEOUT = 3000;
	    public static String  Url="193.112.112.23:2181";   // 自行修改为自己搭建的zookeeper的ip和端口号
	    private static CountDownLatch connectedSemphore = new CountDownLatch(1);
	    
	    public static void main(String[] args) throws Exception {
	        ZooKeeper zookeeper = new ZooKeeper(Url,5000,//
	                new Zookeeper());
	        connectedSemphore.await();
	        String path1 = zookeeper.create("/zk-test-ephemeral-","中国".getBytes(),Ids.OPEN_ACL_UNSAFE,
	                CreateMode.EPHEMERAL);
	        System.out.println("Success create znode:"+path1);
	        
	        String path2 = zookeeper.create("/zk-test-ephemeral-","".getBytes(),
	                Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
	        System.out.println("Success create znode:"+path2);
	    }
	    
	    @Override
	    public void process(WatchedEvent event){
	        if(KeeperState.SyncConnected == event.getState()){
	            connectedSemphore.countDown();
	        }
	    }
}
