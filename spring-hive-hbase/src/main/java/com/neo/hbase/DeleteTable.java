package com.neo.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class DeleteTable {
public static void main(String[] args) throws IOException {
		
		//创建配置
		Configuration config = HBaseConfiguration.create();
 
		// 建立 admin
		HBaseAdmin admin = new HBaseAdmin(config);
 
		// 先 disable 表，再delete
		admin.disableTable("people");
		admin.deleteTable("people");
	}

}
