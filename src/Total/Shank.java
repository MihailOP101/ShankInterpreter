package Total;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 
 * @author mihailkaramanolev
 *
 */
public class Shank 
{
	static int lineNum = 0;
	
	public static void main(String[] args) throws Exception
	{
		// Temporary step to print each token once lexing is complete
		if (args.length != 1)
		{
			System.out.println("You need exactly one argument");
			
			//this exits the program
			System.exit(1);
		}
		
		//This gets the path towards "test.shank" and then reading the file
		Path myPath = Paths.get(args[0]);
		List <String> lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);
		
		//This creates a new Lexer 
		Lexer lexer = new Lexer();
		Lexer lexAll = new Lexer();

		
		//Gets each line as a string
		for (String line : lines) 
		{
			try 
			{
				lineNum++;
			} 
			catch (Exception e) 
			{
				
				System.out.println("Exception: " + e.getMessage());
			}
            try 
            {
            	System.out.print("Line count: " + lineNum + "\t");
            	
            	//Puts each line through the lexer and makes it into a list
                lexer.lex(line);
                lexAll.lex(line);
        		
                
        		//This initiates the parser and prints it out
        		List<Token> tokens = lexer.getTokens();
        		
                
                //prints out each token
                for (Token token : tokens) 
                {
                    System.out.print(token + " ");       
                }     
               
                //Makes a new line and clears out the token from the line
                System.out.println();
                lexer.clearToken();
                tokens.clear();
                
            } 
            
            // catches an exception 
            catch (Exception e) 
            {
//            	e.printStackTrace();
                System.out.println("Exception: " + e);
            }
        }
		
		Parser Parser = new Parser(lexAll.getTokens()); 
		Node result = Parser.parse();
		lexAll.clearToken();
		System.out.println(result.toString());
		
		// Perform semantic analysis
//	    @SuppressWarnings("unused")
//		SemanticAnalysis semanticAnalysis = new SemanticAnalysis((ProgramNode)result);
	    
//	    // Interpret the program
//		Interpreter interpreter = new Interpreter((ProgramNode)result);
		
		//This calls the program Node to create a new HashMap
		ProgramNode programNode = new ProgramNode(null, null);
		
		//This populates the HashMaps
		BuiltInRead readNode = new BuiltInRead(null, null, null, null, null);
		BuiltInWrite writeNode = new BuiltInWrite(null, null, null, null, null);
		BuiltInLeft leftNode = new BuiltInLeft(null, null, null, null, null);
		BuiltInRight rightNode = new BuiltInRight(null, null, null, null, null);
		BuiltInSubstring subStringNode = new BuiltInSubstring(null, null, null, null, null);
		BuiltInSquareRoot squareRootNode = new BuiltInSquareRoot(null, null, null, null, null);
		BuiltInGetRandom getRandomNode = new BuiltInGetRandom(null, null, null, null, null);
		BuiltInIntegerToReal integerToRealNode = new BuiltInIntegerToReal(null, null, null, null, null);
		BuiltInRealToInteger realToIntegerNode = new BuiltInRealToInteger(null, null, null, null, null);
		BuiltInStart startNode = new BuiltInStart(null, null, null, null, null);
		BuiltInEnd endNode = new BuiltInEnd(null, null, null, null, null);
		
		//This adds the functions to the programNode
		programNode.addFunction(readNode.getName(), readNode);
		programNode.addFunction(writeNode.getName(), writeNode);
		programNode.addFunction(leftNode.getName(), leftNode);
		programNode.addFunction(rightNode.getName(), rightNode);
		programNode.addFunction(subStringNode.getName(), subStringNode);
		programNode.addFunction(squareRootNode.getName(), squareRootNode);
		programNode.addFunction(getRandomNode.getName(), getRandomNode);
		programNode.addFunction(integerToRealNode.getName(), integerToRealNode);
		programNode.addFunction(realToIntegerNode.getName(), realToIntegerNode);
		programNode.addFunction(startNode.getName(), startNode);
		programNode.addFunction(endNode.getName(), endNode);
	}
}
