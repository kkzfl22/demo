package com.liujun.pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClassSize {

	public static void main(String[] args) throws IOException {
		Integer value = 10;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(value);
		// // 读出当前对象的二进制流信息
		System.out.println(bos.size());
	}

}
