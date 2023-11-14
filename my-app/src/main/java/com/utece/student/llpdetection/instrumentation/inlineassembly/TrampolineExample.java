public class TrampolineExample {
    static {
        System.load("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/src/main/java/com/utece/student/llpdetection/instrumentation/inlineassembly/TrampolineExample.dylib");
    }

    // Native method declaration to get the RIP value
    private native long invokeTrampoline();
    private native long returnCurrentRip();

    public long functionStub() {
        return invokeTrampoline();
    }

    public long functionRA(){
        return returnCurrentRip();
    }

    public static void main(String[] args) {
        TrampolineExample example = new TrampolineExample();
        long ripValue1 = example.functionStub();
        long ripValue2 = example.functionStub();
        long ripValue3 = example.functionStub();
        long ripValue4 = example.returnCurrentRip();
        long ripValue5 = example.returnCurrentRip();
        example.invokeTrampoline();
        example.invokeTrampoline();
        System.out.println(Long.toHexString(ripValue1));
        System.out.println(Long.toHexString(ripValue2));
        System.out.println(Long.toHexString(ripValue3));
        System.out.println(Long.toHexString(ripValue4));
        System.out.println(Long.toHexString(ripValue5));
    }
}
