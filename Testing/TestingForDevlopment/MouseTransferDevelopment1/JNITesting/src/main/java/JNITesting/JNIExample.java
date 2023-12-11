package JNITesting;

public class JNIExample {
	static {
		System.loadLibrary("MyJNIExample");
	}

	private static native int add(int a, int b);

	public static void main(String[] args) {
		int sum = add(1, 2);
		System.out.println("The sum is " + sum);
	}
}

