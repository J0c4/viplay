package code.model.instruments;

import code.model.instruments.drums.VDrumKit;

/**
 *
 * @author Jose Carlos
 */
public class VInstrumentBuilder 
{
    public final static String DRUM_KIT_INSTRUMENT =  "Drum Kit";
    
    private VInstrument build;
    private String type;
    
    private VIPlayable element;
    private VKey key;
    
    public VInstrumentBuilder(String type)
    {
        this.type = type;
        buildInstrument();
    }

    public VInstrumentBuilder(VInstrument build) 
    {
        this.build = build;
        this.type = build.getType();
    }

    public VIPlayable getElement() 
    {
        return element;
    }

    public VKey getKey() 
    {
        return key;
    }

    public void setElement(VIPlayable element) 
    {
        this.element = element;
    }

    public void setKey(VKey key) 
    {
        this.key = key;
    }

    public VInstrument getBuild() 
    {
        return build;
    }
    
    public boolean addPlayableElement()
    {
        return this.build.addElement(this.key, this.element);
    }
    
    public void reset()
    {
        buildInstrument();
    }

    private void buildInstrument() 
    {
        if (this.type.equals(DRUM_KIT_INSTRUMENT))
        {
            this.build = new VDrumKit();
        }
        this.element = null;
        this.key = null;
    }
}
