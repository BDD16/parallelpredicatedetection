#include <stdio.h>
#include <stdint.h>
#include "NativeAssembly.h"

void print_eip(void){
    unsigned int esp;
    printf("about to print eip");
	asm volatile("movl $0x30, %%eax; movl %%eax, %%esp" : "=r" (esp));
	printf("Value in RIP: %08x\n", esp);
}

void print_rax(void){
	long long value;

    asm volatile("movq %%rax, %0" : "=r" (value));
	printf("Value in RAX: %lld\n", value);
}

void print_rbx(void){
	long long value;
    asm volatile("movq %%rbx, %0" : "=r" (value));
	printf("Value in RBX: %lld\n", value);
}

void print_rcx(void){
	long long value;
    asm volatile("movq %%rcx, %0" : "=r" (value));
	printf("Value in RCX: %lld\n", value);
}

void print_rdx(void){
	long long value;
    asm volatile("movq %%rdx, %0" : "=r" (value));
	printf("Value in RDX: %lld\n", value);
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


