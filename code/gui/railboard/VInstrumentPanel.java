package code.gui.railboard;

import code.controllers.railboard.VInstrumentPanelController;
import code.gui.VAbstractPanel;
import javax.swing.BorderFactory;

/**
 *
 * @author Jose Carlos
 */
public class VInstrumentPanel extends VAbstractPanel
{
    private VInstrumentPanelController controller;
    
    public VInstrumentPanelController getController() 
    {
        return controller;
    }
    
    @Override
    protected void createComponents() 
    {
    }

    @Override
    protected void configureComponents() 
    {
        setVisible(false);
        setBorder(BorderFactory.createTitledBorder("Instrument"));
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VInstrumentPanelController(this);
    }
    
}
