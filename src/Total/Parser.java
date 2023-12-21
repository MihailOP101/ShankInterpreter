package Total;

import Total.Token.Type;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Parser 
{
	
	private Queue<Token> tokens;
	
	//This starts a linked list that 
	public Parser(List<Token> tokens)
	{
		LinkedList<Token> linkedlist = new LinkedList<>(tokens);
		this.tokens = (Queue<Token>) linkedlist;
		
		System.out.print(this.tokens);
		System.out.println();
	}
	
	//Checks if the token type matches the 
	private Token matchAndRemove(Type type) throws Exception
	{
		if (tokens.isEmpty() || tokens.peek().getType() != type)
			return null;
		else
			return tokens.remove();	
	}
	
	//This is to replace peek(0).getType()
	private Type quickType()
	{
	    return peek(0).getType();
	}
	
	//This is to check if there is an end of line at the end of the line
	private void expectsEndsOfLine() throws Exception
	{
		Token test = matchAndRemove(Token.Type.ENDOFLINE);
		if (test == null)
		{
			throw new Exception("Expects end of line");
		}
	}
	
	//This looks at the next token of the line 
	private Token peek(int i)
	{
		if (tokens.size() <= i)
			return null;
		else
			return (Token) tokens.toArray()[i];
	}
	
	//This checks the endOfLine and goes to the first sequence in the parser.
	public Node parse() throws Exception
	{	
		Node expression = boolCompare();
		
//		expectsEndsOfLine();
	
		return expression;
	}
	
	//This parses an if Statement 
	public IfNode parseIf() throws Exception
	{
		if (quickType() == Type.IF)
		{
			matchAndRemove(Type.IF);
			
			BooleanCompareNode condition = (BooleanCompareNode) boolCompare();
			List<StatementNode> ifStatements = statements();
			
			
			
			boolean done = false;
			while (!done)
			{
				BooleanCompareNode elsifCondition = (BooleanCompareNode) boolCompare();
				
				List<StatementNode> elsifStatements = statements();
				
				
			}
		}
		return null;
	}
	
	//This parses a while Statement
	public WhileNode parseWhile() throws Exception
	{
		if (matchAndRemove(Type.WHILE) != null)
		{
			BooleanCompareNode condition = (BooleanCompareNode) boolCompare();
			
			ArrayList<StatementNode> whileStatements = statements();
			
			return new WhileNode(condition, whileStatements);
		}
		
		return null;
	}
	
	//This parses a repeat until statement
	public RepeatNode parseRepeat() throws Exception
	{
		//expects 'repeat'
		if (matchAndRemove(Type.REPEAT) != null)
		{
			ArrayList<StatementNode> loopStatements = statements();
			
			//expects 'until'
			if (matchAndRemove(Type.UNTIL) == null)
			{
				throw new Exception("Expected 'until'");
			}
			
			BooleanCompareNode condition = (BooleanCompareNode) boolCompare();
			
			return new RepeatNode(condition, loopStatements);
		}
		
		return null;
	}
	
	//This parses a for statement
	public ForNode parseFor() throws Exception
	{
		if (matchAndRemove(Type.FOR) != null)
		{
			if (matchAndRemove(Type.IDENTIFIER) == null)
			{
				throw new Exception("Expects an identifier");
			}
			
			String loopVariable = peek(0).getValue();
			
			VariableReferenceNode identiVariable = variableReference();
			
			matchAndRemove(Type.IDENTIFIER);
			
			if (matchAndRemove(Type.FROM) == null)
			{
				throw new Exception("Expects 'from'");
			}
			
			Node fromExpression = expression();
			
			if (matchAndRemove(Type.TO) == null)
			{
				throw new Exception("Expects 'to'");
			}
			
			Node toExpression = expression();
			
			ArrayList<StatementNode> loopStatements = statements();
			
			return new ForNode(identiVariable, fromExpression, toExpression, loopStatements);
			
		}
		return null;
	}
	
	
	/**
	 * This makes the compared boolean symbols
	 * @return
	 * @throws Exception
	 */
	private Node boolCompare() throws Exception
	{
		Node left = expression();
		
		if(peek(0).getType() == Type.LESSTHAN ||
				peek(0).getType() == Type.GREATERTHAN ||
				peek(0).getType() == Type.LESSEQUAL ||
				peek(0).getType() == Type.GREATEREQUAL ||
				peek(0).getType() == Type.EQUAL ||
				peek(0).getType() == Type.NOTEQUAL)
			{
				Token op = matchAndRemove(peek(0).getType());
				return new BooleanCompareNode(op.getType(), left, expression());
			} 
		return left;
	}
	
	//This checks for + or - in the 
	private Node expression() throws Exception
	{
		Node left = term();
		
		while(peek(0).getType() == Type.ADDITION || peek(0).getType() == Type.SUBTRACT)
		{
			Token op = matchAndRemove(peek(0).getType());
			left = new MathOpNode(op.getType(), left, term());
		}
		
		return left;
	}
	
	//this looks for the *, /, or mod in the expression
	private Node term() throws Exception
	{
		Node left = factor();
		Token op = peek(0);
		while(peek(0).getType() == Type.MULTIPLY || peek(0).getType() == Type.DIVIDE || peek(0).getType() == Type.MOD)
		{
			matchAndRemove(peek(0).getType());
			return new MathOpNode(op.getType(), left, factor());		
		}
		
		return left;
	}
	
	//based on which token type it is, it does different thing
	private Node factor() throws Exception
	{
		Token op = peek(0);
		
		//this checks the tokens that it recieves
		if (quickType() == Token.Type.NUMBER || quickType() == Token.Type.SUBTRACT || quickType() == Token.Type.LBRACKET || peek(0).getType() == Token.Type.LPAREN)
		{
			switch(op.getType())
			{
				case NUMBER:
				{
					op = matchAndRemove(Token.Type.NUMBER);
					
					if(Float.parseFloat(op.getValue()) % 1 == 0)
					{
						return new IntegerNode(Integer.parseInt(op.getValue()));
					}
					else 
					{
						return new RealNode(Float.parseFloat(op.getValue()));
					}
				}
					
				case SUBTRACT:
				{	
					op = matchAndRemove(Type.SUBTRACT);
					Node inner = factor();
					if(op != null)
					{
						op = matchAndRemove(Type.NUMBER);
					}
					return new MathOpNode(Token.Type.SUBTRACT, new IntegerNode(0), inner);

				}
				
				case LPAREN:
				{
					op = matchAndRemove(Type.LPAREN);
					Node parenExpress = expression();
					if(op != null)
					{						
						matchAndRemove(Type.RPAREN);					
					}
					return parenExpress;
				}
					
				case LBRACKET:
				{
					op = matchAndRemove(Type.LBRACKET);
					Node bracketBool = boolCompare();
					String name = peek(0).getValue();
					if(op != null)
					{
						matchAndRemove(Type.RBRACKET);
					}
					return new VariableReferenceNode(name, bracketBool);
				}
				
				case WORD:
				{
					return variableReference();
				}
				default:
					break;
					
			}
		}
		
	 throw new Exception();
	}

	//This makes the entire assignment
	private AssignmentNode assignment() throws Exception
	{
		if (quickType() != Type.WORD)
		{
			return null;
		}
		
		VariableReferenceNode target = variableReference();
	    if (quickType() == Type.GETS) 
	    {
	        Node value = boolCompare();
	        return new AssignmentNode(target, value);
	    }
	    else 
	    {
	        throw new Exception("Expected ':=' for assignment.");
	    }
		
	}

	//This 
	private VariableReferenceNode variableReference() throws Exception
	{
		if(peek(0).getType() != Type.WORD)
		{
			return null;
		}
		
		String name = peek(0).getValue();
		
		Node op = null;
		if (quickType() == Type.LBRACKET)
		{
			matchAndRemove(Type.LBRACKET);
			op = expression();
			if (quickType() != Type.RBRACKET)
			{
				throw new Exception("Missing right bracket");
			}
			matchAndRemove(Type.RBRACKET);
		}
		
		return new VariableReferenceNode(name, op);
		
//		String identifier = consume(Type.WORD).getText();
//	    Node indexExpression = null;
//
//	    if (quickType() == Type.LBRACKET) 
//	    {
//	        indexExpression = expression();
//	        matchAndRemove(Type.RBRACKET);
//	    }
//
//	    return new VariableReferenceNode(identifier, indexExpression);
	}
	
	
	/**
	 * This is the function that finds what is being 
	 * @return
	 * @throws Exception
	 */
	private Node function() throws Exception
	{
		if (matchAndRemove(Type.DEFINE) == null)
		{
			return null;
		}
		
		if(quickType() != Type.WORD)
		{
			throw new Exception("Expects the name of the function");
		}
		
		String name = peek(0).getValue();
		matchAndRemove(Type.WORD);
		
		if (matchAndRemove(Type.LPAREN) == null)
		{
			throw new Exception("Expects left Parenthesis");
		}
		
		//String name = peek(0).getValue();
	
		ArrayList<VariableNode> parameters = parameterDeclarations();	
		
		if (quickType() == Type.CONSTANTS)
		{
			matchAndRemove(Type.CONSTANTS);
		}
		
		ArrayList<VariableNode> constants = constantDeclarations();
		
		if(quickType() == Type.VARIABLES)
		{
			matchAndRemove(Type.VARIABLES);
			System.out.println("bchkjabkh");
		}
		
		ArrayList<VariableNode> variables = variableDeclarations();
		
//		ArrayList<StatementNode> statements = statements();

		FunctionNode functionNode = new FunctionNode(name, parameters, constants, variables, null);
		
		return functionNode;
	}
	
	//This makes a statement and checks for indends and dedents
	private ArrayList<StatementNode> statements() throws Exception
	{
		ArrayList<StatementNode> statementList = new ArrayList<>();
		
		matchAndRemove(Type.INDENT);
		
		StatementNode statement = statement();
		
		while (statement != null)
		{
			statementList.add(statement);
			statement = statement();
		}
		
		matchAndRemove(Type.DEDENT);
		
		return statementList;
	}

	//this returns the assignment
	private StatementNode statement() throws Exception
	{
		return assignment();
	}
	
	//Makes the states for the parameter 
	private enum State
	{
		NONE, WORD, IDENTIFIER, SYMBOL, DATATYPE, END;	
	}
	
	State state = State.NONE;
	/**
	 * This checks for the words that would be in a parameter
	 * @return This returns the parameters ArrayList 
	 * @throws Exception
	 */
	private ArrayList<VariableNode> parameterDeclarations() throws Exception 
	{
		//Make a state machine for the different 
		ArrayList<VariableNode> parameters = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		
		boolean isVar = false;
		
		while (tokens.size() >= 1)
			
			switch(state)
			{
			
				case NONE:
				{
					if (quickType() == Type.VAR)
					{
						state = State.WORD;
						isVar = true;
						matchAndRemove(Type.VAR);
						break;
					}
					if (quickType() == Type.WORD)
					{
						name.add(peek(0).getValue());
						state = State.WORD;
						matchAndRemove(Type.WORD);
						break;
					}
					if (peek(0).getType() == Type.RPAREN)
					{
						state = State.END;
						matchAndRemove(Type.RPAREN);
						break;
					}
				}
				case WORD:
				{
					if (quickType() == Type.COMMA)
					{
						state = State.WORD;
						matchAndRemove(Type.COMMA);
						break;
					}
					if (quickType() == Type.WORD)
					{
						name.add(peek(0).getValue());
						state = State.WORD;
						matchAndRemove(Type.WORD);
						break;
					}
					if (quickType() == Type.COLON)
					{
						state = State.SYMBOL;
						matchAndRemove(Type.COLON);
						break;
					}
				}
				case SYMBOL:
				{
					System.out.println(quickType());
					if (quickType() == Type.INTEGER)
					{
						System.out.println(quickType());
						state = State.SYMBOL;
						matchAndRemove(Type.INTEGER);
						break;
					}
					if (quickType() == Type.REAL)
					{
						System.out.println(quickType());
						state = State.SYMBOL;
						matchAndRemove(Type.REAL);
						break;
					}
					if (quickType() == Type.SEMICOLON)
					{
						state = State.NONE;
						matchAndRemove(Type.SEMICOLON);
						break;
					}
					if (quickType() == Type.RPAREN)
					{
						state = State.END;
						matchAndRemove(Type.RPAREN);
						break;
					}
				}
				case END:
				{
					
					if (quickType() != Type.ENDOFLINE)
					{
						throw new Exception("Nothing should be after the right paranthesis");
					}
					if (quickType() == Type.ENDOFLINE)
					{
						System.out.println(quickType());
						parameters.add(new VariableNode(name.toString(), null, isVar));
						expectsEndsOfLine();
						System.out.println(name);
						
						return parameters;
					}	
				}
				default:
				{
					break;
				}
			}
		
		return parameters;
	}
	
	/**
	 * This checks the different parts after it is declared that it's defining variables
	 * @return
	 * @throws Exception
	 */
	private ArrayList<VariableNode> variableDeclarations() throws Exception
	{
		ArrayList<VariableNode> variables = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		
		boolean status = true;
		State state = State.NONE;
		
		while(tokens.size() > 0)
		{
			switch (state)
			{
				case NONE:
				{

					if(peek(0).getType() == Type.WORD)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.WORD);
						state = State.SYMBOL;
						break;
					}
				}
				case SYMBOL:
				{
					if (quickType() == Type.COMMA)
					{
						matchAndRemove(Type.COMMA);
						state = State.NONE;
						break;
					}
					if (peek(0).getType() == Type.COLON)
					{
						matchAndRemove(Type.COLON);
						state = State.DATATYPE;
						break;
					}
				}
				case DATATYPE:
				{
					if (peek(0).getType() == Type.INTEGER)
					{
						
						matchAndRemove(Type.INTEGER);
						state = State.END;
						break;
					}
					if (quickType() == Type.STRING)
					{
						
						matchAndRemove(Type.STRING);
						state = State.END;
						break;
					}
					if (quickType() == Type.REAL)
					{
						matchAndRemove(Type.REAL);
						state = State.END;
						break;
					}
					if (quickType() == Type.BOOLEAN)
					{
						matchAndRemove(Type.BOOLEAN);
						state = State.END;
						break;
					}
				}
				case END:
				{
					if (quickType() == Type.ENDOFLINE)
					{
						variables.add(new VariableNode(name.toString(), null, status));
						expectsEndsOfLine();
						return variables;
					}
				}
				default:
					break;
			}
		}
		
		return variables;
	}
	
	/**
	 * This is checking once it detects that its defining a variable as a constant
	 * @return this returns the constant ArrayList
	 * @throws Exception for whenever we don't finish a Declaration in proper way
	 */
	private ArrayList<VariableNode> constantDeclarations() throws Exception
	{
		ArrayList<VariableNode> constants = new ArrayList<VariableNode>();
		
		ArrayList<String> name = new ArrayList<>();
		boolean status = true;
		State state = State.NONE;
		
		while (tokens.size() > 1)
		{
			switch (state)
			{
				case NONE:
				{
					if(peek(0).getType() == Type.WORD)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.WORD);
					}
					if(peek(0).getType() == Type.EQUAL)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.EQUAL);
						state = State.IDENTIFIER;
						break;
					}
				}
				case IDENTIFIER:
				{
					if (quickType() == Type.NUMBER)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.NUMBER);
						state = State.END;
						break;
					}
					if (quickType() == Type.STRINGLITERAL)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.STRINGLITERAL);
						state = State.END;
						break;
					}
					if (quickType() == Type.CHARACTERLITERAL)
					{
						name.add(peek(0).getValue());
						matchAndRemove(Type.CHARACTERLITERAL);
						state = State.END;
						break;
					}
				}
				case END:
				{
					if (quickType() == Type.COMMA)
					{
						matchAndRemove(Type.COMMA);
						state = State.NONE;
						break;
					}
					if (quickType() == Type.ENDOFLINE)
					{
						constants.add(new VariableNode(name.toString(), null, status));
						expectsEndsOfLine();
						return constants;
					}
				}
			default:
				break;
				
			}
		}
		
		return constants;
	}
}
