package Total;

public class CharacterNode extends Node
{

	private char Character;
	
	public CharacterNode(char Character)
	{
		this.Character = Character;
	}
	
	public char getCharacter()
	{
		return Character;
	}

	public String toString()
	{
		return Float.toString(Character);
	}
}
