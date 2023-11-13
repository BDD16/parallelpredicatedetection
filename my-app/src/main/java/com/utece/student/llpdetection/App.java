package com.utece.student.llpdetection;

import com.utece.student.llpdetection.instrumentation.InstrumentBinary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public App(){
    }

    public static void run(String[] args){
        LinkedList<Integer> local_overwrite = new LinkedList<Integer>(Collections.singleton(3));
        InstrumentBinary binary_trampoline = new InstrumentBinary();
        try{
            int i = 0;
            try {
                while (i < 10) {
                    try {
                        binary_trampoline.customFunction();
                        AtomicInteger j = new AtomicInteger(3);
                        local_overwrite.parallelStream().forEach(number -> {
                            System.out.println("{callee or RIP at this point before write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            try {
                                local_overwrite.set(j.get(), number);
                            }catch(Exception e){
                                System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            }
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            j.getAndIncrement();
                        });
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        binary_trampoline.customFunction();
                    }catch(Exception e){
                        System.out.println(i);
                        System.out.println("{callee of 1: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee of 0: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        //System.out.println("{callee of 2: " + Long.toHexString(binary_trampoline.getLrAtSpecified(2)) + "}");
                        binary_trampoline.customFunction();
                        binary_trampoline.invoke();
                        i++;
                    }
                    i ++;
                }
            }catch(Exception e ){
                binary_trampoline.customFunction();
                binary_trampoline.invoke();
            }
        } catch(Exception e){
            binary_trampoline.customFunction();
            binary_trampoline.invoke();
        }
        binary_trampoline.customFunction();

    }

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public static void main(String[] args) {

        LinkedList<Integer> local_overwrite = new LinkedList<Integer>(Collections.singleton(3));
//        int window = 0x4242;
//
//        context_t context = new context_t();
//        //StructureReadContext printThis = new StructureReadContext(context);
//        trampoline_thievery<Object> x = new trampoline_thievery<>(){
//            @java.lang.Override
//            public java.lang.Object get() {
//               return super.get();
//            }
//
//            @java.lang.Override
//            public trampoline_thievery<java.lang.Object> run(){
//                long rip;
//                long rax;
//                long rbx;
//                long rcx;
//                long rdx;
//                CLibrary.INSTANCE.getcontext(context);
//                rax = NativeAssemblyRegister64Wrapper.instance.print_rax();
//                rbx = NativeAssemblyRegister64Wrapper.instance.print_rbx();
//                rcx = NativeAssemblyRegister64Wrapper.instance.print_rcx();
//                rdx = NativeAssemblyRegister64Wrapper.instance.print_rdx();
//                rip = NativeAssemblyRegister64Wrapper.instance.print_eip();
//
//                CLibrary.INSTANCE.printf("RIP 0x%016x\n", rip);
//                System.out.println(String.format("RIP in java: 0x%016X", rip));
//                CLibrary.INSTANCE.printf("RAX 0x%016x\n", rax);
//                System.out.println(String.format("RAX in java: 0x%016X", rax));
//                CLibrary.INSTANCE.printf("RBX 0x%016x\n", rbx);
//                System.out.println(String.format("RBX in java: 0x%016X", rbx));
//                CLibrary.INSTANCE.printf("RCX 0x%016x\n", rcx);
//                System.out.println(String.format("RCX in java: 0x%016X", rcx));
//                CLibrary.INSTANCE.printf("RDX 0x%016x\n", rdx);
//                System.out.println(String.format("RDX in java: 0x%016X", rdx));
//                //NativeAssemblyRegisterWrapper.instance.print_rax();
//                //NativeAssemblyRegisterWrapper.printAllRegisters();
//                return this;
//            }
//        };
//        for (int i=0;i < args.length;i++) {
//            int j = 0;
//            //x.execute();
//            //return new trampoline_thievery<CLibrary>(){public CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
//        }
//        //x.execute();
//        x.run();
//        System.out.println("Let's attempt to print {context}: ");
//        x.run();
//        System.out.println(context);
//        x.run();
        InstrumentBinary binary_trampoline = new InstrumentBinary();
        /*
//        binary_trampoline.invoke(); // after a function turn into a decorator for each function or basic block
//        binary_trampoline.customFunction();
//        binary_trampoline.customFunction();
//        binary_trampoline.customFunction();
//        binary_trampoline.invoke();
        try{
            int i = 0;
            try {
                while (i < 10) {
                    try {
                        binary_trampoline.customFunction();
                        AtomicInteger j = new AtomicInteger(3);
                        local_overwrite.parallelStream().forEach(number -> {
                            System.out.println("{callee or RIP at this point before write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            try {
                                local_overwrite.set(j.get(), number);
                            }catch(Exception e){
                                System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            }
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            j.getAndIncrement();
                        });
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        binary_trampoline.customFunction();
                    }catch(Exception e){
                        System.out.println(i);
                        System.out.println("{callee of 1: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee of 0: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        //System.out.println("{callee of 2: " + Long.toHexString(binary_trampoline.getLrAtSpecified(2)) + "}");
                        binary_trampoline.customFunction();
                        binary_trampoline.invoke();
                        i++;
                    }
                    i ++;
                }
            }catch(Exception e ){
                binary_trampoline.customFunction();
                binary_trampoline.invoke();
            }
        } catch(Exception e){
            binary_trampoline.customFunction();
            binary_trampoline.invoke();
        }
        binary_trampoline.customFunction();
        */

        /*
         *
         *
         *
         */
        System.out.println("BEGIN EXPERIMENT:");
        final long lr = binary_trampoline.getLrAtSpecified(1);
        final long again_lr = binary_trampoline.getLrAtSpecified(1);
        try{
            final long another_lr = binary_trampoline.getRip();;
            final long another_eip = binary_trampoline.getRip();
            System.out.println("another_lr: " + another_lr);
            System.out.println("another_eip: " + another_eip);
        }catch(Exception e){
            long x = binary_trampoline.getLrAtSpecified(0);
            System.out.println("X" +  x);

        }
        System.out.println(lr);
        System.out.println(again_lr);
        long eip = binary_trampoline.getRip();
        System.out.println(eip);
        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");


    }

    public void run() {
        LinkedList<Integer> local_overwrite = new LinkedList<Integer>(Collections.singleton(3));
        InstrumentBinary binary_trampoline = new InstrumentBinary();
        try{
            int i = 0;
            try {
                while (i < 10) {
                    try {
                        binary_trampoline.customFunction();
                        AtomicInteger j = new AtomicInteger(3);
                        local_overwrite.parallelStream().forEach(number -> {
                            System.out.println("{callee or RIP at this point before write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            try {
                                local_overwrite.set(j.get(), number);
                            }catch(Exception e){
                                System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                            }
                            System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                            j.getAndIncrement();
                        });
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee or RIP at this point after write: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        binary_trampoline.customFunction();
                    }catch(Exception e){
                        System.out.println(i);
                        System.out.println("{callee of 1: " + Long.toHexString(binary_trampoline.getLrAtSpecified(1)) + "}");
                        System.out.println("{callee of 0: " + Long.toHexString(binary_trampoline.getLrAtSpecified(0)) + "}");
                        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
                        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
                        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
                        System.out.println("{callee of rip: " + Long.toHexString(binary_trampoline.getRip()) + "}");
                        binary_trampoline.customFunction();
                        binary_trampoline.invoke();
                        i++;
                    }
                    i ++;
                }
            }catch(Exception e ){
                binary_trampoline.customFunction();
                binary_trampoline.invoke();
            }
        } catch(Exception e){
            binary_trampoline.customFunction();
            binary_trampoline.invoke();
        }
        binary_trampoline.customFunction();


    }
}

