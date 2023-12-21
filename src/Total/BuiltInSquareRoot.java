package Total;

import java.util.ArrayList;


public class BuiltInSquareRoot extends FunctionNode
{
	//Get the information from FunctionNode 
	public BuiltInSquareRoot(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}

	public void Execute (ArrayList<InterpreterDataType> parameters) throws Exception
	{
		if (parameters.size() != 1)
		{
			throw new Exception("Must be a length of 1");
		}
		
		//This checks if it is a real parameter
		if (parameters.get(0) instanceof RealDataType)
		{
			//This does the squareroot of the input
			float result = (float) Math.sqrt((double) parameters.get(0).getType());// Math.sqrt(Float.parseFloat(parameters.get(0).toString()));
			parameters.set(0, new RealDataType(result));
		}
		else 
		{
			throw new Exception("Has to be of type Real");
		}
	}


}
