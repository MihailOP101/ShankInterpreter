package Total;

import java.util.ArrayList;

public class FunctionCallNode extends Node
{
	private String functionName;
	private ArrayList<ParameterNode> parameters;
	
	public FunctionCallNode(String functionName, ArrayList<ParameterNode> parameters)
	{
		this.functionName = functionName;
		this.parameters = parameters;
	}
	
	public String getFunctionName()
	{
		return functionName;
	}
	
	public ArrayList<ParameterNode> getThisParameter()
	{
		return parameters;
	}
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
	
		sb.append("FunctionCall: ").append(functionName).append("(");
		
		for(int i = 0; i < parameters.size(); i++)
		{
			sb.append(parameters.get(i).toString());
			if (i < parameters.size() - 1)
			{
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
