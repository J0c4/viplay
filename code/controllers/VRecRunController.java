package code.controllers;

import code.gui.control.VRecRunPane;
import code.model.recorder.VRecorder;
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
    
    private VRecorder recorder;

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
                if (loadInstrument())
                {
                   this.recorder = new VRecorder();
                   this.rec.setEnabled(false);
                   this.loadSecuence.setEnabled(false);
                   this.stop.setEnabled(true);
                }
            }
            else if (clicked.equals(this.play))
            {
                
            }
            else if (clicked.equals(this.stop))
            {
                if (unloadInstrument())
                {
                    this.stop.setEnabled(false);
                    this.rec.setEnabled(true);
                    this.loadSecuence.setEnabled(true);
                }
            }
            else if (clicked.equals(this.loadSecuence))
            {
                
            }
        }
    }
}
