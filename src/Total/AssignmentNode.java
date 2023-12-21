package Total;

public class AssignmentNode extends StatementNode
{

	private VariableReferenceNode ref;
	private Node value;
	
	public VariableReferenceNode getRef()
	{
		return ref;
	}
	
	public Node getValue()
	{
		return value;
	}
	
	public AssignmentNode(VariableReferenceNode ref, Node value)
	{
        this.ref = ref;
        this.value = value;
    }

	
	public String toString() 
	{
		return ref + " " + value;
	}
	
}
