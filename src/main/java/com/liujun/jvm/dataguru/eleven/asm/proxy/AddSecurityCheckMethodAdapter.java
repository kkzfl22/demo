package com.liujun.jvm.dataguru.eleven.asm.proxy;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {

    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    /**
     * 进行开始解析
     */
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/liujun/jvm/dataguru/eleven/asm/proxy/info/SecurityCheck",
                "checkSecurity", "()Z", false);
        super.visitCode();
    }

}
