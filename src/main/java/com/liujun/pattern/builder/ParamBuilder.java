package com.liujun.pattern.builder;

/**
 * 使用build模式来进行实例化参数对象，
 * 
 * 对于有众多实例化参数的类，在不确定哪个需要初化时，可以提供更加灵活的机制
 * 
 * @author liujun
 * 
 * @date 2014年12月16日
 * @vsersion 0.0.1
 */
public class ParamBuilder {

	/**
	 * 用户的id
	 */
	private final int id;

	/**
	 * 用户的
	 */
	private final String name;

	/**
	 * 用户的姓名
	 */
	private final int age;

	/**
	 * 用户的地址
	 */
	private final String address;

	/**
	 * 用户的身高
	 */
	private final String hight;

	/**
	 * 用来进行作为参数的build类
	 * 
	 * @author liujun
	 * 
	 * @date 2014年12月16日
	 * @vsersion 0.0.1
	 */
	public static class Builder {
		/**
		 * 用户的id
		 */
		private int id;

		/**
		 * 用户的
		 */
		private String name;

		/**
		 * 用户的姓名
		 */
		private int age;

		/**
		 * 用户的地址
		 */
		private String address;

		/**
		 * 用户的身高
		 */
		private String hight;

		public Builder(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder hight(String hight) {
			this.hight = hight;
			return this;
		}

		public ParamBuilder getBuilder() {
			return new ParamBuilder(this);
		}

	}

	private ParamBuilder(Builder build) {
		this.id = build.id;
		this.name = build.name;
		this.address = build.address;
		this.age = build.age;
		this.hight = build.hight;
	}

	@Override
	public String toString() {
		return "ParamBuilder [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", hight=" + hight + "]";
	}

	public static void main(String[] args) {
		ParamBuilder param = new ParamBuilder.Builder(26, "刘军").age(26).getBuilder();
		System.out.println(param.toString());
	}

}
