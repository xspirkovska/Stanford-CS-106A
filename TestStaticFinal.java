public class TestStaticFinal { 
	public static void main(String[] args) {

		StaticVsFinal object1  = new StaticVsFinal();
		System.out.println(StaticVsFinal.finalString);
		System.out.println(StaticVsFinal.staticVariable);
		System.out.println("---------------------------------------");

		StaticVsFinal.staticVariable = "This variable can be modified"; 
		StaticVsFinal.finalString = "This variable can be modified"; // this will throw a compiler error, comment out to compile code
		System.out.println(StaticVsFinal.staticVariable);
		System.out.println(StaticVsFinal.finalString);
		System.out.println("---------------------------------------");

	}
}