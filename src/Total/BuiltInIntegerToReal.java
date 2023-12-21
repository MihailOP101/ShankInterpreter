package Total;

import java.util.ArrayList;

public class BuiltInIntegerToReal extends FunctionNode
{
	
	//Gets the information from FunctionNode
	public BuiltInIntegerToReal(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}

	public void Execute(ArrayList<InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 1)
		{
			throw new Exception("Must be a length of 1");
		}
		
		//This checks if its an integer
		if (parameters.get(0) instanceof IntegerDataType)
		{
			//This gets the value as an integer and converts it to a float 
			int intValue = (int) parameters.get(0).getType(); 
			float floatValue = (float) intValue;
			parameters.set(0, new RealDataType(floatValue));
		}
		else
		{
			throw new Exception("This needs to be a integer");
		}
	}
}
