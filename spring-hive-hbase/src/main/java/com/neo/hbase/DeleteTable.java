package com.neo.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class DeleteTable {
public static void main(String[] args) throws IOException {
		
		//创建配置
		Configuration config = HBaseConfiguration.create();
 
		// 建立 admin
	        //此种方式已过时不建议使用
		//HBaseAdmin admin = new HBaseAdmin(config);
		Connection connection = ConnectionFactory.createConnection(config); 
		Admin admin = connection.getAdmin();
		//转化为表名
                TableName name = TableName.valueOf("mebr");//具体的表明
		// 先 disable 表，再delete
		admin.disableTable(name);
		admin.deleteTable(name);
	}

}
