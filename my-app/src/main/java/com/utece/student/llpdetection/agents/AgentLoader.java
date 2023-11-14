package com.utece.student.llpdetection.agents;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import parallelalgorithms.group9.homework3.ParallelRunners;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static com.utece.student.llpdetection.agentLoader.abstractAgent.transformClass;

public class AgentLoader {
    String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    String jvmPid = jvmName.substring(0, jvmName.indexOf('@'));
    VirtualMachine jvm = VirtualMachine.attach(jvmPid);

    static Class myClass;
    static Constructor constructMyClass;


    public AgentLoader() throws IOException, AttachNotSupportedException, URISyntaxException, AgentLoadException, AgentInitializationException {
        URL resource =AgentLoader.class.getResource("App");
        jvm.loadAgent(String.valueOf(Paths.get(resource.toURI()).toFile()));
        jvm.detach();
    }

    public static Constructor returnConstructor(String className, Class[] parametertypes) throws ClassNotFoundException, NoSuchMethodException {
        myClass = Class.forName(className);
        Class[] types = new Class[parametertypes.length];
        // types[parametertypes.length] = com.utece.student.llpdetection.agents.JavaAgent.class;
        int i = 0;
        for (Class entry : parametertypes) {
            types[i] = entry;
            i++;
        }

        try {
            constructMyClass = myClass.getConstructor(types);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
            Constructor[] likeThis = myClass.getDeclaredConstructors();
            return likeThis[0];
        }

        return constructMyClass;
    }

    public static void run(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Class[] bossClass = new Class[1];
        bossClass[0] = void.class;
        Constructor constructMyClass = (Constructor) returnConstructor(args[0], bossClass);
        ParallelRunners runThis = (ParallelRunners) constructMyClass.newInstance();
        runThis.run(new String[]{""});
    }


    public static void agentmain(
            String agentArgs, Instrumentation inst) {


//            URL resource = AgentLoader.class.getResource("AgentLoader");
//            jvm.loadAgent(String.valueOf(Paths.get(resource.toURI()).toFile()));
//            jvm.detach();

        System.out.println("[Agent] In agentmain method");
        System.out.println(agentArgs);
        String className = "App";
        transformClass(className, inst);
    }
}
