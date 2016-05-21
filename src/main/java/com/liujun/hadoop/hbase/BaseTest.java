package com.liujun.hadoop.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class BaseTest {

	private Configuration config;

	public BaseTest() {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.zookeeper.quorum", "192.168.3.130");
		config.set("hbase.master", "192.168.3.130:600000");
	}

	/**
	 * 获得连接对象
	 * 
	 * @return
	 */
	public HConnection getConn() {
		/**
		 * 连接对象
		 */
		HConnection conn = null;
		;
		try {
			conn = HConnectionManager.createConnection(config);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void main(String[] args) {

		String tableName = "localTest";

		BaseTest base = new BaseTest();
		base.createTable(tableName);
		base.testAddValue(tableName, "row1");
		base.testAddValue(tableName, "row2");
		base.testAddValue(tableName, "row3");
		// base.deleteTable(tableName);

		base.delRowData(tableName, "row1");

		// 输出rowd
		base.queryAllData(tableName);
		
		base.testAddValue(tableName, "row4");
		base.testAddValue(tableName, "row5");
		
		//进行过滤
		base.queryFilter(tableName);
	}

	/**
	 * 创建表
	 * 
	 * @param table
	 */
	public void createTable(String table) {
		System.out.println("创建表开始...");
		try {
			HBaseAdmin admin = new HBaseAdmin(config);

			// 检查当前表是否存在
			if (admin.tableExists(table)) {
				admin.disableTable(table);
				admin.deleteTable(table);
				System.out.println("当前表存在，删除表成功");
			}

			TableName tableName = TableName.valueOf(table);
			// 创建表名描述信息
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

			// 创建列族
			tableDescriptor.addFamily(new HColumnDescriptor("column1"));
			tableDescriptor.addFamily(new HColumnDescriptor("column2"));
			tableDescriptor.addFamily(new HColumnDescriptor("column3"));

			admin.createTable(tableDescriptor);
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("表创建完成....");
	}

	/**
	 * 测试添加数据
	 * 
	 * @param tableName
	 */
	public void testAddValue(String tableName, String rowid) {
		System.out.println("插入数据开始...");

		HConnection conn = this.getConn();
		try {
			HTableInterface tableInf = conn.getTable(tableName);

			Put put = new Put(rowid.getBytes());
			put.add("column1".getBytes(), null, "aaa".getBytes());
			put.add("column2".getBytes(), null, "bbb".getBytes());
			put.add("column3".getBytes(), null, "ccc".getBytes());

			tableInf.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除表
	 * 
	 * @param tableName
	 */
	public void deleteTable(String tableName) {
		HBaseAdmin admin;
		try {
			admin = new HBaseAdmin(config);
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除行数据
	 * 
	 * @param tableName
	 * @param rowid
	 */
	@SuppressWarnings("unchecked")
	public void delRowData(String tableName, String rowid) {
		try {
			HTable tableDel = new HTable(config, tableName);
			List delList = new ArrayList();

			delList.add(new Delete(rowid.getBytes()));

			tableDel.delete(delList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询一个表中的所有数据
	 * 
	 * @param tableName
	 */
	public void queryAllData(String tableName) {
		HConnection conn = getConn();
		try {
			HTableInterface tableInf = conn.getTable(tableName);

			ResultScanner scan = tableInf.getScanner(new Scan());

			for (Result result : scan) {
				System.out.println("获得到rowkey:" + new String(result.getRow()));
				// 遍历列，输出
				for (KeyValue keyValue : result.raw()) {
					System.out.println(
							"列:" + new String(keyValue.getFamily()) + "====值：" + new String(keyValue.getValue()));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryFilter(String tableName) {
		HConnection conn = getConn();
		try {
			HTableInterface tableInf = conn.getTable(tableName);

			Filter filter = new SingleColumnValueFilter(Bytes.toBytes("column1"), null, CompareOp.EQUAL,
					Bytes.toBytes("aaa"));

			Scan scan = new Scan();
			scan.setFilter(filter);

			ResultScanner scanResult = tableInf.getScanner(scan);

			for (Result result : scanResult) {
				System.out.println("获得到rowkey:" + new String(result.getRow()));
				// 遍历列，输出
				for (KeyValue keyValue : result.raw()) {
					System.out.println(
							"列:" + new String(keyValue.getFamily()) + "====值：" + new String(keyValue.getValue()));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
