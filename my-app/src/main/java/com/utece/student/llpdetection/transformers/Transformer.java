package com.utece.student.llpdetection.transformers;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

public abstract class Transformer {
    protected ArrayList<String> targetClassName;

    public static void transform(
            Class<?> clazz,
            ClassLoader classLoader,
            Instrumentation instrumentation) {
        if(clazz != null && classLoader !=null) {
            com.utece.student.llpdetection.transformers.jvmTransformer dt = new com.utece.student.llpdetection.transformers.jvmTransformer(
                    clazz.getName(), classLoader);
            instrumentation.addTransformer(dt, true);
            try {
                instrumentation.retransformClasses(clazz);
            } catch (Exception ex) {
                int x = 0;
                System.out.println(clazz);
                System.out.println("something happened for clazz: " + clazz);
            }
        }
    }
}
