package Total;

import java.util.ArrayList;

public class SemanticAnalysis 
{
	private ProgramNode program;
	private FunctionNode function;
	
	// Constructor for the SemanticAnalysis class
	public SemanticAnalysis(ProgramNode program) throws Exception
	{
		this.program = program;
		allFunctions();
	}
	
	// The allFunctions() method iterates through all the function names in the program
	private void allFunctions() throws Exception
	{
		for(int i = 0; i < program.names.size(); i++)
		{
			function = program.getFunction(program.names.get(i));
			checkAssignments(function.getStatements());
		}
	}

	// The checkAssignments() method takes an ArrayList of StatementNode objects as input.
	public void checkAssignments(ArrayList<StatementNode> statements) throws Exception 
	{	
		for(int i = 0; i < statements.size() ; i++)
		{
			if (statements.get(i) instanceof IfNode)
			{
				checkAssignments(((IfNode)statements.get(i)).getStatements());
			}
			if (statements.get(i) instanceof WhileNode)
			{
				checkAssignments(((WhileNode)statements.get(i)).getStatements());
			}
			if (statements.get(i) instanceof ForNode)
			{
				checkAssignments(((ForNode)statements.get(i)).getStatements());
			}
			if (statements.get(i) instanceof RepeatNode)
			{			
				checkAssignments(((RepeatNode)statements.get(i)).getStatements());
			}
			if (statements.get(i) instanceof AssignmentNode)
			{
				VariableReferenceNode left = ((AssignmentNode)statements.get(i)).getRef();
				Node leftType = variableReferenceNode(left);
				Node right = ((AssignmentNode)statements.get(i)).getValue();
				Node rightType = expression(right);
				if (leftType.getClass() != rightType.getClass())
				{
					throw new Exception("Invalid Assignment");
				}
			}
		}
	}
	
	// This method evaluates a given expression node and returns its value.
	public Node expression(Node node) throws Exception
	{
		// Check the type of the node using a series of if and else if statements.
		if (node instanceof MathOpNode)
		{
			// If the node is a MathOpNode, call the MathOpNode method to evaluate it.
			MathOpNode mathOp = (MathOpNode) node;
			Node value = MathOpNode(mathOp);
			return value;
		}
		else if (node instanceof VariableReferenceNode)
		{
			// If the node is a VariableReferenceNode, call the variableReferenceNode method to evaluate it.
			VariableReferenceNode varRef = (VariableReferenceNode) node;
			Node value = variableReferenceNode(varRef);
			return value;
		}
		else if (node instanceof StringNode 
				|| node instanceof RealNode
				|| node instanceof IntegerNode)
		{
			// If the node is a constant node, call the constantNode method to evaluate it.
			Node conNode = (Node) node;
			Node value = constantNode(conNode); 
			return value;
		}
		else 
		{
			// If the node is of an unknown type, throw an exception.
			throw new Exception("Node a proper Node type");
		}
	}
		
	private Node MathOpNode(MathOpNode mathOp) throws Exception
	{
		// Evaluate the left and right nodes of the MathOpNode.
		Node Left = expression(mathOp.getLeft());
		Node Right = expression(mathOp.getRight());

		// Check if the left and right nodes have the same data type. If not, throw an exception.
		if (!(Left.getClass().equals(Right.getClass())))
		{
			throw new Exception("Not the same data type");
		}

		// Evaluate the MathOpNode based on the data type of the left and right nodes.
		if (Left instanceof IntegerNode && Right instanceof IntegerNode)
		{
			int intLeft = ((IntegerNode) Left).getValue();
			int intRight = ((IntegerNode) Right).getValue();

			// Perform the corresponding operation and return the result.
			switch (mathOp.getType())
			{
				case ADDITION:
				{
					return new IntegerNode(intLeft + intRight);
				}
				case SUBTRACT:
				{
					return new IntegerNode(intLeft - intRight);
				}
				case DIVIDE:
				{
					if (intRight == 0)
					{
						throw new Exception("Cannot divide by 0");
					}
					return new RealNode(intLeft / intRight);
				}	
				case MULTIPLY:
				{
					return new IntegerNode(intLeft * intRight);
				}
				case MOD:
				{
					if (intRight == 0)
					{
						throw new Exception("Cannot mod by 0");
					}
					return new IntegerNode(intLeft % intRight);
				}
				default:
					throw new Exception("Not a valid symbol");
			}
				
		}
		else if (Left instanceof RealNode && Right instanceof RealNode)
		{
			float realLeft = ((RealNode) Left).getValue();
			float realRight = ((RealNode) Right).getValue();
			
			// Perform the corresponding operation and return the result.
			switch (mathOp.getType())
			{
				case ADDITION:
				{
					return new RealNode(realLeft + realRight);
				}
				case SUBTRACT:
				{
					return new RealNode(realLeft - realRight);
				}
				case DIVIDE:
				{
					if (realRight == 0)
					{
						throw new Exception("Cannot divide by 0");
					}
					return new RealNode(realLeft / realRight);
				}
				case MULTIPLY:
				{
					return new RealNode(realLeft * realRight);
				}
				case MOD:
				{
					if (realRight == 0)
					{
						throw new Exception("Cannot mod by 0");
					}
					return new RealNode(realLeft % realRight);
				}
				default:
					throw new Exception("Not a valid symbol");
			}
		}
		else if (Left instanceof StringNode && Right instanceof StringNode)
		{
			String stringLeft = ((StringNode) Left).getString();
			String stringRight = ((StringNode) Right).getString();

			// Concatenate the two strings and return the result.
			switch (mathOp.getType())
			{
				case ADDITION:
		        {
		        	return new StringNode(stringLeft + stringRight);
		        }
				default:
					throw new Exception("Not the valid symbol");
		    }
		}
		else
		{
			throw new Exception("Not the same data type");
		}
	}
	
	private Node variableReferenceNode(VariableReferenceNode name) throws Exception
	{
		String varName = name.getName();
		for (int i = 0; i < function.getParameters().size(); i++)
		{
			if(varName.equals(function.getParameters().get(i).getName()))
			{
				return function.getParameters().get(i);
			}
		}
		for (int i = 0; i < function.getVariables().size(); i++)
		{
			if(varName.equals(function.getVariables().get(i).getName()))
			{
				return function.getVariables().get(i);
			}
		}
		throw new Exception("Not proper type");
	}
	
	// This method evaluates a given constant node and returns its value as a corresponding Node.
	private Node constantNode(Node constantNode) throws Exception
	{
	    // Evaluate the constant node using the expression method.
	    Node node = expression(constantNode);

	    // Convert the node to the appropriate Node based on its type.
	    if (node instanceof StringNode)
	    {
	        String value = ((StringNode)node).getString();
	        return new StringNode(value);
	    }
	    else if (node instanceof RealNode)
	    {
	        float value = ((RealNode)node).getValue();
	        return new RealNode(value);
	    }
	    else if (node instanceof IntegerNode)
	    {
	        Integer value = ((IntegerNode)node).getValue();
	        return new IntegerNode(value);
	    }
	    else if (node instanceof BoolNode)
	    {
	        boolean value = ((BoolNode)node).getBool();
	        return new BoolNode(value);
	    }
	    else
	    {
	        // If the node is of an unknown type, throw an exception.
	        throw new Exception("Not a valid data type");
	    }
	}
}
