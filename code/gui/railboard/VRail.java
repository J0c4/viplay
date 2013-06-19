package code.gui.railboard;

import code.controllers.railboard.VRailController;
import code.gui.VAbstractPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 *
 * @author Jose Carlos
 */
public class VRail extends VAbstractPanel
{
    private VRailController controller;

    public VRailController getController()
    {
        return this.controller;
    }
    
    public void clear()
    {
        this.controller.apocalypse();
        validate();
    }
    
    @Override
    protected void createComponents() 
    {
    }

    @Override
    protected void configureComponents() 
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VRailController(this);
    }
}
