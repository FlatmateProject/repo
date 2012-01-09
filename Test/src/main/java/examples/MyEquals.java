package examples;

public class MyEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Integer x1=10;
		Integer y1=10;
		
		Integer x2=100;
		Integer y2=100;
		
		System.out.println(x1 == y1);
		System.out.println(x1.equals(y1));
		System.out.println(x2 == y2);
		System.out.println(x2.equals(y2));
		
		String s1="10";
		String  z1="10";
		
		String  s2="100";
		String  z2="100";
		
		System.out.println();
		System.out.println(s1 == z1);
		System.out.println(s1.equals(z1));
		System.out.println(s2 == z2);
		System.out.println(s2.equals(z2));

	}

}
