package examples;

public class Trim {

	public String repleaceWhitespace(String s) {
		return s.replaceAll("\\s+", " ");
	}
	
	public static void main(String[] args) 
	{
		Trim trim = new Trim();
		
		System.out.println("addElement result: " + trim.repleaceWhitespace("[\ta]"));
		
	}
}
