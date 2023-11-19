package com.utece.student.llpdetection.agents;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.utece.student.llpdetection.instrumentation.DynamicDebuggerWithProcessAttachAndPID.getCurrentProcessId;
import static com.utece.student.llpdetection.instrumentation.DynamicDebuggerWithProcessAttachAndPID.launchAndConnect;
public class Launcher {

    static com.utece.student.llpdetection.transformers.jvmTransformer x;

    static com.utece.student.llpdetection.agents.JavaAgent agentForLauncher;
    public void Launcher(String[] args) throws Exception {
        agentForLauncher = new com.utece.student.llpdetection.agents.JavaAgent();
    }

    public static void premain(
            String agentArgs, Instrumentation inst) throws IOException, AttachNotSupportedException, URISyntaxException, AgentLoadException, AgentInitializationException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        System.out.println("[Agent] In premain method");
        System.out.println("agentArgs: " + agentArgs);
        String className = "com.parallelalgorithms.group9.homework3.ParallelRunners";
        //com.utece.student.llpdetection.agents.JavaAgent tryThis = new com.utece.student.llpdetection.agents.JavaAgent();
        agentForLauncher.jvm.loadAgent(String.valueOf(new URI("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/target/javaAgentLauncher-1.0-SNAPSHOT.jar")));
        agentForLauncher.jvm.detach();
//        agentForLauncher.transformClass(className, inst);
//        Class<?> targetClass = Class.forName(className);
//        targetClass.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});

    }
    public static void main(String[] args) throws Exception {
        if(true) {
            String targetProcessId = getCurrentProcessId();
            String targetClassName = "parallelalgorithms.group9.homework3.ParallelRunners";

            com.sun.tools.attach.VirtualMachine vm = launchAndConnect(targetClassName);
            assert vm != null;
            //vm.loadAgent("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/target/javaAgentLauncher-1.0-SNAPSHOT.jar", null);
            // start management agent
            // detach
            vm.detach();
//            if (vm != null) {
//                System.out.println("Connected to VM: " + vm);
//
//                vm.
//                vm.suspend();
//                vm.description();
//                System.out.println(vm.toString());
//            }
            //vm.detach();

        } else if(false) {
            com.utece.student.llpdetection.agents.AgentLoader.run(new String[]{"parallelalgorithms.group9.homework3.ParallelRunners"});
        }
        System.out.println("MainProgram of Launcher: Done");
    }
}
