public class Test{ 
	public static void main(String[] args) {
		System.out.println(Variables.staticVariable); // Good practice

		Variables object1  = new Variables();
		System.out.println(object1.instanceVariable);
		System.out.println(object1.staticVariable); // bad practice
		System.out.println("---------------------------------------");

		Variables object2  = new Variables();
		System.out.println(object2.instanceVariable);
		System.out.println(object2.staticVariable); // bad practice
		System.out.println("---------------------------------------");

		object1.instanceVariable = "This is the new instanceVariable for object1";
		System.out.println(object1.instanceVariable);
		System.out.println(object2.instanceVariable);
		System.out.println("---------------------------------------");

		object1.staticVariable = "This is the new staticVariable shared by object1 and object2";
		System.out.println(Variables.staticVariable);
		System.out.println(object1.staticVariable);
		System.out.println(object2.staticVariable);

	}
}