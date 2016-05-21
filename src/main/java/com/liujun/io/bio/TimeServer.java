package com.liujun.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 使用传统的BIO（同步阻塞IO），由于客户端与服务器是1:1的占用，当有万千上万个连接请求时,性能急剧下降，无法适用于高并发，场合，当连接过高时，很容易出现宕机
 * @author liujun
 *
 * @date 2015年1月15日
 * @vsersion 0.0.1
 */
public class TimeServer {

	@Autowired(required = false)
	public static void main(String[] args) throws Exception {
		int port = 9001;

		if (args != null && args.length > 0) {
			port = Integer.parseInt(args[0]);
		}



		ServerSocket serve = null;
		try {
			serve = new ServerSocket(port);
			System.out.println("serve socket start... port :" + port);

			Socket client = null;
			while (true) {
				client = serve.accept();
				new Thread(new TimeServerHandler(client)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (null != serve) {
				System.out.println("serversocket close ...");
				serve.close();
				serve = null;
			}
		}

	}
}
