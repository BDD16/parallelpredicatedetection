package com.utece.student.llpdetection.instrumentation;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Sysinfo extends Structure {
    private static final int PADDING_SIZE = 20 - 2 * NativeLong.SIZE - 4;

    public NativeLong uptime; // Seconds since boot
    // 1, 5, and 15 minute load averages, divide by 2^16
    public NativeLong[] loads = new NativeLong[3];
    public NativeLong totalram; // Total usable main memory size
    public NativeLong freeram; // Available memory size
    public NativeLong sharedram; // Amount of shared memory
    public NativeLong bufferram; // Memory used by buffers
    public NativeLong totalswap; // Total swap space size
    public NativeLong freeswap; // swap space still available
    public short procs; // Number of current processes
    public NativeLong totalhigh; // Total high memory size
    public NativeLong freehigh; // Available high memory size
    public int mem_unit; // Memory unit size in bytes
    // Padding to 64 bytes
    public byte[] _f = new byte[PADDING_SIZE];

    /*
     * getFieldList and getFieldOrder are overridden because PADDING_SIZE
     * might be 0 - that is a GCC only extension and not supported by JNA
     *
     * The dummy field at the end of the structure is just padding and so if
     * the field is the zero length array, it is stripped from the fields
     * and field order.
     */
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

    @Override
    protected List<String> getFieldOrder() {
        List<String> fieldOrder = new ArrayList<String>(super.getFieldOrder());
        if (PADDING_SIZE == 0) {
            fieldOrder.remove("_f");
        }
        return fieldOrder;
    }
}