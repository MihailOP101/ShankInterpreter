package Total;

/**
 * This is the variable node that will check what type of variable is being inputed
 * @author mihailkaramanolev
 *
 */
public class VariableNode extends Node
{

	private String name;
	private Node type;
	private boolean isVar;
	private Node value;
	private int from;
	private int to;
	
	
    public VariableNode(String name, Node type, boolean isVar)
    {
        this.name = name;
        this.type = type;
        this.isVar = isVar;
    }

    public VariableNode(String name, Node type, boolean isVar, Node value) 
    {
        this.name = name;
        this.type = type;
        this.isVar = isVar;
        this.value = value;
    }

    public VariableNode(String name, Node type, boolean isVar, int from, int to)
    {
        this.name = name;
        this.type = type;
        this.isVar = isVar;
        this.from = from;
        this.to = to;
    }
	
    public String getName()
    {
        return name;
    }

    public Node getType() 
    {
        return type;
    }
    
    public Node setType(Node type)
    {
    	return this.type = type;
    }

    public boolean isVar() 
    {
        return isVar;
    }

    public Node getValue() 
    {
        return value;
    }

    public int getFrom() 
    {	
        return from;
    }

    public int getTo()
    {
   		return to;
    }
    
	public String toString() 
	{		
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(": = ");
		sb.append(value == null ? "null" : value.toString()).append(" of type: ");
		sb.append(type == null ? "null" : type.toString()).append(" is constant: ");
		sb.append(isVar);

		return sb.toString();
	}

}
