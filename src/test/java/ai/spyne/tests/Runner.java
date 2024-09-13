package ai.spyne.tests;

import org.testng.TestNG;

public class Runner {

	static TestNG testng;

	public static void main(String[] arr) {
		testng = new TestNG();
		TestNG.main(arr);
	}
}
