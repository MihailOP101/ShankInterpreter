package Total;
import java.util.ArrayList;

public class FunctionNode extends Node
{
	private String name;   
	private ArrayList<VariableNode> parameters;   
	private ArrayList<VariableNode> constants;
	private ArrayList<VariableNode> variables;    
	private ArrayList<StatementNode> statements;   
	
	public FunctionNode(String name, 
			ArrayList<VariableNode> parameters,
			ArrayList<VariableNode> constants, 
			ArrayList<VariableNode> variables,
			ArrayList<StatementNode> statements)
	{      
		this.name = name;    
		this.parameters = parameters;
		this.constants = constants;	
		this.variables = variables;
		this.statements = statements;   
	}

	
	
	public String getName() 
	{
		return name;   
	}

	public ArrayList<VariableNode> getParameters()
	{
		return parameters;   
	}

	public ArrayList<VariableNode> getConstants()
	{
		return constants;    
	}

	public ArrayList<VariableNode> getVariables() 
	{
		return variables;   
	}

	public ArrayList<StatementNode> getStatements() 
	{
		return statements;    
	}
	
	public boolean isVariadic()
	{
		return false;
	}
	
	public String toString()
	{
	 	return "FunctionNode [name=" + name + ", parameters=" + this.parameters + ", constants=" 
				+ this.constants	+ ", variables=" + this.variables + ", statements=" + this.statements + "]";
	}
}
