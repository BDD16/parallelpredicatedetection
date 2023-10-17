package com.utece.student.llpdetection;

import com.utece.student.llpdetection.instrumentation.CLibrary;
import com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssemblyRegisterWrapper;
import com.utece.student.llpdetection.instrumentation.structures.context_t;

import java.lang.annotation.Native;

class trampoline_thievery<T> {
    public T get() { return null; }

    public String toString(){
        return "Some iteration in LibC";
    }
    public trampoline_thievery<T>  run() {
        return null; }

    T execute() {
        trampoline_thievery<T>  trampoline = this;

        while (trampoline.get() == null) {
            System.out.println("Trampolined function" + new String(this.toString()));
            trampoline = trampoline.run();
        }

        return trampoline.get();
    }
}
public class App {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public static void main(String[] args) {
        int window = 0x4242;

        context_t context = new context_t();
        //StructureReadContext printThis = new StructureReadContext(context);
        trampoline_thievery<Object> x = new trampoline_thievery<>(){
            @java.lang.Override
            public java.lang.Object get() {
               return super.get();
            }

            @java.lang.Override
            public trampoline_thievery<java.lang.Object> run(){
                int rip;
                long rax;
                long rbx;
                long rcx;
                long rdx;
                CLibrary.INSTANCE.getcontext(context);
                rax = NativeAssemblyRegisterWrapper.instance.print_rax();
                rbx = NativeAssemblyRegisterWrapper.instance.print_rbx();
                rcx = NativeAssemblyRegisterWrapper.instance.print_rcx();
                rdx = NativeAssemblyRegisterWrapper.instance.print_rdx();
                rip = NativeAssemblyRegisterWrapper.instance.print_eip();

                CLibrary.INSTANCE.printf("RIP %08x\n", rip);
                CLibrary.INSTANCE.printf("RAX %08x\n", rax);
                CLibrary.INSTANCE.printf("RBX %08x\n", rbx);
                CLibrary.INSTANCE.printf("RCX %08x\n", rcx);
                CLibrary.INSTANCE.printf("RDX %08x\n", rdx);
                //NativeAssemblyRegisterWrapper.instance.print_rax();
                //NativeAssemblyRegisterWrapper.printAllRegisters();
                return this;
            }
        };
        for (int i=0;i < args.length;i++) {
            int j = 0;
            //x.execute();
            //return new trampoline_thievery<CLibrary>(){public CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
        //x.execute();
        x.run();
        System.out.println("Let's attempt ot print {context}");
        System.out.println(context);
    }
}

