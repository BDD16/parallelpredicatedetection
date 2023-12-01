package com.utece.student.llpdetection.transformers;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class jvmTransformer implements ClassFileTransformer {

    ClassLoader targetClassLoader = null;
    public String targetClassName;

    public jvmTransformer(String name, ClassLoader classLoader) {
        targetClassName = name;
        targetClassLoader = classLoader;

    }

    public jvmTransformer() {

    }

    public void setTargetClassName(String given){
        targetClassName = given;
    }

    public byte[] getBytesFromTransform(ClassLoader loader,
                                        String className,
                                        Class<?> classBeingRedefined,
                                        ProtectionDomain protectionDomain,
                                        byte[] classfileBuffer){
        return this.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
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
        System.out.println("[Transformer] POWER UP: " + finalTargetClassName);
        if(className.contains("java.lang.Class")){
            try{
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get(finalTargetClassName);

                return cc.toBytecode();
            } catch (IOException | CannotCompileException | NotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {

            System.out.println("[Agent] Transforming class " + className);
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(finalTargetClassName);
                // CtMethod[] m_list = cc.getMethods();
                try {
                    CtMethod m = cc.getDeclaredMethod("runBoruvkaMST");
                    // System.out.println(Arrays.toString(m_list));

                    //for(CtMethod m : m_list ) {
                    m.addLocalVariable(
                            "startTime", CtClass.longType);
                    //m.addLocalVariable("binary_trampoline", CtClass.);
                    ;
                    m.insertBefore(
                            "System.out.println(\"[Application] BANG BANG BANG:\");"
                    );

                    StringBuilder endBlock = new StringBuilder();

                    m.addLocalVariable("endTime", CtClass.longType);
                    m.addLocalVariable("opTime", CtClass.longType);
                    endBlock.append(
                            "endTime = System.currentTimeMillis();");
                    endBlock.append(
                            "opTime = (endTime-startTime)/1000;");

                    endBlock.append(
                            "System.out.println(\"[Application] Withdrawal operation completed in:" +
                                    "\" + opTime + \" seconds!\");");
                    //endBlock.append("binary_trampoline.customFunction();");

                    m.insertAfter(endBlock.toString());
                    System.out.println(Arrays.toString(cc.toBytecode()));
                    //cc.writeFile();


                    // }
                    byteCode = cc.toBytecode();
                    cc.writeFile();
                    System.out.println(Arrays.toString(byteCode));
                    cc.detach();
                } catch (Exception e) {
                    byteCode = cc.toBytecode();
                    cc.detach();
                    System.out.println("MUY MAL");
                    return byteCode;
                }

            } catch (NotFoundException | CannotCompileException | IOException e) {
                System.out.println("Exception" + e);
                System.out.println("Error in jvmTransformer");
            }
            System.out.println("[TRANSFORM] completed byte code is below");
            System.out.println(Arrays.toString(byteCode));
            return byteCode;
        }
    }
}