package Total;

import java.util.ArrayList;
import java.util.List;

//This get the input Array and returns it 
public class ArrayDataType extends InterpreterDataType 
{
    private List<InterpreterDataType> values;

    public ArrayDataType()
    {
        values = new ArrayList<>();
    }

    public ArrayDataType(List<InterpreterDataType> initialValues) 
    {
        values = new ArrayList<>(initialValues);
    }

    public InterpreterDataType get(int index) 
    {
        return values.get(index);
    }

    public void set(int index, InterpreterDataType value)
    {
        values.set(index, value);
    }

    public int size() 
    {
        return values.size();
    }

    public String toString() 
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.size(); i++) 
        {
            sb.append(values.get(i).toString());
            if (i < values.size() - 1) 
            {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void fromString(String input) 
    {
        throw new UnsupportedOperationException("ArrayDataType cannot use fromString directly.");
    }

	public Object getType() 
	{
		return values;
	}
}
