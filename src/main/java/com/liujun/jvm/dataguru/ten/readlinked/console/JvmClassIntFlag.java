package com.liujun.jvm.dataguru.ten.readlinked.console;

/**
* 源文件名：JvmClass.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月8日
* 修改作者：liujun
* 修改日期：2016年7月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public enum JvmClassIntFlag {

    JAVA_LANGUAGE_FLAG(0xCAFEBABE, "java语言标识"),

    CONSTANT_Utf8(1, "UTF-8编码的Unicode字符串"),

    CONSTANT_Integer(3, "int类型的字面值"),

    CONSTANT_Float(4, "float类型的字面值"),

    CONSTANT_Long(5, " long类型的字面值"),

    CONSTANT_Double(6, " double类型的字面值"),

    CONSTANT_Class(7, " 对一个类或接口的符号引用"),

    CONSTANT_String(8, "String类型字面值的引用"),

    CONSTANT_Fieldref(9, "对一个字段的符号引用"),

    CONSTANT_Methodref(10, " 对一个类中方法的符号引用"),

    CONSTANT_InterfaceMethodref(11, "对一个接口中方法的符号引用"),

    CONSTANT_NameAndType(12, " 对一个字段或方法的部分符号引用"),

    ACCESSFLAG_ACC_PUBLIC(0x0001, "public"),

    ACCESSFLAG_ACC_FINAL(0x0010, "final,不能被继承."),

    ACCESSFLAG_ACC_SUPER(0x0020, "是否允许使用invokespecial指令，JDK1.2后，该值为true"),

    ACCESSFLAG_ACC_INTERFACE(0x0200, "是否是接口"),

    ACCESSFLAG_ACC_ABSTRACT(0x0400, "抽象类"),

    ACCESSFLAG_ACC_SYNTHETIC(0x1000, "该类不是由用户代码生成,运行时生成的，没有源码"),

    ACCESSFLAG_ACC_ANNOTATION(0x2000, "是否为注解"),

    ACCESSFLAG_ACC_ENUM(0x4000, "是否是枚举")

    ;

    /**
     * 
    * @字段说明 flag
    */
    private int flag;

    private String value;

    /**
    * 构造方法
    * @param flag
    * @param value
    */
    private JvmClassIntFlag(int flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
