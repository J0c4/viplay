package code.controllers;

import code.gui.VMainWindow;
import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.control.VPlayModePane;
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
    private VRailBoard railBoard;
    private VInstrumentBuilderPanel builder;
    private VPlayModePane control;
    
    public VMainWindowController(VMainWindow railBoard) 
    {
        this.mainWindow = railBoard;
        this.railBoard = railBoard.getRailBoard();
        this.builder = railBoard.getBuilder();
        this.control = railBoard.getControl();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        JButton start = this.control.getStart();
        JButton stop = this.control.getStop();
        VInstrumentPanel instrumentsPanel = this.railBoard.getInstrumentPanel();
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
                    this.builder.switchPanel();
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
        VInstrumentBuilderController builderController = this.builder.getController();
        VInstrument instrument = builderController.getInstrument();
        boolean isValidInstrument = instrument.isBuilt();
        if (isValidInstrument)
        {
            this.railBoard.getController().loadInstrument(instrument);
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
            this.railBoard.getController().unloadInstrument();
            unloaded = true;
        }
        catch (Exception e)
        {}
        
        return unloaded;
    }
}
