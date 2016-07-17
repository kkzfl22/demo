package com.liujun.jvm.dataguru.eleven.asm.demo;

import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 通过asm生成代码
* 源文件名：GeneratorCode.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月17日
* 修改作者：liujun
* 修改日期：2016年7月17日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class GeneratorCode extends ClassLoader {

    /**
     * 引入maven
     * <!-- https://mvnrepository.com/artifact/org.ow2.asm/asm -->
        <dependency>
            <groupId>org.ow2.asm</groupId>
            <artifactId>asm</artifactId>
            <version>5.0</version>
        </dependency>
     * 
    * 方法描述
    * @param args
    * @创建日期 2016年7月17日
    */
    public static void main(String[] args) {
        // 得到一个class写入对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        // 首先生成类对象信息
        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "NumAdd", null, "java/lang/Object", null);

        // 生成构造方法信息
        MethodVisitor methodVis = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        // 首先将this对象压入栈中
        methodVis.visitVarInsn(Opcodes.ALOAD, 0);
        // 调用object的初始化方法
        methodVis.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        // 将方法返回
        methodVis.visitInsn(Opcodes.RETURN);
        // 重新计算栈针大小
        methodVis.visitMaxs(0, 0);
        // 方法结束
        methodVis.visitEnd();

        // 生成main方法
        methodVis = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null,
                null);

        // 局部变量全部没有定义变量，直接指令操作

        // int a=6;
        // int b=7;
        // int c=(a+b)*3;
        // System.out.println(c);
        // bipush 8位带符号整数入栈
        methodVis.visitIntInsn(Opcodes.BIPUSH, 6);
        // 存储局部变量表
        methodVis.visitVarInsn(Opcodes.ISTORE, 2);
        // bipush 8位带符号整数入栈
        methodVis.visitIntInsn(Opcodes.BIPUSH, 7);
        // 储到局部变量表4
        methodVis.visitVarInsn(Opcodes.ISTORE, 3);

        // 开始进行运算
        // 首先读取
        methodVis.visitVarInsn(Opcodes.ILOAD, 2);
        methodVis.visitVarInsn(Opcodes.ILOAD, 3);
        // 进行加法运算
        methodVis.visitInsn(Opcodes.IADD);
        // 读取局部变量
        methodVis.visitIntInsn(Opcodes.BIPUSH, 3);
        // 进行乘法运行
        methodVis.visitInsn(Opcodes.IMUL);
        // 将结果存储到局部变量表4
        methodVis.visitVarInsn(Opcodes.ISTORE, 4);

        // 访问局部变量
        methodVis.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        // 输出信息
        methodVis.visitIntInsn(Opcodes.ILOAD, 4);
        // methodVis.visitLdcInsn("这是第一个程序");
        // 调用打印方法
        methodVis.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        // 方法返回
        methodVis.visitInsn(Opcodes.RETURN);
        // 设置栈的大小信息
        methodVis.visitMaxs(0, 0);
        // 方法结束
        methodVis.visitEnd();

        byte[] code = cw.toByteArray();

        // 得到对象信息
        GeneratorCode asmhelow = new GeneratorCode();

        Class exampleClass = asmhelow.defineClass("NumAdd", code, 0, code.length);

        try {
            exampleClass.getMethods()[0].invoke(null, new Object[] { null });
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
