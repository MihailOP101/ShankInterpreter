package Total;
import Total.Token.Type;

/**
 * This compares the boolean nodes with each other
 * @author mihailkaramanolev
 *
 */
public class BooleanCompareNode extends Node
{
	private Type type;
	private Node left;
	private Node right;
	
	public Type getType()
	{
		return type;
	}
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
	}
	
	public BooleanCompareNode(Type type, Node left, Node right)
	{
		this.type = type;
		this.left = left;
		this.right = right;
	}

	public String toString() 
	{
		return "(" + left.toString() + " " + type + " " + right.toString() + ")";
	}
}
