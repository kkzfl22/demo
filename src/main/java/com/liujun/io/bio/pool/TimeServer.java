package com.liujun.io.bio.pool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.liujun.io.bio.TimeServerHandler;

/**
 * 使用线程池来进行改良
 * 
 * 资源可以重复利用，但利用率依然很低，因为的底层还是同步的，在等待响应结果，但不会出现资源耗尽的情况
 * @author liujun
 *
 * @date 2015年1月15日
 * @vsersion 0.0.1
 */
public class TimeServer {

	
	public static void main(String[] args) {
		int port = 9001;
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("socker server  open port :"+port);
			
			//创建线程池对象
			TimeServerHandlerExecutePool pool = new TimeServerHandlerExecutePool(100, 10);
			
			Socket client = null;
			
			while(true)
			{
				client = server.accept();
				pool.execute(new TimeServerHandler(client));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(null != server)
			{
				System.out.println("server close");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;
			}
		}
		
		
	}
}
