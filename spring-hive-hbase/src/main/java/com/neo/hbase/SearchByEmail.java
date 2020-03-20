package com.neo.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class SearchByEmail {
public static void main(String[] args) throws IOException {
		
		//创建配置
		Configuration config = HBaseConfiguration.create();
        
		
		  Connection connection = ConnectionFactory.createConnection(config); 
		  Admin admin = connection.getAdmin();
		  
		
		
		// 打开表
		Table table =connection.getTable(TableName.valueOf("people"));
		
		//定义一系列要用到的列簇和列
		// 定义列簇
		byte[] contactFamily = Bytes.toBytes("contactinfo");
		// 列
		byte[] emailQualifier = Bytes.toBytes("email");
		//列簇
		byte[] nameFamily = Bytes.toBytes("name");
		//列
		byte[] firstNameQualifier = Bytes.toBytes("first");
		byte[] lastNameQualifier = Bytes.toBytes("last");
		// 创建一个正则表达式的比较器
		RegexStringComparator emailFilter = new RegexStringComparator("rosalie@fabrikam.com");
		// 创建一个filter，把这个正则比较器传进去
		SingleColumnValueFilter filter = new SingleColumnValueFilter(contactFamily, emailQualifier, CompareOp.EQUAL, emailFilter);
 
		// 创建一个 scan对象
		Scan scan = new Scan();
		
		//把filter 传进去
		scan.setFilter(filter);
 
		// 开始查询，并获取结果
		ResultScanner results = table.getScanner(scan);
		// 遍历结果打印数据
		for (Result result : results) {
			String id = new String(result.getRow());
			byte[] firstNameObj = result.getValue(nameFamily, firstNameQualifier);
			String firstName = new String(firstNameObj);
			byte[] lastNameObj = result.getValue(nameFamily, lastNameQualifier);
			String lastName = new String(lastNameObj);
			System.out.println(firstName + " " + lastName + " - ID: " + id);
			byte[] emailObj = result.getValue(contactFamily, emailQualifier);
			String email = new String(emailObj);
			System.out.println(firstName + " " + lastName + " - " + email + " - ID: " + id);
		}
		//关闭结果
		results.close();
		
		//关闭表
		table.close();
	}

}
