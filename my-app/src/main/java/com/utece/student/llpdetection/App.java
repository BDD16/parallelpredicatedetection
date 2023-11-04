package com.utece.student.llpdetection;

import com.utece.student.llpdetection.instrumentation.CLibrary;
import com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper;
import com.utece.student.llpdetection.instrumentation.structures.context_t;
import com.utece.studnet.llpdetection.instrumentation.InstrumentBinary;

public class App {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public static void main(String[] args) {
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
        binary_trampoline.invoke(); // after a function turn into a decorator for each function or basic block
        binary_trampoline.customFunction();
        binary_trampoline.customFunction();
    }
}

