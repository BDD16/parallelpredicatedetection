#include <stdio.h>
#include <jni.h>

JNIEXPORT void JNICALL Java_TrampolineExample_invokeTrampoline(JNIEnv *env, jobject obj) {
    void *returnAddress = __builtin_return_address(0);

    printf("Return address: %p\n", returnAddress);
}
