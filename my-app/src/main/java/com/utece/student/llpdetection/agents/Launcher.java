package com.utece.student.llpdetection.agents;

import agents.AgentLoader;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.utece.student.llpdetection.App;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.net.URISyntaxException;

public class Launcher {

    static com.utece.student.llpdetection.agents.JavaAgent agentForLauncher;
    public void Launcher(String[] args) throws Exception {
        agentForLauncher = new com.utece.student.llpdetection.agents.JavaAgent();
    }

    public static void premain(
            String agentArgs, Instrumentation inst) throws IOException, AttachNotSupportedException, URISyntaxException, AgentLoadException, AgentInitializationException {

        System.out.println("[Agent] In premain method");
        System.out.println("agentArgs: " + agentArgs);
        String className = "com.utece.student.llpdetection.App";
        //com.utece.student.llpdetection.agents.JavaAgent tryThis = new com.utece.student.llpdetection.agents.JavaAgent();
        agentForLauncher.jvm.loadAgent(String.valueOf(new URI("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/target/javaAgentLauncher-1.0-SNAPSHOT.jar")));
        agentForLauncher.jvm.detach();
        agentForLauncher.transformClass(className, inst);

    }
    public static void main(String[] args) throws Exception {
        if(args[0].equals("LLP-App")) {
            new App().run(args);
        } else if(args[0].equals("LoadAgent")) {
            AgentLoader.run(args);
        }
    }
}
