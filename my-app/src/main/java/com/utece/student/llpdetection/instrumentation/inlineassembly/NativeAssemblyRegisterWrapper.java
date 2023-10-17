package com.utece.student.llpdetection.instrumentation.inlineassembly;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface NativeAssemblyRegisterWrapper extends Library{

    NativeAssemblyRegisterWrapper instance = (NativeAssemblyRegisterWrapper) Native.load(("/Users/blake/.m2/repository/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly/1.0/libassembly-1.0.dylib"), NativeAssemblyRegisterWrapper.class);
    //static NativeAssembly nativeAssembly = new NativeAssembly();



    public int print_eip();
    public long print_rax();
    public long print_rbx();
    public long print_rcx();
    public long print_rdx();
    public static void printAllRegisters(){
        instance.print_eip();
        instance.print_rax();
        instance.print_rbx();
        instance.print_rcx();
        instance.print_rdx();
    }

}