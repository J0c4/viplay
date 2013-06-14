package code.controllers;

import code.gui.control.VRecRunPane;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jose Carlos
 */
public class VRecRunController extends VControlCenterController
{
    private VRecRunPane pane;
    
    private JButton rec;
    private JButton play;
    private JButton stop;
    private JButton loadSecuence;
    private JLabel fileName;

    public VRecRunController(VRecRunPane pane) 
    {
        this.pane = pane;
        this.rec = pane.getRec();
        this.play = pane.getPlay();
        this.stop = pane.getStop();
        this.loadSecuence = pane.getLoadSecuence();
        this.fileName = pane.getFileName();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        if (clicked.isEnabled())
        {
            if (clicked.equals(this.rec))
            {
               
            }
            else if (clicked.equals(this.play))
            {
                
            }
            else if (clicked.equals(this.stop))
            {
                
            }
            else if (clicked.equals(this.loadSecuence))
            {
                
            }
        }
    }
}
