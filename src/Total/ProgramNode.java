package Total;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This makes a hashmap from the functionNode and returns that hashmap
 * @author mihailkaramanolev
 *
 */
public class ProgramNode extends Node
{
	public HashMap<String, FunctionNode> functions = new HashMap<>();
	public ArrayList<String> names = new ArrayList<>();
    
	public ProgramNode(String stri, FunctionNode funct)
	{
		functions.put(stri, funct);
	}
	
	public void addFunction(String name, FunctionNode functionNode)
	{
		functions.put(name, functionNode);
	}
	
	public FunctionNode getFunction(String name)
	{
		return functions.get(name);
	}
	
	public String toString() 
	{
		String returnString = "";
		for (String j: functions.keySet())
		{
			returnString += "Name: " + j + " Function: " + functions.get(j) + " ";
		}
		return returnString;
	}

}