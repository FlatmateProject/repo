package examples;

public class StringEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		if(("String".replace('t', 'T')) == ("String".replace('t', 'T'))) System.out.println("equals");
		else  System.out.println("not equals");
		
		System.out.println("String".replace('t', 'T').equals("String".replace('t', 'T')));
		System.out.println("String" == "String");
		
		String hello="STring";
		String siema ="STring";
		
		if(siema == hello)
		System.out.println("equals");
		else 
		System.out.println("not equals");
	}

}
