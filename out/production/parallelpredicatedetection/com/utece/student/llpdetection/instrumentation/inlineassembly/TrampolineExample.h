#include <jni.h>

JNIEXPORT long JNICALL Java_TrampolineExample_returnCurrentRip(JNIEnv *env, jobject obj);
JNIEXPORT long JNICALL Java_TrampolineExample_invokeTrampoline(JNIEnv *env, jobject obj);
long independentRip(void);
