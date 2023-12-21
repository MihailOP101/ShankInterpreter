package Total;

//This gets the input character and returns it
public class CharacterDataType extends InterpreterDataType
{
	private char value;
	
	public CharacterDataType(char value)
	{
		this.value = value;
	}
	
	public Character getValue() 
	{
		return value;
	}
	
	public void setValue(char input)
	{
		this.value = input;
	}
	
	public String toString()
	{
		return Character.toString(value);
	}
	
	public void fromString(String input)
	{
		value = input.charAt(value);
	}

	public Object getType() 
	{
		return value;
	}
}	
