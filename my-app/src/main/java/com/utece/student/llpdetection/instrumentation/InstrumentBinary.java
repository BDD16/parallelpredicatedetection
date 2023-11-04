package com.utece.studnet.llpdetection.instrumentation;

import com.utece.student.llpdetection.instrumentation.structures.context_t;
import com.utece.student.llpdetection.instrumentation.Trampoline;

public class InstrumentBinary {

    Trampoline<Object> instrumentThis;
    long rip;
    long rax;
    long rbx;
    long rcx;
    long rdx;
    int window = 0x4242;

    context_t context = new com.utece.student.llpdetection.instrumentation.structures.context_t();
    //StructureReadContext printThis = new StructureReadContext(context);

    //-Essentially you need to hook into a basic block from a binary.

    //-Start with a java program and insert into the class file the basic blocks

    public InstrumentBinary(){
        this.instrumentThis = new Trampoline<>(){
            @java.lang.Override
            public java.lang.Object get() {
                return super.get();
            }

            @java.lang.Override
            public Trampoline<Object> run(){
                long rip;
                long rax;
                long rbx;
                long rcx;
                long rdx;
                rip =  com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_eip();
                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.getcontext(context);
                rax = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_rax();
                rbx = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_rbx();
                rcx = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_rcx();
                rdx = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_rdx();
                rip = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_eip();

                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.printf("RIP 0x%016x\n", rip);
                System.out.println(String.format("RIP in java: 0x%016X", rip));
                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.printf("RAX 0x%016x\n", rax);
                System.out.println(String.format("RAX in java: 0x%016X", rax));
                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.printf("RBX 0x%016x\n", rbx);
                System.out.println(String.format("RBX in java: 0x%016X", rbx));
                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.printf("RCX 0x%016x\n", rcx);
                System.out.println(String.format("RCX in java: 0x%016X", rcx));
                com.utece.student.llpdetection.instrumentation.CLibrary.INSTANCE.printf("RDX 0x%016x\n", rdx);
                System.out.println(String.format("RDX in java: 0x%016X", rdx));
                return this;
            }
        };
        instrumentThis.run();

        System.out.println("Let's attempt to print {context}: ");
        instrumentThis.run();
        System.out.println(context);
        System.out.println("Done Printing Context");
        instrumentThis.run();
        System.out.println(instrumentThis.toString());
        customFunction();
        int x = 2;
        customFunction();
        int y = 1;
        customFunction();
    }

    public void invoke() {
        instrumentThis.run();
    }

    public void customFunction(){
        final long rip2 = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_eip();
        int x = 2 +1;
        x = 54;
        System.out.println(Long.toHexString(rip2));
        final long rip3 = com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegister64Wrapper.instance.print_eip();
        System.out.println(Long.toHexString(rip3));
        return;
    }

}
