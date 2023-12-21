package Total;

public class BoolNode extends Node 
{
	private Boolean Bool;
	
	public BoolNode(Boolean Bool)
	{
		this.Bool = Bool;
	}
	
	public Boolean getBool()
	{
		return Bool;
	}

	public String toString() 
	{
		return Boolean.toString(Bool);
	}
}
