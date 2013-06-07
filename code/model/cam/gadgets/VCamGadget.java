package code.model.cam.gadgets;

import javax.media.Processor;

/**
 *
 * @author Jose Carlos
 */
public abstract class VCamGadget 
{
    public static final String GADGET_VIDEO_TRACKER = "VideoTracker";
    
    protected Processor processor;
    
    /**
     * Runs the required operations to connect and run the Gadget
     */
    public abstract void connect();
    
    /**
     * Retrieves the type of the gadget that implements the method
     */
    public abstract String getGadgetType();
    
    /**
     * Set the processor for the gadget instance
     */
    public void setProcessor(Processor p)
    {
        this.processor = p;
    }
}
