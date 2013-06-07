package code.model.instruments;

import java.io.Serializable;
import javax.media.*;
/**
 *
 * @author Jose Carlos
 */
public class VSound implements Serializable
{
    private String source;

    public VSound(String soundURL)
    {
        source = soundURL;
    }

    public void run()
    {
        final Player player;
        MediaLocator locator = new MediaLocator(ClassLoader.getSystemResource(source));
        try
        {
            player = Manager.createPlayer(locator);
            player.addControllerListener(new ControllerListener() 
            {
                @Override
                public void controllerUpdate(ControllerEvent ce) 
                {
                        if(ce instanceof EndOfMediaEvent)
                        {
                            player.stop();
                            player.close();
                        }
                }
            });
            player.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }  
}
