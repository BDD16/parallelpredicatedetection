package com.utece.student.llpdetection.instrumentation.inlineassembly;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssembly;
public interface NativeAssemblyRegisterWrapper extends Library{

    NativeAssemblyRegisterWrapper instance = (NativeAssemblyRegisterWrapper) Native.load(("/Users/blake/.m2/repository/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly/1.0/libassembly-1.0.dylib"), NativeAssemblyRegisterWrapper.class);
    //static NativeAssembly nativeAssembly = new NativeAssembly();



    public void print_eip();
    public void print_rax();
    public void print_rbx();
    public void print_rcx();
    public void print_rdx();
//    public static void printAllRegisters(){
//        instance.print_rip();
//        instance.print_rax();
//        instance.print_rbx();
//        instance.print_rcx();
//        instance.print_rdx();
//    }

}