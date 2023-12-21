package Total;

//This is an abstract class that will be used in Interpreter to get and send data
abstract class InterpreterDataType
{
	public abstract String toString();
	public abstract void fromString(String input);
	public abstract Object getType();
}
