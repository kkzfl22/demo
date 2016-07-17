package com.liujun.jvm.dataguru.eleven.asm.proxy;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.liujun.jvm.dataguru.eleven.asm.proxy.info.Account;

public class Generator {

    public static void main(String[] args) throws Exception {
        String basePath = Generator.class.getClassLoader().getResource(".").getPath();
        System.out.println(basePath);
        ClassReader cr = new ClassReader("com.liujun.jvm.dataguru.eleven.asm.proxy.info.Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        FunctionProxy fp = new FunctionProxy(cw);

        cr.accept(fp, ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();

        File file = new File(basePath + "com/liujun/jvm/dataguru/eleven/asm/proxy/info/Account.class");

        System.out.println(file.getPath());
        FileOutputStream output = new FileOutputStream(file);
        output.write(data);
        output.close();

        Account acc = new Account();
        acc.operation();

    }

}
