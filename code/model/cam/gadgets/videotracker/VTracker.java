package code.model.cam.gadgets.videotracker;

import code.model.cam.gadgets.VCamGadget;
import javax.media.Codec;
import javax.media.control.TrackControl;
import javax.media.format.VideoFormat;

/**
 *
 * @author Jose Carlos
 */
public class VTracker extends VCamGadget
{
    private VMotionDetectionEffect motionEffect;
    private TimeStampEffect timeStampEffect;
    
    public VTracker()
    {
        this.motionEffect = new VMotionDetectionEffect();
        this.timeStampEffect = new TimeStampEffect();
    }
    
    public boolean addActionSection(int x, int y, VITrackerListener action)
    {
        return this.motionEffect.addActionSection(x, y, action);
    }
    
    @Override
    public void connect() 
    {
        TrackControl[] controllers = processor.getTrackControls();
        Codec[] codecs = { this.motionEffect, this.timeStampEffect };
        for (TrackControl tracker: controllers)
        {
            if (tracker.getFormat() instanceof VideoFormat)
            {
                try
                {
                    tracker.setCodecChain(codecs);
                }
                catch(Exception e){}
                break;
            }
        }
    }
    
    @Override
    public String getGadgetType()
    {
        return VCamGadget.GADGET_VIDEO_TRACKER;
    }
}
