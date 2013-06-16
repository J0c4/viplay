package code.controllers;

import code.gui.VMainWindow;
import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.control.VControlCenter;
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
    protected VControlCenter controlCenter;
    
    protected VInstrument instrumentLoaded;
    
    public void setWindowReference()
    {
        this.mainWindow = VMainWindow.window;
        this.railBoard = VMainWindow.window.getRailBoard();
        this.builder = VMainWindow.window.getBuilder();
        this.controlCenter = VMainWindow.window.getControl();
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

    protected boolean loadInstrument() 
    {
        VInstrumentBuilderController builderController = this.builder.getController();
        this.instrumentLoaded = builderController.getInstrument();
        boolean isValidInstrument = this.instrumentLoaded.isBuilt();
        if (isValidInstrument)
        {
            this.railBoard.getController().loadInstrument(this.instrumentLoaded);
            this.railBoard.showInstrumentPanel(true);
            this.builder.setVisible(false);
            enableTabs(false);
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
            this.railBoard.showInstrumentPanel(false);
            this.builder.setVisible(true);
            unloaded = true;
            enableTabs(true);
        }
        catch (Exception e)
        {}
        
        return unloaded;
    }
    
    protected void enableTabs(boolean enabled)
    {
        int currentTab = this.controlCenter.getSelectedIndex();
        for (int index = 0; index < this.controlCenter.getTabCount(); index++)
        {
            if (index != currentTab)
            {
                this.controlCenter.setEnabledAt(index, enabled);
            }
        }
    }
}
