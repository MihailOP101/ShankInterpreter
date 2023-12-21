package Total;

import Total.Token.Type;

public class MathOpNode extends Node
{	
	private Type type;
	private Node left;
	private Node right;
	
	public MathOpNode(Type type, Node left, Node right)
	{
		this.type = type;
		this.left = left;
		this.right = right;
	}
	
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
	
	public String toString()
	{
		return "(" + left.toString() + " " + type + " " + right.toString() + ")";
	}
}
