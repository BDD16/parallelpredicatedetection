package com.utece.student.llpdetection.instrumentation.examples;

import java.lang.String;
import com.sun.jna.Library;
class trampoline_thievery_stackoverflow_deletesoon<T> {
        public T get() { return null; }

        public String toString(){
            return "Some iteration in LibC";
        }
        public com.utece.student.llpdetection.instrumentation.examples.trampoline_thievery_stackoverflow_deletesoon<T> run() {
            System.out.println("in Run Command" + new String(this.toString()));
            return null; }

            T execute() {
                com.utece.student.llpdetection.instrumentation.examples.trampoline_thievery_stackoverflow_deletesoon<T> trampoline = this;

                while (trampoline.get() == null) {
                    System.out.println("Trampolined function" + new String(this.toString()));
                    trampoline = trampoline.run();
                }

                return trampoline.get();
        }
}