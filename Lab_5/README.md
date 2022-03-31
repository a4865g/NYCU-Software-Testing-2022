# Lab 5: LLVM Pass
1. Given a target.c file, write a llvm pass to satisfy following requirements **without modifying** source code.
	- **Invoke debug function** with the first argument is **9527** in main function. (40%)
	- Let **argv[1] = "aesophor is ghost !!!"** before checking. (30%)
	- Let **argc = 9487** before checking. (30%)
2. Upgrade your llvm-lab-pass.cc to new e3.
	- We will compile your code and use it to instrument target.c.
	- We will execute ./target 1 and see the output result.
	- We may modify the output message after checking, so do not just instrument the output message. 
3. We provide Pass.cc template, Makefile, and a docker file as testing environment.



**Do not compress the files and plagiarism.**