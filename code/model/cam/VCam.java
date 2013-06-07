package code.model.cam;

import code.model.cam.gadgets.VCamGadget;
import java.util.HashMap;
import java.util.List;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Processor;

/**
 *
 * @author Jose Carlos
 */
public class VCam 
{
    private static final String DEFAULT_CAM_NAME = "vfw://0";
    
    /* Object monitor to connect cam, do not confuse with video interface */
    private VMonitor monitor;
    /* Video cam output screen, inherited by JPanel */
    private VCamScreen output;
    /* API cam processor */
    private Processor processor;
    /* Cam gadgets map, key: gadget type, value: gadget object */
    private HashMap<String, VCamGadget> gadgets;
    
    public VCam()
    {
        init(null);
    }
    
    public VCam(List<VCamGadget> gadgets)
    {
        init(gadgets);
    }

    public void turnOn() 
    {
        this.processor.start();
    }
    
    public void turnOff()
    {
        this.processor.close();
    }

    public VCamScreen getScreen() 
    {
        return output;
    }
    
    public VCamGadget getGadget(String gadgetType)
    {
        return this.gadgets.get(gadgetType);
    }

    private void init(List<VCamGadget> gadgets)
    {
        try
        {
            MediaLocator locator = new MediaLocator(DEFAULT_CAM_NAME);

            this.gadgets = new HashMap<String, VCamGadget>();
            this.processor = Manager.createProcessor(locator);
            this.monitor = new VMonitor(processor);

            this.processor.addControllerListener(monitor);
            this.processor.configure();
            
            this.monitor.waitForState(Processor.Configured);
            this.processor.setContentDescriptor(null);

            connectGadgets(gadgets);
            this.processor.prefetch();
            this.monitor.waitForState(Processor.Prefetched);
            
            this.output = new VCamScreen(this.processor.getVisualComponent(), 
                                         this.processor.getControlPanelComponent());
        }
        catch(Exception e){}
    }

    private void connectGadgets(List<VCamGadget> gadgets) 
    {
        if (gadgets != null && !gadgets.isEmpty())
        {
            for (VCamGadget gadget: gadgets)
            {
                gadget.setProcessor(this.processor);
                gadget.connect();
                this.gadgets.put(gadget.getGadgetType(), gadget);
            }
        }
    }
}
