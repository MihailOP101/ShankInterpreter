package Total;

//This gets the input Real and returns it
public class RealDataType extends InterpreterDataType
{
	private float value;
	
	public RealDataType (float value)
	{
		this.value = value;
	}
	
	public Float getValue()
	{
		return value;
	}
	
	public void setValue(float input)
	{
		this.value = input;
	}
	
	public String toString()
	{
		return Float.toString(value);
	}
	
	public void fromString(String input)
	{
		value = Float.parseFloat(input);
	}

	public Object getType() 
	{
		return value;
	}
}
