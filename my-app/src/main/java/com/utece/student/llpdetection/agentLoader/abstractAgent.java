package com.utece.student.llpdetection.agentLoader;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.utece.student.llpdetection.transformers.jvmTransformer;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.net.URISyntaxException;

//
// https://www.baeldung.com/java-instrumentation ideas here, abstract Agent to support other classes is what is new.
//
public abstract class abstractAgent {
    public static void premain(
            String agentArgs, Instrumentation inst) throws AttachNotSupportedException, URISyntaxException, AgentLoadException, AgentInitializationException, IOException {

        System.out.println("[Agent] In premain method");
        String className = "com.utece.student.llpdetection.App";
        transformClass(className,inst);
    }

    public static void agentmain(
            String agentArgs, Instrumentation inst) throws URISyntaxException, AgentLoadException, IOException, AgentInitializationException {

        System.out.println("[Agent] In agentmain method");
        String className = "com.utece.student.llpdetection.App";
        transformClass(className,inst);
    }

    public static void transformClass(
            String className, Instrumentation instrumentation) {
        Class<?> targetCls;
        ClassLoader targetClassLoader;
        // see if we can get the class using forName
        Class[] clazz_array = instrumentation.getAllLoadedClasses();
        System.out.println(clazz_array);
        for(Class<?> clazz: clazz_array) {
                targetCls = clazz;
                System.out.println("targetCls is: " + targetCls.toString());
                try {
                    if(targetCls.toString().contains("com.utece.student.llpdetection.agents.")){
                    }
                    else {
                        targetClassLoader = instrumentation.getClass().getClassLoader();

                        transform(targetCls, targetClassLoader, instrumentation);
                        return;
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
        }
        // otherwise iterate all loaded classes and find what we want
        for(Class<?> clazz: instrumentation.getAllLoadedClasses()) {
            if(clazz.getName().equals(className)) {
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                return;
            }
        }
    }

    private static void transform(
            Class<?> clazz,
            ClassLoader classLoader,
            Instrumentation instrumentation) {
        jvmTransformer dt = new jvmTransformer(
                clazz.getName(), classLoader);
        instrumentation.addTransformer((ClassFileTransformer) dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Transform failed for: [" + clazz.getName() + "]", ex);
        }
    }
}
