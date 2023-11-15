package com.utece.student.llpdetection.transformers;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
public class jvmTransformer implements ClassFileTransformer {

    ClassLoader targetClassLoader = null;
    public String targetClassName;

    public jvmTransformer(String name, ClassLoader classLoader) {
        super();
        targetClassName = name;
        targetClassLoader = classLoader;

    }

    public byte[] getBytesFromTransform(ClassLoader loader,
                                        String className,
                                        Class<?> classBeingRedefined,
                                        ProtectionDomain protectionDomain,
                                        byte[] classfileBuffer){
        return transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
    }
    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) {
        byte[] byteCode = classfileBuffer;

        this.targetClassName = className;
        String finalTargetClassName = this.targetClassName.replaceAll("\\.", "/");
//        if (!className.equals(finalTargetClassName)) {
//            return byteCode;
//        }
//
//        if (className.equals(finalTargetClassName)
//                && loader.equals(targetClassLoader)) {

            System.out.println("[Agent] Transforming class " + className);
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(finalTargetClassName);
                CtMethod m = cc.getDeclaredMethod(
                        "main");
                m.addLocalVariable(
                        "startTime", CtClass.longType);
                m.insertBefore(
                        "startTime = System.currentTimeMillis();" +
                                "InstrumentBinary binary_trampoline = new InstrumentBinary();" +
                                "binary_trampoline.customFunction();" +
                                "System.out.println('BANG BANG BANG');"
                        );

                StringBuilder endBlock = new StringBuilder();

                m.addLocalVariable("endTime", CtClass.longType);
                m.addLocalVariable("opTime", CtClass.longType);
                endBlock.append(
                        "endTime = System.currentTimeMillis();");
                endBlock.append(
                        "opTime = (endTime-startTime)/1000;");

                endBlock.append(
                        "LOGGER.info(\"[Application] Withdrawal operation completed in:" +
                                "\" + opTime + \" seconds!\");");
                endBlock.append("binary_trampoline.customFunction();");

                m.insertAfter(endBlock.toString());

                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                System.out.println("Exception" + e);
                System.out.println("Error in jvmTransformer");
            }

        return byteCode;
    }
}