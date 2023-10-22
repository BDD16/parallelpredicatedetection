#include <stdio.h>
#include <stdint.h>
#include "com_utece_student_llpdetection_instrumentation_inlineassembly_NativeAssembly.h"

long print_eip(void){
//    unsigned int esp;
//    printf("about to print eip");
//	asm volatile("movl $0x30, %%eax; movl %%eax, %0" : "=r" (esp));
//	return esp;
    unsigned long long rip_value;
    long result;

    // Inline assembly to read the RIP register
    asm volatile("pushq (%%rip); popq %0" : "=r" (result));
    return result;
}

long print_rax(void){
	long result;
    asm volatile("pushq %%rax; popq %0" : "=r" (result));
    return result;
}

long print_rbx(void){
	long result;
        asm volatile("pushq %%rbx; popq %0" : "=r" (result));
        return result;
}

long print_rcx(void){
	long result;
    asm volatile("pushq %%rcx; popq %0" : "=r" (result));
    return result;
}

long print_rdx(void){
	long result;
    asm volatile("pushq %%rdx; popq %0" : "=r" (result));
    return result;
}

JNIEXPORT void JNICALL Java_NativeAssembly_print_eip(JNIEnv *env, jobject obj) {
    print_eip();
}

JNIEXPORT void JNICALL Java_NativeAssembly_print_rax(JNIEnv *env, jobject obj) {
    print_rax();
}

JNIEXPORT void JNICALL Java_NativeAssembly_print_rbx(JNIEnv *env, jobject obj) {
    print_rbx();
}

JNIEXPORT void JNICALL Java_NativeAssembly_print_rcx(JNIEnv *env, jobject obj) {
    print_rcx();
}
JNIEXPORT void JNICALL Java_NativeAssembly_print_rdx(JNIEnv *env, jobject obj) {
    print_rdx();
}


