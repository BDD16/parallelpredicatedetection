#include <stdio.h>
#include <stdint.h>
#include "com_utece_student_llpdetection_instrumentation_inlineassembly_NativeAssembly.h"

int print_eip(void){
//    unsigned int esp;
//    printf("about to print eip");
//	asm volatile("movl $0x30, %%eax; movl %%eax, %0" : "=r" (esp));
//	return esp;
    unsigned long long rip_value;

    // Inline assembly to read the RIP register
    asm("movq (%%rip), %0" : "=r" (rip_value));
    return (int)rip_value;
}

long long print_rax(void){
	long long value;

    asm volatile("movq %%rax, %0" : "=r" (value));
	return value;
}

long long print_rbx(void){
	long long value;
    asm volatile("movq %%rbx, %0" : "=r" (value));
	return value;
}

long long print_rcx(void){
	long long value;
    asm volatile("movq %%rcx, %0" : "=r" (value));
	return value;
}

long long print_rdx(void){
	long long value;
    asm volatile("movq %%rdx, %0" : "=r" (value));
	return value;
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


