package Total;

import java.util.ArrayList;

public class BuiltInEnd extends FunctionNode
{
	//This gets the information from FunctionNode
	public BuiltInEnd(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}

	public void Execute(ArrayList <InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 2)
		{
			throw new Exception("Must be a length of 2");
		}
		
		//This checks if its an Array type
		if (parameters.get(0) instanceof ArrayDataType)
		{
			//This checks if its an IntegerType
			if (parameters.get(1) instanceof IntegerDataType)
			{
				//Gets and sets the value of the 
				String strBuild = parameters.get(0).toString();
				int strValue = strBuild.length();
				parameters.get(1).fromString(String.valueOf(strBuild.charAt(strValue - 1)));
			}
			else
			{
				throw new Exception("Must be an integer type");
			}
		}
		else
		{
			throw new Exception("Must be an Array type");
		}
	}
}
