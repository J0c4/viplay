package code.model.instruments;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jose Carlos
 */
public abstract class VInstrument  implements Serializable
{
    protected String type;
    protected Map<VKey, VIPlayable> elements;
    
    public VInstrument()
    {
        this.elements = new HashMap<VKey, VIPlayable>();
    }

    public String getType() 
    {
        return type;
    }

    public Map<VKey, VIPlayable> getElements() 
    {
        return elements;
    }
    
    public void playElement(VKey elementKey)
    {
        VIPlayable element = this.elements.get(elementKey);
        if (element != null)
        {
            element.playSound();
        }
    }
    
    public boolean addElement(VKey key, VIPlayable element)
    {
        boolean isInvalidElement = elements.containsKey(key) || 
                                   element == null ||
                                   key == null;
        if (!isInvalidElement)
        {
            this.elements.put(key, element);
        }
        return !isInvalidElement;
    }
    
    public boolean isBuilt()
    {
        return !elements.isEmpty();
    }
    
    public abstract VIPlayable[] getElementOptions();
}
