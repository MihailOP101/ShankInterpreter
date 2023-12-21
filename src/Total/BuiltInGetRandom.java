package Total;

import java.util.ArrayList;

public class BuiltInGetRandom extends FunctionNode
{
	//Gets the information from FunctionNode
	public BuiltInGetRandom(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
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
		
		//This makes sure that the input for this is an integer
		if (parameters.get(0) instanceof IntegerDataType)
		{
			//This gets a random number is sets parameters to it
			int GetRandom = (int) Math.random();
			parameters.set(0, new IntegerDataType(GetRandom));
		}
		else
		{
			throw new Exception("It must be of type Integer");
		}
	}
}
