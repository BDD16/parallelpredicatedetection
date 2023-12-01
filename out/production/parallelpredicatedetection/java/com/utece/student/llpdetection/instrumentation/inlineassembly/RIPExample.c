#include <jni.h>

JNIEXPORT jlong JNICALL Java_RIPExample_getRIP(JNIEnv *env, jobject obj) {
    jlong rip;

    // Inline assembly to read the RIP register
    asm("movq $0, %0\n\t"
        "call 1f\n\t"
        "1: popq %0"
        : "=r" (rip));

    return rip;
}

