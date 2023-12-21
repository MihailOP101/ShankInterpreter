package Total;

import java.util.ArrayList;

public class BuiltInSubstring extends FunctionNode
{
	//This gets the information from FunctionNode
	public BuiltInSubstring(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}

	public void Execute(ArrayList <InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 4)
		{
			throw new Exception("Must be length of 4");
		}
		
		//This checks if its a String parameter
		if (parameters.get(0) instanceof StringDataType)
		{
			//This checks if its an Integer parameter
			if (parameters.get(1) instanceof IntegerDataType)
			{
				//This checks if its an Integer parameter 
				if (parameters.get(2) instanceof IntegerDataType)
				{
					//This checks if its a String parameter
					if (parameters.get(3) instanceof StringDataType)
					{
						String str = parameters.get(0).getType().toString();
						str = str.substring( (int) parameters.get(1).getType(), (int) parameters.get(2).getType());
						parameters.get(3).fromString(str);
					}
					else
					{
						throw new Exception("Has to be a String type");
					}
				}
				else 
				{
					throw new Exception("Has to be an Integer type");
				}
			}
			else 
			{
				throw new Exception("Has to be an Integer type");
			}
		}
		else
		{
			throw new Exception("Has to be a String type");
		}
	}
}
