package Total;

import java.util.ArrayList;

public class IfNode extends StatementNode
{
	private BooleanCompareNode condition;
	private ArrayList<StatementNode> statements;
	
	private IfNode elseNode;
	
	public IfNode (ArrayList<StatementNode> statements)
	{
		this.statements = statements;
	}
	
	public IfNode (BooleanCompareNode condition, ArrayList<StatementNode> statements)
	{
		this.condition = condition;
		this.statements = statements;
		this.elseNode = null;
	}
	
	public IfNode (BooleanCompareNode condition, ArrayList<StatementNode> statements, IfNode elseNode)
	{
		this.condition = condition;
		this.statements = statements;
		this.elseNode = elseNode;
	}
	
	
	public IfNode getElseNode()
	{
		return elseNode;
	}
	
	public BooleanCompareNode getCondition()
	{
		return condition;
	}
	
	public ArrayList<StatementNode> getStatements() 
	{
		return statements;    
	}
	
	
	public String toString()
	{
		return "IfNode [condition=" + condition.toString() + ", statements=" + statements.toString() + "]";
 	}
}
