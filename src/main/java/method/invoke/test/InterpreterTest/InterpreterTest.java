package method.invoke.test.InterpreterTest;

public class InterpreterTest {
	
	public int calculate() {
		int a = 100;  
	    int b = 200;  
	    int c = 300;  
	    return (a + b) * c;  
	}
	
	public static void main(String[] args) {
		System.out.println(new InterpreterTest().calculate());
	}
}
