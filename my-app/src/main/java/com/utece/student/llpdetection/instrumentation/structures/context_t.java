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
import com.utece.student.llpdetection.instrumentation.structures.sigset_t;
import com.utece.student.llpdetection.instrumentation.structures.stack_t;

/**
 * typedef struct ucontext_t {
 *                struct ucontext_t *uc_link;
 *                sigset_t          uc_sigmask;
 *                stack_t           uc_stack;
 *                mcontext_t        uc_mcontext;
 *                ...
 *            } ucontext_t;
 */

@FieldOrder({"uc_context", "uc_sigmask", "uc_stack", "mcontext_ptr"})
public class context_t extends Structure {


    private static final int PADDING_SIZE = 20 - 2 * NativeLong.SIZE - 4;

    public Pointer uc_context;
    public sigset_t uc_sigmask;
    public stack_t uc_stack;
    public Pointer mcontext_ptr;

    public byte[] _f = new byte[PADDING_SIZE];

    public context_t(){
        super();
    }

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

//class StructureReadContext extends FromNativeContext {
//
//    private Structure structure;
//    private Field field;
//    StructureReadContext(Structure struct, Field field) {
//        super(field.getType());
//        this.structure = struct;
//        this.field = field;
//    }
//    /** Get the {@link Structure} the field is a member of. */
//    public Structure getStructure() { return structure; }
//    /** Get the {@link Field} being read from native memory. */
//    public Field getField() { return field; }
//}