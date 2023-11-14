package com.utece.student.llpdetection.instrumentation;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector.Argument;
import com.sun.jdi.connect.LaunchingConnector;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicDebuggerWithProcessAttachAndPID {
    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Usage: DynamicDebuggerWithProcessAttachAndPID <processId> <className>");
//            System.exit(1);
//        }

        String targetProcessId = getCurrentProcessId();
        String targetClassName = "com.parallelalgorithms.group9.homework3.ParallelRunners";

        VirtualMachine vm = launchAndConnect(targetClassName);

        if (vm != null) {
            System.out.println("Connected to VM: " + vm);
        }
    }

    public static VirtualMachine launchAndConnect(String mainClassName) {
        LaunchingConnector connector = findLaunchingConnector();

        if (connector == null) {
            System.err.println("Launching connector not found");
            return null;
        }

        Map<String, Argument> arguments = connector.defaultArguments();
        arguments.get("main").setValue(mainClassName);

        try {
            return connector.launch(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static LaunchingConnector findLaunchingConnector() {
        return Bootstrap.virtualMachineManager().launchingConnectors()
                .stream()
                .filter(connector -> connector.name().equals("com.sun.jdi.CommandLineLaunch"))
                .findFirst()
                .orElse(null);
    }
    public static VirtualMachine attachToProcess(String processId) {
        LaunchingConnector connector = findLaunchingConnector();

        if (connector == null) {
            System.err.println("ProcessAttach connector not found");
            return null;
        }

        Map<String, Argument> arguments = connector.defaultArguments();
        arguments.get("pid").setValue(processId);

        try {
            return connector.launch(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    private static String getCurrentProcessId() {
////        if (Platform.isWindows()) {
////            return getWindowsProcessId();
////        } else if (Platform.isLinux() || Platform.isMac()) {
////            return getUnixProcessId();
////        } else {
////            System.err.println("Unsupported operating system");
////            return null;
////        }
//
//        return getUnixProcessId();
//    }

//    private static String getWindowsProcessId() {
//        return Kernel32.INSTANCE.GetCurrentProcessId() + "";
//    }

    private static String getUnixProcessId() {
        return CLibrary.INSTANCE.getpid() + "";
    }

    // Interface for JNA to access getpid on Unix
    private interface CLibrary extends com.sun.jna.Library {
        CLibrary INSTANCE = com.sun.jna.Native.load("c", CLibrary.class);

        int getpid();
    }

    private static String getCurrentProcessId() {
        //Platform platform = Platform.detect();
//        if (platform.isWindows()) {
//            return getWindowsProcessId();
//        } else if (platform.isLinux() || platform.isMac()) {
//            return getUnixProcessId();
//        } else {
//            System.err.println("Unsupported operating system");
//            return null;
//        }
        return getUnixProcessId();
    }

    private static VirtualMachine attachToProcess(String processId, String className) {
        List<LaunchingConnector> connectors = Bootstrap.virtualMachineManager().launchingConnectors();

        AtomicReference<LaunchingConnector> connector = new AtomicReference<LaunchingConnector>();
        connectors.stream()
                .forEach(c ->{
                    System.out.println(c.name());
                    if(c.name().equals("com.sun.jdi.CommandLineLaunch")) {
                        connector.set(c);
                        }
                    }
                );

        if (connector.get() == null) {
            System.err.println("ProcessAttach connector not found");
            return null;
        }

        Map<String, Argument> arguments = connector.get().defaultArguments();
        arguments.get("pid").setValue(processId);

        // Set the main class for debugging
        arguments.get("main").setValue(className);

        try {
            return connector.get().launch(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
