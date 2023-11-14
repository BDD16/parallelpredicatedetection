#include <stdio.h>
#include <jni.h>
#include "./TrampolineExample.h"

JNIEXPORT long JNICALL Java_TrampolineExample_invokeTrampoline(JNIEnv *env, jobject obj) {
    long returnAddress = independentRip();
    printf("Return Address: %lx", returnAddress);
    return returnAddress;
}
long independentRip(void){
    void *returnAddress = __builtin_return_address(0);
    int x = 0;
    x ++;
    return (*((long*) returnAddress));
}

JNIEXPORT long JNICALL Java_TrampolineExample_returnCurrentRip(JNIEnv *env, jobject obj){
    return independentRip();
}

JNIEXPORT void JNICALL Java_TrampolineExample_invokeReturnAddress(JNIEnv *env, jobject obj){
    printf("Return Address: %lx", (long) independentRip());
}
