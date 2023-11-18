package com.utece.student.llpdetection.agents;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.VirtualMachine;
import com.utece.student.llpdetection.agentLoader.abstractAgent;
import com.utece.student.llpdetection.transformers.jvmTransformer;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class

JavaAgent extends abstractAgent {

    static String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    static String jvmPid = jvmName.substring(0, jvmName.indexOf('@'));
    static VirtualMachine jvm;

    static jvmTransformer t;



    public JavaAgent() {
        super();
        {

            this.jvmPid = jvmName.substring(0, jvmName.indexOf('@'));
            String jvmPid1 = jvmPid;
            System.out.println(jvmPid);
            String className = "parallelalgorithms.group9.homework3.ParallelRunners";
            //this.jvm = (VirtualMachine) DynamicDebuggerWithProcessAttachAndPID.attachToProcess(jvmPid1);
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
            try {
                System.out.println("[Agent] In agentmain method");
                String className = "parallelalgorithms.group9.homework3.ParallelRunners";
                com.utece.student.llpdetection.transformers.Transformer.transform(parallelalgorithms.group9.homework3.ParallelRunners.class.getComponentType(), inst.getClass().getClassLoader(), inst);
                System.out.println(inst.isRetransformClassesSupported());

                Class[] classArray = inst.getAllLoadedClasses();

                Arrays.stream(classArray).forEach(c -> {
                    Class<?> targetClass = null;
                    try {
                        targetClass = inst.getClass().forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if( c == targetClass){

                        try {
                            c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }catch(Exception e){
                System.out.println(e);
                System.out.println("DANGER DANGER DANGER");
            }
    }

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

    public static void premain(
            String agentArgs, Instrumentation inst) throws URISyntaxException, AgentLoadException, AgentInitializationException, IOException {

        System.out.println("[Agent] In premain method");
        System.out.println("agentArgs: " + agentArgs);
        String className = "parallelalgorithms.group9.homework3.ParallelRunners";

        try {
            //System.out.println("[Agent] In agentmain method");
            //String className = "parallelalgorithms.group9.homework3.ParallelRunners";
            com.utece.student.llpdetection.transformers.Transformer.transform(parallelalgorithms.group9.homework3.ParallelRunners.class.getComponentType(), inst.getClass().getClassLoader(), inst);
            System.out.println(inst.isRetransformClassesSupported());

            Class[] classArray = inst.getAllLoadedClasses();
            System.out.println(Arrays.toString(classArray));
            Arrays.stream((Class[]) Arrays.stream(classArray).toArray()).forEach(c -> {
                Class<?> targetClass;
                try {
                    targetClass = Class.forName(c.toString());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                    try {
                        transform(targetClass, ClassLoader.getSystemClassLoader(), inst);
                        c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
            });

        }catch(Exception e){
            System.out.println(e);
            System.out.println("DANGER DANGER DANGER");
        }

//        try{
//            Class<?> targetClass = Class.forName(className);
//            targetClass.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
//        }catch(Exception e){
//            System.out.println(e);
//            System.out.println("DANGER DANGER DANGER");
//        }
        //com.utece.student.llpdetection.agents.JavaAgent tryThis = new com.utece.student.llpdetection.agents.JavaAgent();
//        try {
//            jvm.loadAgent(String.valueOf(new URI("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/target/javaAgentLauncher-1.0-SNAPSHOT.jar")));
//            jvm.detach();
//        }catch(java.lang.NullPointerException e){
//            try {
//                String jvmPid_static = jvmName.substring(0, jvmName.indexOf('@'));
//
//                System.out.println(jvmPid_static);
//                jvm = VirtualMachine.attach(jvmPid_static);
//            } catch (AttachNotSupportedException | IOException g) {
//                throw new RuntimeException(g);
//            }
//        }
        System.out.println("This is the classname: " + className);

    }


    }

//public class transfromClass{
//
//        public void  transformClass(
//            String className,Instrumentation instrumentation){
//            Class<?> targetCls=null;
//            ClassLoader targetClassLoader=null;
//            // see if we can get the class using forName
//            try{
//            targetCls=Class.forName(className);
//            targetClassLoader=targetCls.getClassLoader();
//            transform(targetCls,targetClassLoader,instrumentation);
//            return;
//            }catch(Exception ex){
//            LOGGER.error("Class [{}] not found with Class.forName");
//            }
//            // otherwise iterate all loaded classes and find what we want
//            for(Class<?> clazz:instrumentation.getAllLoadedClasses()){
//            if(clazz.getName().equals(className)){
//            targetCls=clazz;
//            targetClassLoader=targetCls.getClassLoader();
//            transform(targetCls,targetClassLoader,instrumentation);
//            return;
//            }
//            }
//            throw new RuntimeException(
//            "Failed to find class ["+className+"]");
//            }
//
//    private static void transform(
//            Class<?> clazz,
//            ClassLoader classLoader,
//            Instrumentation instrumentation){
//            AtmTransformer dt=new AtmTransformer(
//            clazz.getName(),classLoader);
//            instrumentation.addTransformer(dt,true);
//            try{
//            instrumentation.retransformClasses(clazz);
//            }catch(Exception ex){
//            throw new RuntimeException(
//            "Transform failed for: ["+clazz.getName()+"]",ex);
//            }
//            }
//}
