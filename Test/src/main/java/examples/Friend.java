package examples;

public class Friend 
{

	private int a;
	
	public Friend(int a)
	{
		this.a = a;
	}
	
	public void add(Friend f)
	{
		this.a += f.a;
	}
	
	public String toString()
	{
		return ""+a;
	}
}
