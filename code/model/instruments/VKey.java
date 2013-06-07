package code.model.instruments;

import java.io.Serializable;

/**
 *
 * @author Jose Carlos
 */
public class VKey implements Serializable
{
    private int key;
    private String keySymbol;

    public VKey(int key) 
    {
        this.key = key;
    }
    
    public VKey(int key, String keySymbol) 
    {
        this.key = key;
        this.keySymbol = keySymbol;
    }

    public int getKey() 
    {
        return key;
    }

    public String getKeySymbol() 
    {
        return keySymbol;
    }
    
    @Override
    public String toString()
    {
        return keySymbol;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if (obj instanceof VKey)
        {
            isEqual = ((VKey)obj).key == this.key;
        }
        return isEqual;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 11 * hash + this.key;
        return hash;
    }
}
