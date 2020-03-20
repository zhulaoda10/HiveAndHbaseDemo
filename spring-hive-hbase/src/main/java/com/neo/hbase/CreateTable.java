package com.neo.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class CreateTable {
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		Configuration config = HBaseConfiguration.create();
		// 这边注释起来的是动态设定zookeeper参数的方法，如果你没有hbase-site.xml 或者想动态改变
		// 可以采用动态方式设定
	    //
	     config.set("hbase.zookeeper.quorum",
	                "192.168.121.134,192.168.121.133,192.168.121.131");
	    config.set("hbase.zookeeper.property.clientPort", "2181");
	    config.set("hbase.cluster.distributed", "true");
		
	    
	    Connection connection = ConnectionFactory.createConnection(config); 
	   
	   
		// 使用配置文件创建一个 admin 对象
	      Admin admin= connection.getAdmin(); 
	    //HBaseAdmin admin = new HBaseAdmin(config); 这种方式已过时
	    
	    // 创建表
	    HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("people"));
	    // 创建2个列簇
	    tableDescriptor.addFamily(new HColumnDescriptor("name"));
	    tableDescriptor.addFamily(new HColumnDescriptor("contactinfo"));
	    
	    admin.createTable(tableDescriptor);
	    
	    // 接下来搞点数据进去呗
	    String[][] people = {
	        { "1", "Marcel", "Haddad", "marcel@fabrikam.com"},
	        { "2", "Franklin", "Holtz", "franklin@contoso.com" },
	        { "3", "Dwayne", "McKee", "dwayne@fabrikam.com" },
	        { "4", "Rae", "Schroeder", "rae@contoso.com" },
	        { "5", "Rosalie", "burton", "rosalie@fabrikam.com"},
	        { "6", "Gabriela", "Ingram", "gabriela@contoso.com"} };

	      //TODO
	      Table table = connection.getTable(TableName.valueOf("people"));
	    //HTable table = new HTable(config, "people"); 这种方式已过时
	    
	    //  把这些数据插入到表里面
	    for (int i = 0; i< people.length; i++) {
	      //第一列做rowkey
	      Put person = new Put(Bytes.toBytes(people[i][0]));
	      
	      //把 Marcel 放到 name 这个列簇的 first 这个字段去
	      person.addColumn(Bytes.toBytes("name"), Bytes.toBytes("first"), Bytes.toBytes(people[i][1]));
	      person.addColumn(Bytes.toBytes("name"), Bytes.toBytes("last"), Bytes.toBytes(people[i][2]));
	      person.addColumn(Bytes.toBytes("contactinfo"), Bytes.toBytes("email"), Bytes.toBytes(people[i][3]));
	      table.put(person);
	    }
	    
	    // 最后要记得提交和关闭表
	    ((HTable) table).flushCommits();
	    table.close();
	}

}
