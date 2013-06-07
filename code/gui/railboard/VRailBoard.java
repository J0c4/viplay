package code.gui.railboard;

import code.controllers.VRailBoardController;
import code.gui.VAbstractPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 *
 * @author Jose Carlos
 */
public class VRailBoard extends VAbstractPanel
{
    /** UI Components */
    private VInstrumentPanel instrumentPanel;
    private VRail rail;
    
    /** Controller */
    private VRailBoardController controller;

    public VRailBoardController getController() 
    {
        return controller;
    }

    public VRail getRail() 
    {
        return rail;
    }

    public VInstrumentPanel getInstrumentPanel() 
    {
        return instrumentPanel;
    }
    
    @Override
    protected void createComponents()
    {
        this.rail = new VRail();
        this.instrumentPanel = new VInstrumentPanel();
    }
    
    @Override
    protected void configureComponents()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(this.rail, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 0;
        add(this.instrumentPanel, constraints);
    }
    
    @Override
    protected void configureControllers() 
    {
        this.controller = new VRailBoardController(this);
        this.instrumentPanel.addKeyListener(this.controller);
    }
}
