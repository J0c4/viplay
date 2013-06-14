package code.controllers;

import code.gui.VMainWindow;
import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.railboard.VRailBoard;
import code.model.instruments.VInstrument;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Carlos
 */
public abstract class VControlCenterController implements MouseListener
{
    protected VMainWindow mainWindow;
    protected VRailBoard railBoard;
    protected VInstrumentBuilderPanel builder;
    
    public void setWindowReference(VMainWindow window)
    {
        this.mainWindow = window;
        this.railBoard = window.getRailBoard();
        this.builder = window.getBuilder();
    }

    protected boolean loadInstrument() 
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

    protected boolean unloadInstrument() 
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
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
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
