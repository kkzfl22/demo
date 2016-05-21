package com.liujun.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileWrite {

	public static void main(String[] args) throws IOException {

		String filePath = "f:/GBConfig.properties";

		// 获得流通道
		FileChannel chan = null;

		FileInputStream file = null;

		try {
			// 读取文件
			file = new FileInputStream(filePath);

			chan = file.getChannel();

			// 获得一声内存区域进行读取操作
			ByteBuffer BufByte = ByteBuffer.allocate(512);
			
			while (chan.read(BufByte) != -1) {
				BufByte.flip();
				String value = new String(BufByte.array());
				System.out.println(value);
				BufByte.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != chan) {
				chan.close();
			}
			if (null != file) {
				file.close();
			}
		}

	}
}
