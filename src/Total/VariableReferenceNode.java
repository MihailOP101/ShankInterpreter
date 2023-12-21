package Total;

/**
 * This is makes a reference to the variables
 * @author mihailkaramanolev
 *
 */
public class VariableReferenceNode extends Node
{
	private String name;
	private Node arrayIndexExpression;
	
	public String getName()
	{
		return name;
	}
	
	public Node getArrayIndexExpression()
	{
		return arrayIndexExpression;
	}
	
	public VariableReferenceNode(String name)
	{
		this.name = name;
		this.arrayIndexExpression = null;
	}
	
	public VariableReferenceNode(String name, Node arrayIndexExpression)
	{
		this.name = name;
		this.arrayIndexExpression = arrayIndexExpression;
	}
	
	public String toString()
	{
		if (arrayIndexExpression == null)
		{
			return name;
		}
		else
		{
			return name + "[" + arrayIndexExpression.toString() + "]";
		}
	}
}
