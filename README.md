## Predicate Detection Project
Predicate detection project is outlined at a very high level in Parallel_Algorithms_Project.pdf in this repository.

## QEMU installation and some quicklinks
Note: This was tested on a ubuntu 23.04 lunar release

```

sudo apt update -y && sudo apt upgrade -y
sudo apt install qemu-user qemu-user-static gcc-aarch64-linux-gnu binutils-aarch64-linux-gnu binutils-aarch64-linux-gnu-dbg build-essential

```

copy and pasted the above commands shamelessly from

```

https://azeria-labs.com/arm-on-x86-qemu-user/

```

for more detailed information on QEMU and GDB this seems like a good starting point

```

https://en.wikibooks.org/wiki/QEMU/Debugging_with_QEMU

```

# Taskings and What needs to be done (high level)

## 1) Linear Lattice Predicate Proof

Proving the Predicate of Return-to-Stack-Exploit is a linear predicate

## 2) Emulate a staically compiled program with GDB

Proving that we can emulate a statically compiled binary and get its state with GDB 

## 3) Proving that we can deploy a system in parallel 

Such that different states can be tested during the Linear Lattice Predicate (LLP) Detection Algorithm is feasible.  

## 4) For debugging purposes Spin up an example vulnerable program

This is neccessary in order to gain a fail fast, and progress as we develop a parallel program


## another approach is to just see when LR is overwriiten wiht a hook into each basic block?

from chat gpt:

```
// Assume this is your function that you want to test
// It should not modify the LR register
void myFunction(int a, int b) {
    // Some code here
}

// This is a function that we will use to check the state of the LR
int isLRPreserved(void (*func)(int, int)) {
    // Read the value of the LR register before calling the function
    int originalLR;
    asm volatile ("MOV %0, lr" : "=r" (originalLR));

    // Call the function
    func(0, 0);

    // Read the value of the LR register again after calling the function
    int updatedLR;
    asm volatile ("MOV %0, lr" : "=r" (updatedLR));

    // Compare the values to check if LR is preserved
    return originalLR == updatedLR;
}

// This is your unit test function
void testLRPreservation() {
    // Run the test by passing your function to the isLRPreserved function
    int result = isLRPreserved(myFunction);

    // Check the result and report the test outcome
    if (result) {
        // Test passed
        // You might want to log or print a success message
    } else {
        // Test failed
        // You might want to log or print a failure message
    }
}

int main() {
    testLRPreservation();
    return 0;
}
```
above is a unit test for seeing when LR is overwritten. more or less pseudocode.

## Java instead of all the C and C++ jargon
Just realized that Java has Java Parallel Streams that may make it easier to hook into programs to analyze and introspect?

```
src: https://www.baeldung.com/java-when-to-use-parallel-stream
```

The above source is good for doing operations.  But what about hooking into another program?

so diving down into the internet fount this link

```
https://java-native-access.github.io/jna/5.13.0/javadoc/overview-summary.html#overview_description
```
which was nested in a stack overflow post

```
https://github.com/java-native-access/jna
https://stackoverflow.com/questions/917789/java-native-interface-with-any-arbitrary-c-code
```

So maybe we can hook c programs into java through the Java Native Access (JNA) although the build is failing? at the moment we'll have to find a version that works? or use the java version that allows us to use parallel streams and hook into different sections of code to...emulate the program..through Java (ewww). However, I think if we are able to do this we can at least gain some introspection from a low level program such as C and use Java's high level libraries to make it easier to step through a programs Global State for each Thread/Processor, which makes it a little more theoretically designed with a higher degree of confidence of project completion.  QEMU and Docker I think would be pretty hard.  but this paried with the homework assignment (Homework 3) I think it's more doable now.

# Design Choices

## Qemu

## Docker

## Java JNA and Parallel Streams
This may be the best option since we need to generalize loading a program and executing lines of code (or instructions) and stepping through each processes in parallel according to an  Linear Lattice Predicte (LLP) path search mixed with a predicate detection algorithm.


