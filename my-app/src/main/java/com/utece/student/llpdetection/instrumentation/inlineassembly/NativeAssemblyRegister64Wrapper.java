package com.utece.student.llpdetection.instrumentation.inlineassembly;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface NativeAssemblyRegister64Wrapper extends Library{

    NativeAssemblyRegister64Wrapper instance = (NativeAssemblyRegister64Wrapper) Native.load(("/Users/blake/.m2/repository/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly64/1.1/libassembly64-1.1.dylib"), NativeAssemblyRegister64Wrapper.class);
    //static NativeAssembly = new NativeAssembly();



    public long print_eip();
    public long print_lr_at_callee(int callee);
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