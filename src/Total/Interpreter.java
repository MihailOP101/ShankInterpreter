package Total;

import java.util.ArrayList;
import java.util.HashMap;

//This is the main interpreter class that gathers all the information and makes it into an IDT
public class Interpreter 
{
	HashMap <String, InterpreterDataType> interMap = new HashMap<String, InterpreterDataType>();
	
	private InterpreterDataType functionCall(HashMap<String, InterpreterDataType> interMap, FunctionCallNode node) throws Exception 
	{
		return null;
	}
	
	private FunctionNode findFunctionDefinition(String functionName) 
	{
		return null;
		
	}

	// This method interprets the code of a function.
	private void interpretFunction(FunctionNode functionNode) throws Exception 
	{
	    // First, iterate through the constants of the function.
	    for (VariableNode constant : functionNode.getConstants()) 
	    {
	        // Put the name and type of each constant into the interMap HashMap.
	        interMap.put(constant.getName(), expression(interMap, constant.getType()));
	    }
	    
	    // Next, iterate through the local variables of the function.
	    for (VariableNode localVar : functionNode.getVariables()) 
	    {
	        // Put the name and type of each local variable into the interMap HashMap.
	        interMap.put(localVar.getName(), expression(interMap, localVar.getType()));
	    }

	    // Finally, interpret the statements of the function by calling the interpretBlock method.
	    interpretBlock(interMap, functionNode.getStatements());
	}
	
	// This method interprets a block of statements.
	private void interpretBlock(HashMap<String, InterpreterDataType> interMap, ArrayList<StatementNode> statements) throws Exception 
	{
	    // Iterate through each statement in the ArrayList.
	    for (StatementNode statement : statements) 
	    {
	        // Check the type of the statement using a series of if and else if statements.
	        if (statement instanceof AssignmentNode) 
	        {
	            // If the statement is an AssignmentNode, call the interpretAssignmentNode method.
	            interpretAssignmentNode(interMap, (AssignmentNode) statement);
	        } 
	        else if (statement instanceof IfNode) 
	        {
	            // If the statement is an IfNode, call the interpretIfNode method.
	            interpretIfNode(interMap, (IfNode) statement);
	        }
	        else if (statement instanceof WhileNode) 
	        {
	            // If the statement is a WhileNode, call the interpretWhileNode method.
	            interpretWhileNode(interMap, (WhileNode) statement);
	        }
	        else if (statement instanceof RepeatNode) 
	        {
	            // If the statement is a RepeatNode, call the interpretRepeatNode method.
	            interpretRepeatNode(interMap, (RepeatNode) statement);
	        } 
	        else if (statement instanceof ForNode) 
	        {
	            // If the statement is a ForNode, call the interpretForNode method.
	            interpretForNode(interMap, (ForNode) statement);
	        } 
//	        else if (statement instanceof FunctionCallNode)
//	        {
//	            // If the statement is a FunctionCallNode, call the interpretFunctionCall method.
//	            functionCall(interMap, (FunctionCallNode) statement);
//	        }
	        else 
	        {
	            // If the statement is of an unknown type, throw an exception.
	            throw new Exception("Unknown statement type: " + statement.getClass().getSimpleName());
	        }
	    }
	}
	
	// This method evaluates a given expression node and returns its value.
	public InterpreterDataType expression(HashMap<String, InterpreterDataType> interMap, Node node) throws Exception
	{
	    // Check the type of the node using a series of if and else if statements.
	    if (node instanceof MathOpNode)
	    {
	        // If the node is a MathOpNode, call the MathOpNode method to evaluate it.
	        MathOpNode mathOp = (MathOpNode) node;
	        InterpreterDataType value = MathOpNode(interMap, mathOp);
	        return value;
	    }
	    else if (node instanceof VariableReferenceNode)
	    {
	        // If the node is a VariableReferenceNode, call the variableReferenceNode method to evaluate it.
	        VariableReferenceNode varRef = (VariableReferenceNode) node;
	        InterpreterDataType value = variableReferenceNode(interMap, varRef);
	        return value;
	    }
	    else if (node instanceof StringNode 
	                || node instanceof RealNode
	                || node instanceof IntegerNode)
	    {
	        // If the node is a constant node, call the constantNode method to evaluate it.
	        Node conNode = (Node) node;
	        InterpreterDataType value = constantNode(interMap, conNode); 
	        return value;
	    }
	    else 
	    {
	        // If the node is of an unknown type, throw an exception.
	        throw new Exception("Node a proper Node type");
	    }
	}

	// This method evaluates a booleanCompare and returns the result of the corresponding operation.
	private InterpreterDataType booleanCompare(HashMap <String, InterpreterDataType> interMap, BooleanCompareNode node) throws Exception
	{
		// Evaluate the left and right nodes of the booleanCompare.
		InterpreterDataType Left = expression (interMap, node.getLeft());
		InterpreterDataType Right = expression (interMap, node.getRight());
		
		 // Evaluate the booleanCompare based on the data type of the left and right nodes.
		if (Left instanceof IntegerDataType && Right instanceof IntegerDataType)
		{
			int intLeft = ((IntegerDataType)Left).getValue();
			int intRight = ((IntegerDataType)Right).getValue();
			
			// Perform the corresponding operation and return the result.
			switch(node.getType())
			{
				case EQUAL:
				{
					return new BooleanDataType(intLeft == intRight);
				}
				case LESSTHAN:
				{
					return new BooleanDataType(intLeft < intRight);
				}
				case GREATERTHAN:
				{
					return new BooleanDataType(intLeft > intRight);
				}
				case LESSEQUAL:
				{
					return new BooleanDataType(intLeft <= intRight);
				}
				case GREATEREQUAL:
				{
					return new BooleanDataType(intLeft >= intRight);
				}
				case NOTEQUAL:
				{
					return new BooleanDataType(intLeft != intRight);
				}
				default:
					throw new Exception("Not a valid symbol");
			}
		}
		else if (Left instanceof RealDataType && Right instanceof RealDataType)
		{
			Float realLeft = ((RealDataType)Left).getValue();
			Float realRight = ((RealDataType)Right).getValue();
			
			// Perform the corresponding operation and return the result.
			switch(node.getType())
			{
				case EQUAL:
				{
					return new BooleanDataType(realLeft == realRight);
				}
				case LESSTHAN:
				{
					return new BooleanDataType(realLeft < realRight);
				}
				case GREATERTHAN:
				{
					return new BooleanDataType(realLeft > realRight);
				}
				case LESSEQUAL:
				{
					return new BooleanDataType(realLeft <= realRight);
				}
				case GREATEREQUAL:
				{
					return new BooleanDataType(realLeft >= realRight);
				}
				case NOTEQUAL:
				{
					return new BooleanDataType(realLeft != realRight);
				}
				default:
					throw new Exception("Not a valid symbol");
			}
		}
		else
		{
			// Throws an exception
			throw new Exception("Not a valid Data Type");
		}
	}	
	
	// This method evaluates a VariableReferenceNode and returns its corresponding value from the interMap.
	private InterpreterDataType variableReferenceNode(HashMap<String, InterpreterDataType> interMap, VariableReferenceNode node) throws Exception
	{
	    // Get the name of the variable from the VariableReferenceNode.
	    String name = node.getName();

	    // Check if the interMap contains the variable. If not, throw an exception.
	    if (!interMap.containsKey(name))
	    {
	        throw new Exception("Doesn't contain item from HashMap");
	    }

	    // If the VariableReferenceNode has an array index expression, get the index value and return the corresponding value from the interMap.
	    if (node.getArrayIndexExpression() != null)
	    {
	        InterpreterDataType arrayIndex = expression(interMap, node.getArrayIndexExpression());
	        return interMap.getOrDefault(name, arrayIndex);
	    }

	    // If the VariableReferenceNode does not have an array index expression, return the corresponding value from the interMap.
	    return interMap.get(name);
	}
	
	// This method evaluates a MathOpNode and returns the result of the corresponding operation.
	private InterpreterDataType MathOpNode(HashMap<String, InterpreterDataType> interMap, MathOpNode mathOp) throws Exception
	{
	    // Evaluate the left and right nodes of the MathOpNode.
	    InterpreterDataType Left = expression(interMap, mathOp.getLeft());
	    InterpreterDataType Right = expression(interMap, mathOp.getRight());

	    // Check if the left and right nodes have the same data type. If not, throw an exception.
	    if (!(Left.getClass().equals(Right.getClass())))
	    {
	        throw new Exception("Not the same data type");
	    }

	    // Evaluate the MathOpNode based on the data type of the left and right nodes.
	    if (Left instanceof IntegerDataType && Right instanceof IntegerDataType)
	    {
	        int intLeft = ((IntegerDataType) Left).getValue();
	        int intRight = ((IntegerDataType) Right).getValue();

	        // Perform the corresponding operation and return the result.
	        switch (mathOp.getType())
	        {
	            case ADDITION:
	            {
	                return new IntegerDataType(intLeft + intRight);
	            }
	            case SUBTRACT:
	            {
	                return new IntegerDataType(intLeft - intRight);
	            }
	            case DIVIDE:
	            {
	                if (intRight == 0)
	                {
	                    throw new Exception("Cannot divide by 0");
	                }
	                return new RealDataType(intLeft / intRight);
	            }
	            case MULTIPLY:
	            {
	                return new IntegerDataType(intLeft * intRight);
	            }
	            case MOD:
	            {
	                if (intRight == 0)
	                {
	                    throw new Exception("Cannot mod by 0");
	                }
	                return new IntegerDataType(intLeft % intRight);
	            }
	            default:
	                throw new Exception("Not a valid symbol");
	        }

	    }
	    else if (Left instanceof RealDataType && Right instanceof RealDataType)
	    {
	        float realLeft = ((RealDataType) Left).getValue();
	        float realRight = ((RealDataType) Right).getValue();

	        // Perform the corresponding operation and return the result.
	        switch (mathOp.getType())
	        {
	            case ADDITION:
	            {
	                return new RealDataType(realLeft + realRight);
	            }
	            case SUBTRACT:
	            {
	                return new RealDataType(realLeft - realRight);
	            }
	            case DIVIDE:
	            {
	                if (realRight == 0)
	                {
	                    throw new Exception("Cannot divide by 0");
	                }
	                return new RealDataType(realLeft / realRight);
	            }
	            case MULTIPLY:
	            {
	                return new RealDataType(realLeft * realRight);
	            }
	            case MOD:
	            {
	                if (realRight == 0)
	                {
	                    throw new Exception("Cannot mod by 0");
	                }
	                return new RealDataType(realLeft % realRight);
	            }
	            default:
	                throw new Exception("Not a valid symbol");
	        }
	    }
	    else if (Left instanceof StringDataType && Right instanceof StringDataType)
	    {
	        String stringLeft = ((StringDataType) Left).getValue();
	        String stringRight = ((StringDataType) Right).getValue();

	        // Concatenate the two strings and return the result.
	        switch (mathOp.getType())
	        {
	            case ADDITION:
	            {
	                return new StringDataType(stringLeft + stringRight);
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
	
	// This method interprets an IfNode by evaluating its condition and executing its statements if the condition is true.
	private void interpretIfNode(HashMap<String, InterpreterDataType> interMap, IfNode ifNode) throws Exception
	{
	    // Evaluate the condition of the IfNode using the expression method.
	    BooleanDataType conditionResult = (BooleanDataType) expression(interMap, ifNode.getCondition());

	    // If the condition is true, interpret the statements of the IfNode.
	    if (conditionResult.getValue()) 
	    {
	        interpretBlock(interMap, ifNode.getStatements());
	    }

	    // If the IfNode has an ElseNode, interpret it recursively.
	    if (ifNode.getElseNode() != null)
	    {
	        interpretIfNode(interMap, ifNode.getElseNode());
	    }    
	}

	// This method interprets a ForNode by iterating through a range of values and executing its statements.
	private void interpretForNode(HashMap<String, InterpreterDataType> interMap, ForNode forNode) throws Exception 
	{
	    // Evaluate the initial value, final value, and step (if provided).
	    IntegerDataType initialValue = (IntegerDataType) expression(interMap, forNode.from());
	    IntegerDataType finalValue = (IntegerDataType) expression(interMap, forNode.to());
	    IntegerDataType stepValue = (IntegerDataType) (forNode.getStatements() != null ? expression(interMap, forNode.getVariable()) : new IntegerDataType(1));

	    // Create a local variable for the loop control variable and set its initial value.
	    String loopVariableName = forNode.getVariable().getName();
	    interMap.put(loopVariableName, initialValue);

	    // Iterate through the loop, executing the corresponding block of statements.
	    while ((stepValue.getValue() > 0 && ((IntegerDataType) interMap.get(loopVariableName)).getValue() <= finalValue.getValue())
	            || (stepValue.getValue() < 0 && ((IntegerDataType) interMap.get(loopVariableName)).getValue() >= finalValue.getValue())) 
	    {
	        interpretBlock(interMap, forNode.getStatements());

	        // Update the loop control variable by the step value.
	        int updatedValue = ((IntegerDataType) interMap.get(loopVariableName)).getValue() + stepValue.getValue();
	        interMap.put(loopVariableName, new IntegerDataType(updatedValue));
	    }
	}

	// This method interprets a RepeatNode by executing its statements a given number of times.
	private void interpretRepeatNode(HashMap<String, InterpreterDataType> interMap, RepeatNode repeatNode) throws Exception 
	{
	    // Evaluate the number of times to repeat the statements.
	    int repeatCount = ((IntegerDataType) expression(interMap, repeatNode.getCondition())).getValue();

	    // Iterate through the statements the specified number of times, interpreting the block each time.
	    for (int i = 0; i < repeatCount; i++) 
	    {
	        interpretBlock(interMap, repeatNode.getStatements());
	    }
	}
	
	// This method evaluates a given constant node and returns its value as a corresponding InterpreterDataType.
	private InterpreterDataType constantNode(HashMap<String, InterpreterDataType> interMap, Node constantNode) throws Exception
	{
	    // Evaluate the constant node using the expression method.
	    InterpreterDataType node = expression(interMap, constantNode);

	    // Convert the node to the appropriate InterpreterDataType based on its type.
	    if (node instanceof StringDataType)
	    {
	        String value = ((StringDataType)node).getValue();
	        return new StringDataType(value);
	    }
	    else if (node instanceof RealDataType)
	    {
	        float value = ((RealDataType)node).getValue();
	        return new RealDataType(value);
	    }
	    else if (node instanceof IntegerDataType)
	    {
	        Integer value = ((IntegerDataType)node).getValue();
	        return new IntegerDataType(value);
	    }
	    else if (node instanceof BooleanDataType)
	    {
	        boolean value = ((BooleanDataType)node).getValue();
	        return new BooleanDataType(value);
	    }
	    else
	    {
	        // If the node is of an unknown type, throw an exception.
	        throw new Exception("Not a valid data type");
	    }
	}

	// This method interprets a WhileNode by repeatedly evaluating its condition and executing its statements while the condition is true.
	private void interpretWhileNode(HashMap<String, InterpreterDataType> interMap, WhileNode whileNode) throws Exception
	{
	    // Evaluate the condition of the WhileNode using the booleanCompare method.
	    BooleanDataType conditionResult = (BooleanDataType) booleanCompare(interMap, whileNode.getCondition());

	    // While the condition is true, interpret the statements of the WhileNode.
	    while (conditionResult.getValue()) 
	    {
	        interpretBlock(interMap, whileNode.getStatements());
	        conditionResult = (BooleanDataType) booleanCompare(interMap, whileNode.getCondition());
	    }
	}
	
	// This method interprets an AssignmentNode by assigning a value to a variable or array.
	private void interpretAssignmentNode(HashMap<String, InterpreterDataType> interMap, AssignmentNode assignmentNode) throws Exception
	{
	    // Get the target of the assignment and evaluate its value using the expression method.
	    VariableReferenceNode target = assignmentNode.getRef();
	    InterpreterDataType value = expression(interMap, assignmentNode.getValue());

	    // Get the name and value of the variable from the interMap.
	    String variableName = target.getName();
	    InterpreterDataType variable = interMap.get(variableName);

	    // If the variable is not defined, throw an exception.
	    if (variable == null) 
	    {
	        throw new Exception("Variable " + variableName + " not defined.");
	    }

	    // If the target has an array index expression, assign the value to the corresponding element of the array.
	    if (target.getArrayIndexExpression() != null) 
	    {
	        // Check that the variable is an array.
	        if (variable instanceof ArrayDataType)
	        {
	            // Get the array and the index of the element to assign.
	            ArrayDataType array = (ArrayDataType) variable;
	            InterpreterDataType index = expression(interMap, target.getArrayIndexExpression());

	            // Check that the index is an integer and assign the value to the element.
	            if (index instanceof IntegerDataType) 
	            {
	                int intIndex = ((IntegerDataType) index).getValue();
	                array.set(intIndex, value);
	            } 
	            else
	            {
	                throw new Exception("Array index must be of type Integer.");
	            }
	        }
	        else
	        {
	            throw new Exception("Variable " + variableName + " is not an array.");
	        }
	    }
	    // If the target does not have an array index expression, assign the value to the variable directly.
	    else 
	    {
	        variable.fromString(value.toString());
	    }
	}
}
