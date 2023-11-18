public class RIPExample {
    // Load the native library
    static {
        System.loadLibrary("RIPExample");
    }

    // Native method declaration to get the RIP value
    private native long getRIP();

    public long functionStub(){
        long x = getRIP();
        long z_1 = getRIP();
        long z_2 = getRIP();
        return z_1;
    }

    public long AnotherFunctionDeep(){
        return functionStub();
    }

    public static void main(String[] args) {
        RIPExample example = new RIPExample();
        RIPExample example_2 = new RIPExample();
        long ripValue1 = example.functionStub();
        long ripValue2 = example.functionStub();
        long ripValue3 = example.functionStub();
        System.out.println("RIP value: 0x" + Long.toHexString(ripValue1));
        System.out.println("RIP value: 0x" + Long.toHexString(ripValue2));
        System.out.println("RIP value: 0x" + Long.toHexString(ripValue3));
        System.out.println("RIP value: 0x" + Long.toHexString(example.getRIP()));
        System.out.println("RIP value: 0x" + Long.toHexString(example.functionStub()));
        System.out.println("RIP value: 0x" + Long.toHexString(example_2.AnotherFunctionDeep()));
    }
}
