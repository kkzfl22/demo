package com.liujun.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

	private Socket socket;

	public TimeServerHandler(Socket socke) {
		this.socket = socke;
	}

	@Override
	public void run()  {

		// 1,使用Bufferedreader包装请注流
		BufferedReader in = null;
		// 打印使用printWrite;
		PrintWriter out = null;

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);

			String line = null;

			String outReceive = null;

			while (true) {
				
				line = in.readLine();
				
				if(line == null)
				{
					break;
				}
				
				System.out.println("this server receive cent :" + line);

				outReceive = "QUERY TIME ORDER".equalsIgnoreCase(line) ? new Date(System.currentTimeMillis()).toString() : "BEAD ORDER";
				out.println(outReceive);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
				out = null;
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(null != socket)
			{
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			socket=null;
		}

	}

}
