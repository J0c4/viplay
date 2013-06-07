package code.model.cam;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Jose Carlos
 */
public class VCamScreen extends JPanel
{
    private Component mainScreen;
    private Component controlPanel;
    
    public VCamScreen(Component mainScreen, Component controlPanel)
    {
        this.mainScreen = mainScreen;
        this.controlPanel = controlPanel;
        init();
    }
    
    private void init()
    {
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, mainScreen);
        this.add(BorderLayout.NORTH, controlPanel);
    }
}
