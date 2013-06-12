package code.controllers;

import code.gui.VMainWindow;
import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.control.VRecRunPane;
import code.gui.railboard.VRailBoard;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jose Carlos
 */
public class VRecRunController implements MouseListener
{
    private VRecRunPane pane;
    
    private JButton rec;
    private JButton play;
    private JButton stop;
    private JButton loadSecuence;
    private JLabel fileName;
    
    private VMainWindow mainWindow;
    private VRailBoard railBoard;
    private VInstrumentBuilderPanel builder;

    public VRecRunController(VRecRunPane pane) 
    {
        this.pane = pane;
        this.rec = pane.getRec();
        this.play = pane.getPlay();
        this.stop = pane.getStop();
        this.loadSecuence = pane.getLoadSecuence();
        this.fileName = pane.getFileName();
    }
    
    public void setWindow(VMainWindow window)
    {
        if (window != null)
        {
            this.mainWindow = window;
            this.railBoard = window.getRailBoard();
            this.builder = window.getBuilder();
        }
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

    @Override
    public void mousePressed(MouseEvent e) 
    {
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
}
