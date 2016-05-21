package com.liujun.pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;

/**
 * 进行对象的深拷贝信息
 * 
 * final 关键字不能用在原型模式中
 * 
 * @author liujun
 * 
 * @date 2015年5月7日
 * @vsersion 0.0.1
 */
public class DeepPrototype implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 基本信息
	 */
	private BaseItem item;

	/**
	 * 身高
	 */
	private int hight;

	public BaseItem getItem() {
		return item;
	}

	public void setItem(BaseItem item) {
		this.item = item;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	@Override
	public String toString() {
		return "DeepPrototype [item=" + item + ", hight=" + hight + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DeepPrototype deepClone() {
		// // 写入当前对象的二进制流
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// ObjectOutputStream oos = new ObjectOutputStream(bos);
		// oos.writeObject(this);
		//
		// // 读出当前对象的二进制流信息
		// ByteArrayInputStream inputIs = new
		// ByteArrayInputStream(bos.toByteArray());
		// ObjectInputStream ois = new ObjectInputStream(inputIs);
		//
		// return (DeepPrototype) ois.readObject();

		DeepPrototype deopObject = null;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ByteArrayInputStream inputIs = null;
		ObjectInputStream ois = null;

		try {
			//写入当前对象的二进制流
			oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			
			// 读出当前对象的二进制流信息
			inputIs = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(inputIs);
			deopObject = (DeepPrototype) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(ois);
			IOUtils.closeQuietly(inputIs);
			IOUtils.closeQuietly(oos);
			IOUtils.closeQuietly(bos);
		}

		return deopObject;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DeepPrototype li1 = new DeepPrototype();

		BaseItem item = new BaseItem();

		item.setBaseAge(24);
		item.setBaseName("basenameA");

		li1.setItem(item);
		li1.setHight(170);

		System.out.println("li1:" + li1);

		DeepPrototype li2 = li1.deepClone();
		li2.getItem().setBaseName("baseNamB");
		//li2.getItem().setBaseAge(22);
		li2.setHight(180);

		System.out.println("li2:" + li2);
		System.out.println("li1:" + li1);
	}

}
