export JAVA_HOME={/Users/<username>/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home
## Then to compile on Linux (if mac use the Darwin library)
gcc -shared -o libassembly.o.1.0 -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -I${JAVA_HOME}/include/darwin readi364RegNative.c
g++ -dynamiclib -o libassembly.dylib.1.0 libassembly.o.1.0 -lc

cd /path/to/my-app

mvn install:install-file \
-Dfile=/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/src/main/java/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly.dylib.1.0 \
-DgroupId=com.utece.student.llpdetection.instrumentation.inlineassembly \
-DartifactId=libassembly \
-Dversion=1.0 \
-Dpackaging=dylib \
-DgeneratePom=true