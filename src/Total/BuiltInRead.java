package Total;

import java.util.ArrayList;
import java.util.Scanner;

public class BuiltInRead extends FunctionNode
{

	public BuiltInRead(String name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants,
			ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) 
	{
		super(name, parameters, constants, variables, statements);
	}
	
	public void Execute (ArrayList<InterpreterDataType> parameters)
	{
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNext()) //continues until nothing left
		{
			String input = scan.next(); //gets the next word
			String accumulator = "";
			int j = 0;
			
			for (int i = 0; i < input.length(); i++)
			{
				char c = input.charAt(i);
				
				//This checks the amount of words that there are in the input
				if (Character.isWhitespace(c))
				{
					//gets accumulator and resets the process
					parameters.get(j).fromString(accumulator);
					accumulator = "";
					j++;
				}
				else
				{
					accumulator += c;
				}
			}
			//gets remaining characters in accumulator
			parameters.get(j).fromString(accumulator);
			
		}
		//Finishes scanning 
		scan.close();	
	}
	
	public boolean isVariadic()
	{
		return true;
	}
}
