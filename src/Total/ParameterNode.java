package Total;

public class ParameterNode extends Node
{
	private VariableReferenceNode varVariable;
	private Node expression;
	
	//This sees if theres a varVariable or not and makes a certain type of ParameterNode
	public ParameterNode (VariableReferenceNode varVariable)
	{
		this.varVariable = varVariable;
		this.expression = null;
	}
	
	public ParameterNode(Node expression)
	{
		this.varVariable = null;
		this.expression = expression;
	}
	
	public VariableReferenceNode getVarVariable()
	{
		return varVariable;
	}
	
	public Node getExpression()
	{
		return expression;
	}
	
	//This returns the node thats needed
	public String toString()
	{
		if (varVariable != null)
			return "Variable" + varVariable.toString();
		else
			return expression.toString();
	}
}
