package com.liujun.memcache;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

@SuppressWarnings("unused")
public class MemGetDataComp {

	private static MemcachedClient client;

	public MemGetDataComp() {

	}

	public static void main(String[] args) {

		try {
			client = new MemcachedClient(new InetSocketAddress("localhost", 11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client.add("111", 3600, "1111");
		client.add("22", 3600, "1111");
		client.add("33", 3600, "1111");
		client.add("44", 3600, "1111");
		
		
	}

}
