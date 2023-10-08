package com.utece.student.llpdetection.instrumentation;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.utece.student.llpdetection.instrumentation.CLibrary;
public interface CLibrary extends Library {

    CLibrary INSTANCE = (CLibrary) Native.load(("c"), CLibrary.class);

    void printf(String format, Object... args);
}