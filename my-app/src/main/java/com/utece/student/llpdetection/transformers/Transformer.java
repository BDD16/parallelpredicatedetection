package com.utece.student.llpdetection.transformers;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;

public abstract class Transformer {
    protected ArrayList<String> targetClassName;

    private static void transform(
            Class<?> clazz,
            ClassLoader classLoader,
            Instrumentation instrumentation) {
        com.utece.student.llpdetection.transformers.jvmTransformer dt = new com.utece.student.llpdetection.transformers.jvmTransformer(
                clazz.getName(), classLoader);
        instrumentation.addTransformer((ClassFileTransformer) dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Transform failed for: [" + clazz.getName() + "]", ex);
        }
    }

    public abstract byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer);
}
