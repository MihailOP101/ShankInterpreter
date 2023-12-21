package Total;

import java.util.ArrayList;

public class BuiltInWrite extends FunctionNode
{

	public BuiltInWrite(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}

	public void Execute(ArrayList<IntegerDataType> parameters)
	{
		for (int i = 0; i < parameters.size(); i++)
		{
			 System.out.print(parameters.get(i) + " ");
		}
	}
	
	public boolean isVaridic()
	{
		return true;
	}
}
