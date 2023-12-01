package com.utece.student.llpdetection.agents;

import com.sun.tools.attach.VirtualMachine;
import com.utece.student.llpdetection.agentLoader.abstractAgent;
import com.utece.student.llpdetection.transformers.jvmTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.security.ProtectionDomain;

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
                //premain(agentArgs, inst);
                System.out.println("[Agent] In agentmain method");
                String className = "parallelalgorithms.group9.homework3.ParallelRunners";
                //com.utece.student.llpdetection.transformers.Transformer.transform(parallelalgorithms.group9.homework3.ParallelRunners.class.getComponentType(), inst.getClass().getClassLoader(), inst);
                System.out.println(inst.isRetransformClassesSupported());
                java.util.ArrayList<Class> targetedInstrumentation = new java.util.ArrayList<Class>() ;
                Class[] classArray = inst.getAllLoadedClasses();
                for(Class clazz: classArray){
                    if(clazz.getName().equals("parallelalgorithms.group9.homework3.ParallelRunners")){
                        System.out.println("Instrumenting Class: " + clazz.getName());
                        targetedInstrumentation.add(clazz);
                    }
                }
                Class[] edited = new Class[targetedInstrumentation.size()];
                for(int i = 0; i < targetedInstrumentation.size(); i ++){
                    edited[i] = targetedInstrumentation.get(i);
                }
                System.out.println(edited);
                inst.addTransformer(new jvmTransformer());
                inst.retransformClasses(edited);
                //Class<?> targetClass = inst.getClass().forName(className);
                inst.getAllLoadedClasses().getClass().forName(className).getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});

//                Arrays.stream(classArray).forEach(c -> {
//                    Class<?> targetClass = null;
//                    try {
//
//                        targetClass = inst.getClass().forName(className);
//                    } catch (ClassNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                    if( c == targetClass){
//
//                        try {
//                            c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
//                        } catch (IllegalAccessException e) {
//                            throw new RuntimeException(e);
//                        } catch (InvocationTargetException e) {
//                            throw new RuntimeException(e);
//                        } catch (NoSuchMethodException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });

            }catch(Exception e){
                System.out.println(e);
                System.out.println("DANGER DANGER DANGER");
            }
    }

    public static byte[] transform(
            Class<?> clazz,
            ClassLoader classLoader,
            Instrumentation instrumentation) {
        if(clazz != null && classLoader !=null) {
                if(t ==null) {
                    t = new jvmTransformer(
                            clazz.getName(), classLoader);
                }
                else{
                    t.setTargetClassName(clazz.getName());
                }
            instrumentation.addTransformer(t);

            System.out.println("retransforming class: " + clazz);
            try {
                byte[] result = new byte[10000];
                ProtectionDomain public_identity = clazz.getProtectionDomain();
                result = t.getBytesFromTransform(classLoader, clazz.getName(), clazz,public_identity, result);
                System.out.println(result);
                System.out.println("[TRANSFORM] LETS ROCK!");
                instrumentation.retransformClasses(clazz);
                System.out.println("[TRANSFORM] Done Retransforming Classes of clazz: " + clazz.toString());
                return result;
            }catch(UnmodifiableClassException e){
                System.out.println(e);
            }
        }
        return null;
    }

    public static void premain(
            String agentArgs, Instrumentation inst) {

        System.out.println("[Agent] In premain method");
        System.out.println("agentArgs: " + agentArgs);
        String className = "parallelalgorithms.group9.homework3.ParallelRunners";

        try {
            //System.out.println("[Agent] In agentmain method");
            //String className = "parallelalgorithms.group9.homework3.ParallelRunners";
            com.utece.student.llpdetection.transformers.Transformer.transform(parallelalgorithms.group9.homework3.ParallelRunners.class.getComponentType(), inst.getClass().getClassLoader(), inst);
            System.out.println(inst.isRetransformClassesSupported());

            Class[] classArray = inst.getAllLoadedClasses();
            //System.out.println(Arrays.toString(classArray));
            try {
                inst.addTransformer(new jvmTransformer(), true);
            }catch(Exception e){
                System.out.println(e);
                Throwable cause = e.getCause();
                if (cause != null){
                    cause.printStackTrace();
                }
            }

            for(Class class_n_day: classArray){
                Class<?> targetClass = Class.forName(class_n_day.getClass().getName());
                try {
                        inst.retransformClasses(targetClass);
                        System.out.println(targetClass.getName() + " hopefully transformed");
                        transform(targetClass, targetClass.getClassLoader(), inst);
                    } catch (UnmodifiableClassException ex) {
                    throw new RuntimeException(ex);
                }
                //c.getClass().getEnclosingClass().getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
                }
            } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
//            Arrays.stream(new Object[]{Arrays.stream(classArray).collect(Collectors.toList())}).forEach(c -> {
//                Class<?> targetClass = null;
//                try {
//                    targetClass = Class.forName(c.getClass().getName());
//                } catch (ClassNotFoundException e) {
//                    System.out.println(c + " : cannot be transformed so skipping");
//                }
//
//                    try {
//                        if(!targetClass.getName().contains("javasist")) {
//                            transform(targetClass, ClassLoader.getSystemClassLoader(), inst);
//                        }
//                        //c.getClass().getEnclosingClass().getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
//                    } catch(java.lang.ClassCastException e){
//                        System.out.println(className + " : cannot be transformed so skipping");
//                    }
//                    catch(java.lang.NullPointerException e){
//                        System.out.println("skipping a null return");
//                    }
//                    catch(java.lang.NoClassDefFoundError e){
//                        System.out.println("continue on!");
//                    }
//            });

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
