package code.controllers;

import code.gui.VMainWindow;
import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.railboard.VInstrumentPanel;
import code.gui.railboard.VRailBoard;
import code.model.instruments.VInstrument;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Carlos
 */
public class VMainWindowController extends VAbstractController implements MouseListener
{
    private VMainWindow mainWindow;
    
    public VMainWindowController(VMainWindow railBoard) 
    {
        this.mainWindow = railBoard;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        JButton start = this.mainWindow.getControl().getStart();
        JButton stop = this.mainWindow.getControl().getStop();
        VInstrumentPanel instrumentsPanel = this.mainWindow.getRailBoard().getInstrumentPanel();
        VInstrumentBuilderPanel builder = this.mainWindow.getBuilder();
        if (clicked.isEnabled())
        {
            if (clicked.equals(start))
            {
                if (loadInstrument())
                {
                    instrumentsPanel.setVisible(true);
                    instrumentsPanel.setFocusable(true);
                    start.setEnabled(false);
                    stop.setEnabled(true);
                    builder.switchPanel();
                }
            }
            else if (clicked.equals(stop))
            {
                if (unloadInstrument())
                {
                    instrumentsPanel.setVisible(false);
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    builder.switchPanel();
                }
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

    private boolean loadInstrument() 
    {
        VInstrumentBuilderController builderController = this.mainWindow.getBuilder().getController();
        VInstrument instrument = builderController.getInstrument();
        boolean isValidInstrument = instrument.isBuilt();
        if (isValidInstrument)
        {
            VRailBoard railBoard = this.mainWindow.getRailBoard();
            railBoard.getController().loadInstrument(instrument);
        }
        else
        {
            JOptionPane.showInternalMessageDialog(this.mainWindow.getContentPane(), "You must add at least one element to the instrument");
        }
        return isValidInstrument;
    }

    private boolean unloadInstrument() 
    {
        boolean unloaded = false;
        try
        {
            VRailBoard railBoard = this.mainWindow.getRailBoard();
            railBoard.getController().unloadInstrument();
            unloaded = true;
        }
        catch (Exception e)
        {}
        
        return unloaded;
    }
}
