package code.gui.builder;

import code.controllers.VInstrumentBuilderController;
import code.gui.VAbstractPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.plaf.BorderUIResource;

/**
 *
 * @author Jose Carlos
 */
public class VInstrumentBuilderPanel extends VAbstractPanel
{
    /** UI components */
    private VBuiltPanel builtPanel;
    private VBuilderControls buildControls;
    
    /** Controller */
    private VInstrumentBuilderController controller;

    public VInstrumentBuilderController getController() 
    {
        return this.controller;
    }

    public VBuiltPanel getInstrumentsTable() 
    {
        return this.builtPanel;
    }

    public VBuilderControls getBuilderControls() 
    {
        return this.buildControls;
    }
    
    @Override
    protected void createComponents() 
    {
        this.builtPanel = new VBuiltPanel();
        this.buildControls = new VBuilderControls();
    }

    @Override
    protected void configureComponents() 
    {
        setVisible(true);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(300, 0));
        setPreferredSize(new Dimension(300, 0));
        setBorder(BorderFactory.createCompoundBorder(
                        BorderUIResource.getRaisedBevelBorderUIResource(), 
                        BorderUIResource.getLoweredBevelBorderUIResource()));
        this.buildControls.setBorder(BorderFactory.createTitledBorder("Instrument builder"));
        add(this.builtPanel, BorderLayout.NORTH);
        add(this.buildControls, BorderLayout.SOUTH);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VInstrumentBuilderController(this);
        this.buildControls.getAddButton().addMouseListener(this.controller);
        this.buildControls.getResetButton().addMouseListener(this.controller);
        this.buildControls.getInstrumentOptions().addActionListener(this.controller);
        this.buildControls.getSoundOptions().addActionListener(this.controller);
        this.buildControls.getKeyOptions().addActionListener(this.controller);
        this.builtPanel.getSaveInstrument().addMouseListener(this.controller);
        this.builtPanel.getLoadInstrument().addMouseListener(this.controller);
    }
}
