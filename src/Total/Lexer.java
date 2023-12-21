package Total;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Total.Token.Type;


/**
 * This class is the lexer to make 
 * @author mihailkaramanolev
 *
 */
public class Lexer
{
	//the different states this machine could be in 
	private enum State
	{
		NONE, NUMBER, DECNUMBER, IDENTIFIER, ENDOFLINE, 
		STRINGLITERAL, CHARACTERLITERAL, PUNCTUATION, COMMENTS,
		PLUS1, MINUS1, GREATEQUAL, LESSNOTEQUAL, COLON;	
	}
	
	//HASHMAP for keywords
	HashMap<String, Type> keyWords = new HashMap<String, Type>();

	//makes the original state the none or "base" one
	State state = State.NONE;
	
	//creates an array of tokens
	List<Token> tokens = new ArrayList<>();
	
	@SuppressWarnings("unused")
	public Lexer()
	{
		
	      keyWords.put("for", Type.FOR);
	      keyWords.put("write", Type.WRITE);
	      keyWords.put("read", Type.READ);
	      keyWords.put("variables", Type.VARIABLES);
	      keyWords.put("constants", Type.CONSTANTS);
	      keyWords.put("define", Type.DEFINE);
	      keyWords.put("if", Type.IF);
	      keyWords.put("then", Type.THEN);
	      keyWords.put("elsif", Type.ELSIF);
	      keyWords.put("else", Type.ELSE);
	      keyWords.put("while", Type.WHILE);
	      keyWords.put("repeat", Type.REPEAT);
	      keyWords.put("from", Type.FROM);
	      keyWords.put("var", Type.VAR);
	      keyWords.put("array", Type.ARRAY);
	      keyWords.put("of", Type.OF);
	      keyWords.put("string", Type.STRING);
	      keyWords.put("real", Type.REAL);
	      keyWords.put("character", Type.CHARACTER);
	      keyWords.put("boolean", Type.BOOLEAN);
	      keyWords.put("to", Type.TO);
	      keyWords.put("until", Type.UNTIL);
	      keyWords.put("mod", Type.MOD);
	      keyWords.put("integer", Type.INTEGER);
	      
	      boolean doWeHaveFor = keyWords.containsKey("for");
	      boolean doWeHaveWrite = keyWords.containsKey("write");
	      boolean doWeHaveRead = keyWords.containsKey("read");
	      boolean doWeHaveVariables = keyWords.containsKey("variables");
	      boolean doWeHaveConstants = keyWords.containsKey("constants");
	      boolean doWeHaveDefine = keyWords.containsKey("define");
	      boolean doWeHaveIf = keyWords.containsKey("if");
	      boolean doWeHaveThen = keyWords.containsKey("then");
	      boolean doWeHaveElsIf = keyWords.containsKey("elsif");
	      boolean doWeHaveElse = keyWords.containsKey("else");
	      boolean doWeHaveWhile = keyWords.containsKey("while");
	      boolean doWeHaveRepeat = keyWords.containsKey("repeat");
	      boolean doWeHaveFrom = keyWords.containsKey("from");
	      boolean doWeHaveVar = keyWords.containsKey("var");
	      boolean doWeHaveArray = keyWords.containsKey("array");
	      boolean doWeHaveOf = keyWords.containsKey("of");
	      boolean doWeHaveString = keyWords.containsKey("string");
	      boolean doWeHaveReal = keyWords.containsKey("real");
	      boolean doWeHaveCharacter = keyWords.containsKey("character");
	      boolean doWeHaveBoolean = keyWords.containsKey("boolean");
	      boolean doWeHaveTo = keyWords.containsKey("to");
	      boolean doWeHaveUntil = keyWords.containsKey("until");
	      boolean doWeHaveMod = keyWords.containsKey("mod");
	      boolean doWeHaveInteger = keyWords.containsKey("integer");
	      
	      Type ForType = keyWords.get("for");
	      Type WriteType = keyWords.get("write");
	      Type ReadType = keyWords.get("read");
	      Type VariablesType = keyWords.get("variables");
	      Type ContantsType = keyWords.get("constants");
	      Type DefineType = keyWords.get("define");
	      Type IfType = keyWords.get("if");
	      Type ThenType = keyWords.get("then");
	      Type ElsifType = keyWords.get("elsif");
	      Type ElseType = keyWords.get("else");
	      Type WhileType = keyWords.get("while");
	      Type RepeatType = keyWords.get("repeat");
	      Type FromType = keyWords.get("from");
	      Type VarType = keyWords.get("var");
	      Type ArrayType = keyWords.get("array");
	      Type OfType = keyWords.get("of");
	      Type StringType = keyWords.get("string");
	      Type RealType = keyWords.get("real");
	      Type CharacterType = keyWords.get("character");
	      Type BooleanType = keyWords.get("boolean");
	      Type ToType = keyWords.get("to");
	      Type UntilType = keyWords.get("until");
	      Type ModType = keyWords.get("mod");
	      Type IntegerType = keyWords.get("integer");
	}
	
	//initiates the current and previous indent level
	int previousIndent = 0;
	
		
	public void lex(String input) throws Exception
	{
		
		//buffer to be able to read/place it on the output
		StringBuilder buffer = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++)
		{
			//creates the char 
			char c = input.charAt(i); 
			
			int currentIndent = getindent(input);

			for (int j = 0; j < currentIndent - previousIndent; j++)
			{
				tokens.add(new Token(Token.Type.INDENT, buffer.toString()));
			}
			for (int j = 0; j < previousIndent - currentIndent; j++)
			{
				tokens.add(new Token(Token.Type.DEDENT, buffer.toString()));
			}
			previousIndent = currentIndent;
			
			//starts the switch states
			switch(state)
			{
				/**
				 * this is the None or "base" state which looks for which state to go next
				 */
				case NONE:
					if (Character.isDigit(c))
					{
						//goes to the number state
						state = State.NUMBER;
						buffer.append(c);
					}

					//checks for different symbols
					else if (c == ':' || c == '>' || c == '<' || c == '=' || c == '+' || c == '-' || c == '/'
							|| c == '*' || c == '(' || c == ')' || c == ',' || c == '[' || c ==']' || c == ';')
					{
						//goes to punctuation state 
	                    state = State.PUNCTUATION;
	                    buffer.append(c);
	                    i--;
					}
					
					//checks for decimals 
					else if (c == '.')
					{
						//goes to the DecNumber state
						state = State.DECNUMBER;
						buffer.append(c);
					}
					
					//checks for quotation marks
					else if (c == '"')
					{
						//goes to the StringLiteral
						state = State.STRINGLITERAL;
						buffer.append(c);
					}
					
					//checks for a single quote
					else if (c == '\'')
					{
						state = State.CHARACTERLITERAL;
						buffer.append(c);
					}
					
					//checks if the character is a letter
					else if (Character.isLetter(c))
					{
						//goes to the word state
						state = State.IDENTIFIER;
						buffer.append(c);
					}
					
					//Checks if its a start of an open bracket 
					else if (c == '{')
					{
						//goes to comments state
						state = State.COMMENTS;
					}
					
					//Checks if character is whitespace
					else if (Character.isWhitespace(c))
					{
						state = State.NONE;
					}
					
					
					//this throws an exception for any variable that is not valid or defined
					else
					{
						throw new Exception("This is an invalid variable: " + c);
					}
						
					break;
				
				/**
				 * this is the number state which is for the numbers in the file
				 */
				case NUMBER:
					if (Character.isDigit(c))
					{
						buffer.append(c);
						break;
					}
					
					else if (c == '.')
					{
						//goes to the DecNumber state
						state = State.DECNUMBER;
						buffer.append(c);
						break;
					}
					
					//adds the state as a token next to the phrase and returns to the base state
					else 
					{
						tokens.add(new Token(Token.Type.NUMBER, buffer.toString()));
						buffer.setLength(0);;
						state = State.NONE;
						i--;
						break;
					}
					
					
					/**
					 * this is the punctuation state that either goes to another state 
					 */
				case PUNCTUATION:
				{
					
					if (c == '<')
					{
						state = State.LESSNOTEQUAL;
						break;
					}
					
					else if (c == '>')
					{
						state = State.GREATEQUAL;
						break;
					}

					else if(c == ':')
					{
						state = State.COLON;
						break;
					}
					
					else if (c == '=')
					{
						tokens.add(new Token(Token.Type.EQUAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == '+')
					{
						tokens.add(new Token(Token.Type.ADDITION, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == '*')
					{
						tokens.add(new Token(Token.Type.MULTIPLY, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					
					else if (c == '/')
					{
						tokens.add(new Token(Token.Type.DIVIDE, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == '-')
					{
						state = State.MINUS1;
						break;
					}
					
					else if (c == '(')
					{
						tokens.add(new Token(Token.Type.LPAREN, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == ')')
					{
						tokens.add(new Token(Token.Type.RPAREN, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == ',')
					{
						tokens.add(new Token(Token.Type.COMMA, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == '[')
					{
						tokens.add(new Token(Token.Type.LBRACKET, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == ']')
					{
						tokens.add(new Token(Token.Type.RBRACKET, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						break;
					}
					
					else if (c == ';')
					{
						tokens.add(new Token(Token.Type.SEMICOLON, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					break;

				}
				
				
				/**
				 * This checks if theres one or two - and outputs the proper token
				 */
				case MINUS1:
				{
					
					if (Character.isDigit(c))
					{
						buffer.append(c);
						state = State.NUMBER;
					}
					else 
					{
						tokens.add(new Token(Token.Type.SUBTRACT, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					break;
				}
				
				/**
				 * This checks for the punctuation after the < sign and puts the proper punctuation 
				 */
				case LESSNOTEQUAL:
				{
					if (c == '>')
					{
						buffer.append(c);
						tokens.add(new Token(Token.Type.NOTEQUAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					
					else if (c == '=')
					{
						buffer.append(c);
						tokens.add(new Token(Token.Type.LESSEQUAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					
					else
					{
						tokens.add(new Token(Token.Type.LESSTHAN, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						i--;
					}
					break;
				}
				
				/**
				 * This checks for the punctuation after the > sign and puts the proper punctuation
				 */
				case GREATEQUAL:
				{
					if (c == '=')
					{
						buffer.append(c);
						tokens.add(new Token(Token.Type.GREATEREQUAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					
					else 
					{
						tokens.add(new Token(Token.Type.GREATERTHAN, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						i--;
					}
					break;
				}
				
				/**
				 * This checks for the punctuation after the : sign and puts the proper punctuation 
				 */
				case COLON:
				{
					if(c == '=')
					{
						buffer.append(c);
						tokens.add(new Token(Token.Type.GETS, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					else
					{
						tokens.add(new Token(Token.Type.COLON, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					break;
				}
					
				/**
				 * this is the DecNumber state for number that have a decimal in them 
				 */
				case DECNUMBER:
				{
					if (Character.isDigit(c))
					{
						buffer.append(c);
					}
					
					//adds the state as a token next to the phrase and returns to the base state
					else 
					{
						tokens.add(new Token(Token.Type.NUMBER, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						i--;
					}
					break;
				}
				
				/**
				 * this is the StringLiteral state for phrases in quotes
				 */
				case STRINGLITERAL:
				{
					
					if(c != '"')
					{
						buffer.append(c);
					}
					
					else if(c == '"')
					{
						buffer.append(c);						
						tokens.add(new Token(Token.Type.STRINGLITERAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}
					
					break;
				}
				
				/**
				 * Thus is the characterLiteral state for phrases in the single quotes
				 */
				case CHARACTERLITERAL:
				{
					
					if (buffer.length() >= 3)
					{					
						state = State.NONE;
						throw new Exception("This is longer than a single character");	
					}
					
					else if (c != '\'')
					{
						buffer.append(c);
					}
					
					else if(c == '\'')
					{
						buffer.append(c);
						tokens.add(new Token(Token.Type.CHARACTERLITERAL, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
					}	
					break;
				}
				
				/**
				 * This is the word state that identifies if something is a word or keyword
				 */
				case IDENTIFIER:
				{
					
					if(Character.isLetterOrDigit(c))
					{
						buffer.append(c);
					} 
					
					else if(keyWords.containsKey(buffer.toString()))
					{
						tokens.add(new Token(keyWords.get(buffer.toString()), buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						i--;	
					}
						
					//adds the state as a token next to the phrase and returns to the base state
					else
					{
						tokens.add(new Token(Token.Type.WORD, buffer.toString()));
						buffer.setLength(0);
						state = State.NONE;
						i--;
					}
				}
					break;
					
				/**
				 * This is the comments state, this makes things in {} comments
				 */
				case COMMENTS:
				{
					if (c != '}' || c == '\n' || Character.isWhitespace(c))
					{
						if (c == '{')
						{
							state = State.NONE;
							throw new Exception("Invalid character: " + c);
						}
						//repeats until it sees }
						else if(c == '}')
						{
							state = State.NONE;
						}
					}
				
					break;
				}
								
					
				//if a non valid state is called, an exception is thrown
				default:
					throw new Exception("Invalid state");
					
			}
		}
		
		//this checks if buffer still has some length in it and adds the remaining tokens
		if (buffer.length() > 0) 
		{
            switch (state)
            {
	            case IDENTIFIER:
	            {
	                if (keyWords.containsKey(buffer.toString()))
	                {
		                tokens.add(new Token(keyWords.get(buffer.toString()), buffer.toString()));
		                buffer.setLength(0);
		                state = State.NONE;
	                } 
	                else
	                {
	                    tokens.add(new Token(Token.Type.WORD, buffer.toString()));
	                    buffer.setLength(0);
	                    state = State.NONE;
	                }
	            }
	            break;
	
	            case NUMBER:
	            {
	                tokens.add(new Token(Token.Type.NUMBER, buffer.toString()));
	                buffer.setLength(0);
	                state = State.NONE;
	            }
	            break;
	            
	            case DECNUMBER:
	            {
	                tokens.add(new Token(Token.Type.NUMBER, buffer.toString()));
	                buffer.setLength(0);
	                state = State.NONE;
	            }
	            break;
	
	            case PUNCTUATION:
	            {
	                buffer.setLength(0);
	                state = State.PUNCTUATION;
	            }
	            break;
	            
			default:
				state = State.NONE;
				break;
            }
		}
		//This puts a 
		buffer.setLength('\0');
		tokens.add(new Token(Token.Type.ENDOFLINE, buffer.toString()));
		
	}
	
	//This takes the indent 
	public int getindent(String input) 
	{
		int j = 0;
		for (char c : input.toCharArray())
		{
			if (c == ' ')
				j++;
			else if (c == '\t') 
				j += 4;		
			else 
				return j/4;
		}
		return j/4;
	}


	//This gets the token in the list
	public List<Token> getTokens()
	{
		return tokens;
	}
	
	//This clears the tokens on the list
	public void clearToken()
	{
		tokens.clear();
	}
}
