package transformers;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import javassist.NotFoundException;
import javassist.CannotCompileException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
public class jvmTransformer extends Transformer implements ClassFileTransformer {

    ClassLoader targetClassLoader = null;

    public jvmTransformer(String name, ClassLoader classLoader) {
        super();
    }

    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) {
        byte[] byteCode = classfileBuffer;
        String finalTargetClassName = this.targetClassName.toString().replaceAll("\\.", "/");
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (className.equals(finalTargetClassName)
                && loader.equals(targetClassLoader)) {

            System.out.println("[Agent] Transforming class MyAtm");
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName.get(0));
                CtMethod m = cc.getDeclaredMethod(
                        "main");
                m.addLocalVariable(
                        "startTime", CtClass.longType);
                m.insertBefore(
                        "startTime = System.currentTimeMillis();" +
                                "InstrumentBinary binary_trampoline = new InstrumentBinary();" +
                                "binary_trampoline.customFunction();"
                        );

                StringBuilder endBlock = new StringBuilder();

                m.addLocalVariable("endTime", CtClass.longType);
                m.addLocalVariable("opTime", CtClass.longType);
                endBlock.append(
                        "endTime = System.currentTimeMillis();");
                endBlock.append(
                        "opTime = (endTime-startTime)/1000;");

                endBlock.append(
                        "LOGGER.info(\"[Application] Withdrawal operation completed in:" +
                                "\" + opTime + \" seconds!\");");
                endBlock.append("binary_trampoline.customFunction();");

                m.insertAfter(endBlock.toString());

                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                System.out.println("Exception" + e);
            }
        }
        return byteCode;
    }
}