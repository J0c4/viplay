package code.model.instruments.drums;

import code.model.cam.gadgets.videotracker.VITrackerListener;
import code.model.instruments.VIPlayable;
import code.model.instruments.VSound;

/**
 *
 * @author José Carlos
 */
public class VDrumElement implements VIPlayable, VITrackerListener
{
    private String name;
    private VSound sound;

    public VDrumElement(String name, String soundUrl) 
    {
        this.name = name;
        this.sound = new VSound(soundUrl);
    }

    public void changeSound(String SoundNuevo)
    {
        sound = new VSound(SoundNuevo);
    }

    @Override
    public void playSound()
    {
        sound.run();
    }

    @Override
    public void runAction() 
    {
        playSound();
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
