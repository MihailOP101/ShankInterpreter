package Total;

//This gets the input String and returns it
public class StringDataType extends InterpreterDataType
{
	private String value;
	
	public StringDataType(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String input)
	{
		this.value = input;
	}
	
	public String toString()
	{
		return value;
	}
	
	public void fromString(String input)
	{
		value = input;
	}

	public Object getType()
	{
		return value;
	}
}
