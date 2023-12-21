package Total;

import java.util.ArrayList;

public class ForNode extends StatementNode
{
	private VariableReferenceNode variable;
	private Node from;
	private Node to;
	private ArrayList<StatementNode> statements;
	
	public ForNode (VariableReferenceNode variable, Node from, Node to, ArrayList<StatementNode> statements)
	{
		this.variable = variable;
		this.from = from;
		this.to = to;
		this.statements = statements;
	}
	
	public VariableReferenceNode getVariable()
	{
		return variable;
	}
	
	public Node from()
	{
		return from;
	}
	
	public Node to()
	{
		return to;
	}
	
	public ArrayList<StatementNode> getStatements()
	{
		return statements;
	}
	
	public String toString()
	{
		return "ForNode [variable=" + variable.toString() + ", from=" + from.toString() + ", to=" + to.toString() +
				", statements=" + statements.toString() + "]";
	}
}
 