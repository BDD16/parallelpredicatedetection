public class TrampolineExample {
    static {
        System.load("/Users/blake/Documents/UT_Masters/Parallel_Algorithms/parallel_algorithms/term_project/parallelpredicatedetection/my-app/src/main/java/com/utece/student/llpdetection/instrumentation/inlineassembly/TrampolineExample.dylib");
    }

    // Native method declaration to get the RIP value
    private native long invokeTrampoline();

    public long functionStub() {
        return invokeTrampoline();
    }

    public static void main(String[] args) {
        TrampolineExample example = new TrampolineExample();
        long ripValue1 = example.functionStub();
        long ripValue2 = example.functionStub();
        long ripValue3 = example.functionStub();
        example.invokeTrampoline();
        example.invokeTrampoline();
    }
}
