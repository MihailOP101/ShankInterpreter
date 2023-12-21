package Total;

//This gets the input Boolean and returns it
public class BooleanDataType extends InterpreterDataType
{
	private boolean value;
	
	public BooleanDataType(boolean value)
	{
		this.value = value;
	}
	
	public Boolean getValue()
	{
		return value;
	}
	
	public void setValue(boolean input)
	{
		this.value = input;
	}
	
	public String toString()
	{
		return Boolean.toString(value);
	}
	
	public void fromString(String input)
	{
		value = Boolean.parseBoolean(input);
	}

	@Override
	public Object getType() 
	{
		return value;
	}
}
