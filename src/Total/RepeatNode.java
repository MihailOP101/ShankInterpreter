package Total;

import java.util.ArrayList;

public class RepeatNode extends StatementNode
{
	private BooleanCompareNode condition;
	private ArrayList<StatementNode> statements;
	
	public RepeatNode (BooleanCompareNode condition, ArrayList<StatementNode> statements)
	{
		this.condition = condition;
		this.statements = statements;
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
		return "RepeatNode [condition=" + condition.toString() + ", statements=" + statements.toString() + "]";
 	}
}