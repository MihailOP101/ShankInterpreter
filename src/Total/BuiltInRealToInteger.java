package Total;

import java.util.ArrayList;

public class BuiltInRealToInteger extends FunctionNode
{
	//This gets the data from FunctionNode
	public BuiltInRealToInteger(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
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
		
		//This checks if its an float
		if (parameters.get(0) instanceof RealDataType)
		{
			//This gets the value float and converts it to a integer
			float floatValue = (float) parameters.get(0).getType();
			int intValue = (int) floatValue;
			parameters.set(0, new IntegerDataType(intValue));
		}
		else
		{
			throw new Exception("This needs to be a float number");
		}
	}
}
