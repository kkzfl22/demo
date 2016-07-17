package com.liujun.jvm.dataguru.eleven.asm.proxy;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FunctionProxy extends ClassVisitor {

    public FunctionProxy(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        MethodVisitor warppedMv = mv;

        if (mv != null) {
            if ("operation".equals(name)) {
                warppedMv = new AddSecurityCheckMethodAdapter(mv);
            }

        }

        return warppedMv;
    }

}
