package com.utece.student.llpdetection.instrumentation.structures;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * void     *ss_sp       Stack base or pointer.
 * size_t    ss_size     Stack size.
 * int       ss_flags    Flags.
 */

@FieldOrder({"ss_sp", "ss_size", "ss_flags"})
public class stack_t extends Structure{
    private static final int PADDING_SIZE = 20 - 2 * NativeLong.SIZE - 4;

    public NativeLong ss_sp; // stack pointer
    public NativeLong ss_size; // stack size
    public int ss_flags; // flags

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