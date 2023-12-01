#include <stdio.h>
#include <stdint.h>
#include "NativeAssembly_64.h"

long print_eip(void){
// input lr_stack_frame is starting at 0 to whatever is the callee stack frame 0 is current stack frame
// 1 is the callee stack frame, etc.
//    unsigned int esp;
//    printf("about to print eip");
//	asm volatile("movl $0x30, %%eax; movl %%eax, %0" : "=r" (esp));
//	return esp;
    long result;

    // Inline assembly to read the RIP register
     asm volatile("pushq (%%rip); popq %0" : "=r" (result));
    //result = ((long)__builtin_extract_return_addr (__builtin_return_address(lr_stack_frame)));
    return result;
}

long print_lr_at_callee(int callee){
    long result;

    if(callee == 0){
        result = ((long)__builtin_extract_return_addr (__builtin_return_address(0)));
    }
    else if(callee == 1){
        result = ((long)__builtin_extract_return_addr (__builtin_return_address(1)));
    }
    else if(callee == 2){
        result = ((long)__builtin_extract_return_addr (__builtin_return_address(2)));
    }

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

JNIEXPORT long JNICALL Java_NativeAssembly_print_eip(JNIEnv *env, jobject obj) {
    return print_eip();
}

JNIEXPORT long JNICALL Java_NativeAssembly_print_rax(JNIEnv *env, jobject obj) {
    return print_rax();
}

JNIEXPORT long  JNICALL Java_NativeAssembly_print_rbx(JNIEnv *env, jobject obj) {
    return print_rbx();
}

JNIEXPORT long JNICALL Java_NativeAssembly_print_rcx(JNIEnv *env, jobject obj) {
    return print_rcx();
}
JNIEXPORT long JNICALL Java_NativeAssembly_print_rdx(JNIEnv *env, jobject obj) {
    return print_rdx();
}


