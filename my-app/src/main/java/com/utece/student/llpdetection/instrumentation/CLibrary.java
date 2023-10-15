package com.utece.student.llpdetection.instrumentation;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.utece.student.llpdetection.instrumentation.structures.context_t;
public interface CLibrary extends Library {

    CLibrary INSTANCE = (CLibrary) Native.load(("c"), CLibrary.class);

    void printf(String format, Object... args);

    /**
     * sysinfo() provides a simple way of getting overall system statistics.
     * This is more portable than reading /dev/kmem.
     *
     * @param info
     *            A Sysinfo structure which will be populated
     * @return On success, zero is returned. On error, -1 is returned, and errno
     *         is set appropriately.
     */
    int getcontext(context_t info);

}