package code.controllers;

import code.gui.control.VPlayModePane;
import code.gui.railboard.VInstrumentPanel;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Jose Carlos
 */
public class VPlayModeController extends VControlCenterController
{
    private VPlayModePane pane;
    
    private JButton start;
    private JButton stop;
    
    public VPlayModeController(VPlayModePane pane) 
    {
        this.pane = pane;
        this.start = pane.getStart();
        this.stop = pane.getStop();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        VInstrumentPanel instrumentsPanel = this.railBoard.getInstrumentPanel();
        if (clicked.isEnabled())
        {
            if (clicked.equals(this.start))
            {
                if (loadInstrument())
                {
                    instrumentsPanel.setVisible(true);
                    instrumentsPanel.setFocusable(true);
                    this.start.setEnabled(false);
                    this.stop.setEnabled(true);
                    this.builder.switchPanel();
                }
            }
            else if (clicked.equals(this.stop))
            {
                if (unloadInstrument())
                {
                    instrumentsPanel.setVisible(false);
                    this.start.setEnabled(true);
                    this.stop.setEnabled(false);
                    this.builder.switchPanel();
                }
            }
        }
    }
}
