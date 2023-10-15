package com.utece.student.llpdetection.instrumentation.inlineassembly;

import com.sun.jna.Library;

import com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssembly;
public class NativeAssemblyRegisterWrapper{
    static {
        System.loadLibrary("assembly.1.0");
    }

    static NativeAssembly nativeAssembly = new NativeAssembly();

    public NativeAssemblyRegisterWrapper(){}


    public static void printAllRegisters(){
        nativeAssembly.print_rip();
        nativeAssembly.print_rax();
        nativeAssembly.print_rbx();
        nativeAssembly.print_rcx();
        nativeAssembly.print_rdx();
    }

}