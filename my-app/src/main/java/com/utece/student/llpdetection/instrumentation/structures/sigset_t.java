package com.utece.student.llpdetection.instrumentation.structures;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import com.sun.jna.FromNativeContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.sun.jna.Pointer;

@FieldOrder({"SIGABRT", "SIGALRM", "SIGBUS", "SIGCHLD", "SIGCONT",
        "SIGFPE", "SIGHUP", "SIGILL", "SIGINT", "SIGKILL", "SIGPIPE",
        "SIGPOLL", "SIGPROF", "SIGQUIT", "SIGSEGV", "SIGSTOP", "SIGSYS",
        "SIGTERM", "SIGTRAP", "SIGTSTP", "SIGTTIN", "SIGTTOU", "SIGURG",
        "SIGUSR1", "SIGUSR2", "SIGVTALRM", "SIGXCPU", "SIGXFSZ"})
public class sigset_t extends Structure {

    public sigset_t(){
        super();
    }

    private static final int PADDING_SIZE = 20 - 2 * NativeLong.SIZE - 4;

    /**
     * the actual sigterm events as in
     */
    public long SIGABRT; // Total usable main memory size
    public long SIGALRM; // Available memory size
    public long SIGBUS; // Amount of shared memory
    public long SIGCHLD; // Memory used by buffers
    public long SIGCONT; // swap space still available
    public long SIGFPE; // Total usable main memory size
    public long SIGHUP; // Available memory size
    public long SIGILL; // Amount of shared memory
    public long SIGINT; // Memory used by buffers
    public long SIGKILL; // Total swap space size
    public long SIGPIPE; // swap space still available
    public long SIGQUIT; // Total usable main memory size
    public long SIGSEGV; // Available memory size
    public long SIGSTOP; // Amount of shared memory
    public long SIGTERM; // Memory used by buffers
    public long SIGTSTP; // Total usable main memory size
    public long SIGTTIN; // Available memory size
    public long SIGTTOU; // Amount of shared memory
    public long SIGUSR1; // Memory used by buffers
    public long SIGUSR2; // Total swap space size
    public long SIGPOLL; // swap space still available
    public long SIGPROF; // Total usable main memory size
    public long SIGSYS; // Available memory size
    public long SIGTRAP; // Amount of shared memory
    public long SIGURG; // Memory used by buffers
    public long SIGVTALRM; // Total swap space size
    public long SIGXCPU; // swap space still available
    public long SIGXFSZ; // Total usable main memory size

    public byte[] _f = new byte[PADDING_SIZE];


    @Override
    protected List<Field> getFieldList() {
        List<Field> fields = new ArrayList<Field>(super.getFieldList());
        if (PADDING_SIZE == 0) {
            Iterator<Field> fieldIterator = fields.iterator();
            while (fieldIterator.hasNext()) {
                Field field = fieldIterator.next();
                if ("_f".equals(field.getName())) {
                    fieldIterator.remove();
                }
            }
        }
        return fields;
    }
}