package Total;

/**
 * This is the token class for the different types of token that are in the state machine
 * @author mihailkaramanolev
 *
 */
public class Token 
{
	//this creates the different types of state Types
	public enum Type
	{
		NONE, NUMBER, WORD, ENDOFLINE,
		STRINGLITERAL, CHARACTERLITERAL, PUNCTUATION,
		KEYWORD, COMMENTS, INDENT,
		NOTEQUAL, GREATERTHAN, GREATEREQUAL, EQUAL, 
		LESSTHAN, LESSEQUAL, GETS, COLON, PLUS1,
		MULTIPLY, DIVIDE, SUBTRACT, ADDITION, MINUS1,
		START, END, FOR, WRITE, READ, LEFT, RIGHT,
		SUBSTRING, SQUAREROOT, INTEGERTOREAL, REALTOINTEGER,
		VARIABLES, CONSTANTS, DEFINE, IF, THEN, ELSIF, ELSE,
		WHILE, REPEAT, FROM, VAR, ARRAY, OF,
		STRING, REAL, CHARACTER, BOOLEAN, TO, UNTIL, MOD, DEDENT, 
		
		LPAREN, RPAREN, COMMA, LBRACKET, RBRACKET, SEMICOLON,
		IDENTIFIER, INTEGER;
	}
	
	
	private Type type;
	private String value;
	
	//this initializes type and value variables
	public Token(Type type, String value)
	{
		this.type = type;
		this.value = value;
	}
	
	//this initializes type
	public Token(Type type)
	{
		this.type = type;
	}
	
	public void newToken(char input)
	{
		value = "" + input;
	}
	
	public void addToToken(char input)
	{
		value = value + input;
	}

	//this gets the type of variable it is
	public Type getType()
	{
		return type;
	}
	
	//this get the value of string
	public String getValue() 
	{
		return value;
	}
	
	//this prints out the 
	public String toString()
	{
		return type + "(" + value + ")";
	}
}
