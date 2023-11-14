package com.utece.student.llpdetection.agents;

import agentLoader.abstractAgent;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URISyntaxException;

public class

JavaAgent extends abstractAgent {

    static String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    static String jvmPid = jvmName.substring(0, jvmName.indexOf('@'));
    static VirtualMachine jvm;



    public JavaAgent() throws IOException, AttachNotSupportedException {
        super();
        {

            try {
                this.jvmPid = jvmName.substring(0, jvmName.indexOf('@'));
                String jvmPid1 = jvmPid;
                System.out.println(jvmPid);
                this.jvm = VirtualMachine.attach(jvmPid1);
            } catch (AttachNotSupportedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void premain(
            String agentArgs, Instrumentation inst) throws AttachNotSupportedException, URISyntaxException, AgentLoadException, AgentInitializationException, IOException {

        System.out.println("[Agent] In premain method");
        System.out.println("agentArgs: " + agentArgs);
        String className = "com.utece.student.llpdetection.agents.JavaAgent";
        //com.utece.student.llpdetection.agents.JavaAgent tryThis = new com.utece.student.llpdetection.agents.JavaAgent();
        try {
            jvm.loadAgent(String.valueOf(new URI("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/target/javaAgentLauncher-1.0-SNAPSHOT.jar")));
            jvm.detach();
        }catch(java.lang.NullPointerException e){
            try {
                String jvmPid_static = jvmName.substring(0, jvmName.indexOf('@'));

                System.out.println(jvmPid_static);
                jvm = VirtualMachine.attach(jvmPid_static);
            } catch (AttachNotSupportedException | IOException g) {
                throw new RuntimeException(g);
            }
        }
        System.out.println("This is the classname: " + className);

        transformClass(className, inst);

    }

    public static void premain(String[] stringArgs){

//            System.out.println("[Agent] In premain method");
//            String className = "com.utece.student.llpdetection.App";
//            transformClass(className,inst);
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
