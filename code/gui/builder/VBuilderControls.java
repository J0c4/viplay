package code.gui.builder;

import code.controllers.VBuilderControlsController;
import code.gui.VAbstractPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Jose Carlos
 */
public class VBuilderControls extends VAbstractPanel
{
    private JLabel instrumentLabel;
    private JLabel soundLabel;
    private JLabel keyLabel;
    private JComboBox instrumentOptions;
    private JComboBox soundOptions;
    private JComboBox keyOptions;
    private JButton addButton;
    private JButton resetButton;
    
    private VBuilderControlsController controller;

    public VBuilderControlsController getController() 
    {
        return controller;
    }
    
    public JButton getAddButton() 
    {
        return addButton;
    }

    public JButton getResetButton() 
    {
        return resetButton;
    }

    public JComboBox getInstrumentOptions() 
    {
        return instrumentOptions;
    }

    public JComboBox getKeyOptions() 
    {
        return keyOptions;
    }

    public JComboBox getSoundOptions() 
    {
        return soundOptions;
    }
    
    @Override
    protected void createComponents() 
    {
        this.instrumentLabel = new JLabel("Instrument: ");
        this.soundLabel = new JLabel("Sound: ");
        this.keyLabel = new JLabel("Key: ");
        this.instrumentOptions = new JComboBox();
        this.soundOptions = new JComboBox();
        this.keyOptions = new JComboBox();
        this.addButton = new JButton("Add");
        this.resetButton = new JButton("Reset");
    }

    @Override
    protected void configureComponents() 
    {
        setLayout(new GridLayout(0, 2));
        this.resetButton.setEnabled(false);
        add(this.instrumentLabel);
        add(this.instrumentOptions);
        add(this.soundLabel);
        add(this.soundOptions);
        add(this.keyLabel);
        add(this.keyOptions);
        add(this.addButton);
        add(this.resetButton);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VBuilderControlsController(this);
    }
    
}
