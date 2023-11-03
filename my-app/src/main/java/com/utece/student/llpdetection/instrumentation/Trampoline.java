package com.utece.student.llpdetection.instrumentation;

public class Trampoline<T> {
    public T get() {
        return null;
    }

    public String toString() {
        return "Some iteration in LibC";
    }

    public Trampoline<T> run() {
        return null;
    }

    T execute() {
        Trampoline<T> trampoline = this;

        while (trampoline.get() == null) {
            System.out.println("Trampolined function" + new String(this.toString()));
            trampoline = trampoline.run();
        }

        return trampoline.get();
    }
}
