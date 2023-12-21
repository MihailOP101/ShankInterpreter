package Total;

import java.util.ArrayList;

public class BuiltInStart extends FunctionNode
{
	//This gets the information from FunctionNode
	public BuiltInStart(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements)
	{
		super(name, parameters, constants, variables, statements);
	}
	
	public void Execute(ArrayList<InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 2)
		{
			throw new Exception("Must be a length of 2");
		}
		
		//Checks if it is an array type
		if (parameters.get(0) instanceof ArrayDataType)
		{
			//Checks if it is an Integer type
			if (parameters.get(1) instanceof IntegerDataType)
			{
				//This gets the var value equal to the first value there
				String str = parameters.get(0).toString();
				parameters.get(1).fromString(String.valueOf(str.charAt(0)));
			}
			else
			{
				throw new Exception("Needs to be an Integer");
			}
		}
		else
		{
			throw new Exception("Needs to be an Array");
		}
	}
}
