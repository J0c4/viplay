package code.model.recorder;

import code.model.instruments.VKey;
import java.io.Serializable;

/**
 *
 * @author Jose Carlos
 */
public class VRecord implements Serializable
{
    private VKey key;
    private long startTime;

    public VRecord(VKey key, long startTime) 
    {
        this.key = key;
        this.startTime = startTime;
    }

    public long getStartTime() 
    {
        return startTime;
    }

    public VKey getKey() 
    {
        return key;
    }
}
