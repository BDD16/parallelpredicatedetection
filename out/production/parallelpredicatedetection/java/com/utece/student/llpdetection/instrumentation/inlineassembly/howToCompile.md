export JAVA_HOME=/Users/blake/Library/Java/JavaVirtualMachines/openjdk-20.0.2/Contents/Home
## Then to compile on Linux (if mac use the Darwin library)
## first generate the NativeAssembly.h file with
javah -jni NativeAssembly

this may not work so you have to down grade java to 1.8
    export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
then compile from /path/to/src/main/java/ directory (com/ shoudl be a directory level now)

now run 
```
javah -jni com.utece.student.llpdetection.instrumentation.inlineassembly.NativeAssembly
```
that'll make a prety large file name. copy that to the where the  original NativeAssembly.java file folder is
```
cd /com/utece/student/llpdetection/instrumentation/inlineassembly/
```

now we can recompile and loathe the steps we took to make a change! 
(only need to do when a new prototype (reutrn type) changes or functin name changes)

else if only the code block changes then you can skip the above and move to compiiling the shared with gcc

## compile shared with gcc
gcc -shared -o libassembly.o.1.0 -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -I${JAVA_HOME}/include/darwin readi364RegNative.c

## mac caveat instead of -dynamiclib use -bundle for a mach-o file
g++ -bundle -o libassembly.1.0.dylib libassembly.o.1.0
## copy the libassembly object file to the root my-app directory to be later found after mvn installed
cp ./libassembly.o.1.0 /path/to/my-app/

cd /path/to/my-app

mvn install:install-file \
-Dfile=/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/src/main/java/com/utece/student/llpdetection/instrumentation/inlineassembly/libassembly.1.0.dylib \
-DgroupId=com.utece.student.llpdetection.instrumentation.inlineassembly \
-DartifactId=libassembly \
-Dversion=1.0 \
-Dpackaging=dylib \
-DgeneratePom=true


## once installed you can reference with something like this in the pom.xml in /path/to/my-app/ directory
    <dependency>
      <groupId>com.utece.student.llpdetection.instrumentation.inlineassembly</groupId>
      <artifactId>libassembly</artifactId>
      <version>1.0</version>
      <type>dylib</type>
    </dependency>




general explanation:

Writing inline assembly through Java is typically not recommended because Java is designed to be platform-independent and provides a high-level abstraction that isolates you from low-level hardware details, including assembly language. However, if you have a specific need for inline assembly, you can use the Java Native Interface (JNI) to incorporate native assembly code into your Java program. This approach should be used with caution as it can make your code platform-dependent and may introduce security risks.

Here are the general steps to write inline assembly through Java using JNI:

1. **Write the Assembly Code**:
   First, write your assembly code. Be sure to use the syntax and conventions specific to the architecture you're targeting. Inline assembly code is typically written within C/C++ functions.

2. **Compile the Assembly Code**:
   Compile your assembly code into a shared library or dynamic link library (e.g., a `.dll` file on Windows or a `.so` file on Linux).

3. **Create a Java Class**:
   Create a Java class that will serve as the bridge between your Java code and the native assembly code. This class will use JNI to load and call the native code.

4. **Define Native Methods**:
   In your Java class, define native methods using the `native` keyword. These methods will be declared with the `native` keyword but won't have a method body in Java.

   ```java
   public class NativeAssembly {
       public native void executeAssembly();
   }
   ```

5. **Generate Header File**:
   Use the `javah` tool to generate a header file from your Java class. This header file will contain function prototypes for the native methods.

   ```bash
   javah -jni NativeAssembly
   ```

   This command will generate a header file like `NativeAssembly.h`.

6. **Implement Native Code**:
   Write a C/C++ source file that implements the native methods declared in your Java class. Include the generated header file and write the native assembly code within these methods.

   ```c
   #include "NativeAssembly.h"

   JNIEXPORT void JNICALL Java_NativeAssembly_executeAssembly(JNIEnv *env, jobject obj) {
       // Your inline assembly code here
   }
   ```

7. **Compile Native Code**:
   Compile the C/C++ source file into a shared library, making sure to link any necessary libraries and include the JNI header files.

   ```bash
   gcc -shared -o libassembly.so -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux NativeAssembly.c
   ```

8. **Load and Call Native Code**:
   In your Java code, load the shared library using `System.loadLibrary` and call the native method.

   ```java
   public class Main {
       static {
           System.loadLibrary("assembly");
       }

       public static void main(String[] args) {
           NativeAssembly nativeAssembly = new NativeAssembly();
           nativeAssembly.executeAssembly();
       }
   }
   ```

9. **Run the Java Program**:
   Compile and run your Java program as usual.

Keep in mind that writing inline assembly through Java using JNI is complex and platform-dependent. It should be used sparingly and only when there is a compelling need for low-level hardware access that cannot be achieved through standard Java libraries or native Java code.