package com.utece.student.llpdetection;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.utece.student.llpdetection.instrumentation.Sysinfo;
import com.utece.student.llpdetection.instrumentation.CLibrary;
import com.utece.student.llpdetection.instrumentation.structures.context_t;
import com.sun.jna.StructureReadContext;

/** Simple example of JNA interface mapping and usage. with a trampoline */

import java.lang.String;
import com.sun.jna.Library;
class trampoline_thievery<T> {
    public T get() { return null; }

    public String toString(){
        return "Some iteration in LibC";
    }
    public trampoline_thievery<T>  run() {
        return null; }

    T execute() {
        trampoline_thievery<T>  trampoline = this;

        while (trampoline.get() == null) {
            System.out.println("Trampolined function" + new String(this.toString()));
            trampoline = trampoline.run();
        }

        return trampoline.get();
    }
}
public class App {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public static void main(String[] args) {
        context_t context = new context_t();
        //StructureReadContext printThis = new StructureReadContext(context);
        trampoline_thievery x = new trampoline_thievery<>(){
            @java.lang.Override
            public java.lang.Object get() {
               return super.get();
            }

            @java.lang.Override
            public trampoline_thievery<java.lang.Object> run(){
                CLibrary.INSTANCE.getcontext(context);
                CLibrary.INSTANCE.printf("Hello, World %s\n",context );
                return this;
            }
        };
        for (int i=0;i < args.length;i++) {
            //x.execute();
            //return new trampoline_thievery<CLibrary>(){public CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
        //x.execute();
        x.run();
        System.out.println("Let's attempt ot print {context}");
        System.out.println(context);
    }
}

