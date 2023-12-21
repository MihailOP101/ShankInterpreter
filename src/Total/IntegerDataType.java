package Total;

//This gets the input Integer and returns it
public class IntegerDataType extends InterpreterDataType
{
	private int value;
	
	public IntegerDataType (int value)
	{
		this.value = value;
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public void setValue(int input)
	{
		this.value = input;
	}
	
	public String toString()
	{
		return Integer.toString(value);
	}
	
	public void fromString(String input)
	{
		value = Integer.parseInt(input);
	}

	public Object getType() 
	{
		return value;
	}
}
	
