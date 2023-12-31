package Total;

public class RealNode extends Node 
{
	private float value;
	
	public RealNode(float value)
	{
		this.value = value;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return Float.toString(value);
	}
}
