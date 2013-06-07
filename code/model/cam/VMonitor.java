package code.model.cam;

import javax.media.*;

/**
 *
 * @author Jose Carlos
 */
public class VMonitor implements ControllerListener
{
    private boolean stateTransitionOK;
    private Processor processor;
    
    public VMonitor(Processor processor)
    {
        this.stateTransitionOK = true;
        this.processor = processor;   
    }
    
    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     */
    public boolean waitForState(int state) 
    {
        synchronized (this) 
        {
	    try 
            {
		while (processor.getState() != state && stateTransitionOK)
                {
                    this.wait();
                }
	    } 
            catch (Exception e) {}
	}
	return stateTransitionOK;
    }
    
    @Override
    public void controllerUpdate(ControllerEvent evt) 
    {
        if (evt instanceof ConfigureCompleteEvent ||
	    evt instanceof RealizeCompleteEvent ||
	    evt instanceof PrefetchCompleteEvent)
        {
	    synchronized (this) 
            {
		stateTransitionOK = true;
		this.notifyAll();
	    }
	} 
        else 
        {
            if (evt instanceof ResourceUnavailableEvent) 
            {
                synchronized (this) 
                {
                    stateTransitionOK = false;
                    this.notifyAll();
                }
            } 
            else
            {
                if (evt instanceof EndOfMediaEvent) 
                {
                    processor.close();
                    System.exit(0);
                }
            }
        }
    }
}
