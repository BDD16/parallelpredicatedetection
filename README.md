## Predicate Detection Project
Predicate detection project is outlined at a very high level in Parallel_Algorithms_Project.pdf in this repository.

## QEMU installation and some quicklinks
Note: This was tested on a ubuntu 23.04 lunar release

'''bat
sudo apt update -y && sudo apt upgrade -y
sudo apt install qemu-user qemu-user-static gcc-aarch64-linux-gnu binutils-aarch64-linux-gnu binutils-aarch64-linux-gnu-dbg build-essential
'''

copy and pasted the above commands shamelessly from

'''
https://azeria-labs.com/arm-on-x86-qemu-user/
'''

for more detailed information on QEMU and GDB this seems like a good starting point

'''
https://en.wikibooks.org/wiki/QEMU/Debugging_with_QEMU
'''

# Taskings and What needs to be done (high level)

## 1) Linear Lattice Predicate Proof

Proving the Predicate of Return-to-Stack-Exploit is a linear predicate

## 2) Emulate a staicallyh compile program with GDB

Proving that we can emulate a statically compiled binary and get its state with GDB 

## 3) Proving that we can deploy a system in parallel 

Such that different states can be tested during the Linear Lattice Predicate (LLP) Detection Algorithm is feasible.  

## 4) For debugging purposes Spin up an example vulnerable program

This is neccessary in order to gain a fail fast, and progress as we develop a parallel program

