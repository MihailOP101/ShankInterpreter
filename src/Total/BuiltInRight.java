package Total;

import java.util.ArrayList;

public class BuiltInRight extends FunctionNode
{
	//This gets the information from FunctionNode
	public BuiltInRight(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}
	
	public void Execute(ArrayList<InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 3)
		{
			throw new Exception("After 'left' there needs to be 3 parameters");
		}
		
		//This makes sure that this is a String type
		if (parameters.get(0) instanceof StringDataType)
		{	
			//This makes sure that it is an Integer type
			if (parameters.get(1) instanceof IntegerDataType)
			{   
		        //This makes sure that this is a String Type
				if (parameters.get(2) instanceof StringDataType)
				{
					//This gets the parameters length and sets it
					String str = parameters.get(0).toString();
					str = str.substring(str.length() - (int) parameters.get(1).getType());
					parameters.get(2).fromString(str);
				}
				else
				{
					throw new Exception("Must be a string type");
				}
			}
			else
			{
				throw new Exception("Must be a Integer type");
			}
		}
		else
		{
			throw new Exception("Must be a String Type");
		}
	}
}
