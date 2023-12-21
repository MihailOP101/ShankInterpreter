package Total;

import java.util.ArrayList;

public class BuiltInLeft extends FunctionNode
{
	public BuiltInLeft(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}
	
	public void Execute(ArrayList <InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 3)
		{
			throw new Exception("After 'left' there needs to be 3 parameters");
		}
		
		//This makes sure that this is a String type
		if (parameters.get(0) instanceof StringDataType)
		{
			String readString = parameters.get(0).toString();
			
			//This makes sure that it is an Integer type
			if (parameters.get(1) instanceof IntegerDataType)
			{
				int readNumber = Integer.parseInt(parameters.get(1).toString());
				String returnString = "";
		        for (int i = 0; i < readNumber; i++)
		        {
		            returnString += readString.charAt(i);
		        }
		        
		        //This makes sure that this is a String Type
				if (parameters.get(2) instanceof StringDataType)
				{
					parameters.get(2).fromString(returnString);
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
