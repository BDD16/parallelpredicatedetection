export JAVA_HOME=/Users/blake/Library/Java/JavaVirtualMachines/openjdk-20.0.2/Contents/Home
## Then to compile on Linux (if mac use the Darwin library)
gcc -shared -o libassembly.o.1.0 -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -I${JAVA_HOME}/include/darwin readi364RegNative.c
g++ -dynamiclib -o libassembly.1.0.dylib libassembly.o.1.0 -lassembly

cd /path/to/my-app

mvn install:install-file \
-Dfile=/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/src/main/java/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly.1.0.dylib \
-DgroupId=com.utece.student.llpdetection.instrumentation.inlineassembly \
-DartifactId=libassembly \
-Dversion=1.0 \
-Dpackaging=dylib \
-DgeneratePom=true