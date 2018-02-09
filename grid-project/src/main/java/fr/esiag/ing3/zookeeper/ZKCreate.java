package fr.esiag.ing3.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {
	   // create static instance for zookeeper class.
	   private static ZooKeeper zk;

	   // create static instance for ZooKeeperConnection class.
	   private static ZooKeeperConnection conn;

	   // Method to create znode in zookeeper ensemble
	   public static void create(String path, byte[] data) throws 
	      KeeperException,InterruptedException {
	      zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
	      CreateMode.PERSISTENT);
	   }
}