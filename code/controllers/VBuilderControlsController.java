package code.controllers;

import code.gui.builder.VBuilderControls;
import code.model.instruments.VIPlayable;
import code.model.instruments.VInstrument;
import code.model.instruments.VInstrumentBuilder;
import code.model.instruments.VKey;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Jose Carlos
 */
public class VBuilderControlsController extends VAbstractController
{
    private VBuilderControls controls;
    private JComboBox instrumentOptions;
    private JComboBox soundOptions;
    private JComboBox keyOptions;
    private JButton addButton;
    private JButton resetButton;
    
    private VInstrumentBuilder builder;
    private boolean isInstrumentOnBuild;

    public VBuilderControlsController(VBuilderControls controls) 
    {
        this.controls = controls;
        this.instrumentOptions = this.controls.getInstrumentOptions();
        this.soundOptions = this.controls.getSoundOptions();
        this.keyOptions = this.controls.getKeyOptions();
        this.addButton = this.controls.getAddButton();
        this.resetButton = this.controls.getResetButton();
        this.isInstrumentOnBuild = false;
        // load Combo box options
        loadOptions(this.instrumentOptions, loadInstruments());
        loadInstrumentSelected();
        // set default values
        setDefaultValues();
    }
    
    public VInstrument getInstrumentBuilt()
    {
        return this.builder.getBuild();
    }
    
    public void setElement(VIPlayable sound)
    {
        this.builder.setElement(sound);
    }
    
    public void setKey(VKey key)
    {
        this.builder.setKey(key);
    }
    
    public final void loadInstrumentSelected()
    {
        String instrumentType = (String)this.instrumentOptions.getSelectedItem();
        if (instrumentType != null)
        {
            this.builder = new VInstrumentBuilder(instrumentType);
            loadBuilder();
        }
    }
    
    public void loadInstrument(VInstrument toLoad)
    {
        this.builder = new VInstrumentBuilder(toLoad);
        this.isInstrumentOnBuild = true;
        loadBuilder();
        setOnBuildState();
        setDefaultValues();
    }
    
    public List<Object> addCurrentElement()
    {
        List<Object> rowColumns = new ArrayList<Object>();
        boolean isAdded = this.builder.addPlayableElement();
        if (isAdded)
        {
            rowColumns.add(this.builder.getElement());
            rowColumns.add(this.builder.getKey());
            if (!this.isInstrumentOnBuild)
            {
                this.isInstrumentOnBuild = true;
                setOnBuildState();
            }
        }
        return rowColumns;
    }
    
    public void resetControls()
    {
        this.builder.reset();
        this.isInstrumentOnBuild = false;
        setOnBuildState();
        setDefaultValues();
        loadOptions(this.keyOptions, loadKeys());
    }
    
    private void loadBuilder()
    {
        VInstrument loaded = this.builder.getBuild();
        List<Object> keysUsed = new ArrayList<Object>(loaded.getElements().keySet());
        loadOptions(this.soundOptions, loaded.getElementOptions());
        loadOptions(this.keyOptions, loadKeys(), keysUsed);
    }

    private void loadOptions(JComboBox toLoad, Object[] data) 
    {
        loadOptions(toLoad, data, new ArrayList<Object>());
    }
    
    private void loadOptions(JComboBox toLoad, Object[] data, List<Object> ignored)
    {
        DefaultComboBoxModel model = (DefaultComboBoxModel)toLoad.getModel();
        if (model.getSize() > 0)
        {
            model.removeAllElements();
        }
        for (Object element : data)
        {
            if (!ignored.contains(element))
            {
                model.addElement(element);
            }
        }
        if (data.length > 0)
        {
            toLoad.setSelectedIndex(0);
        }
    }

    private void setDefaultValues() 
    {
        VIPlayable defaultElement = (VIPlayable)this.soundOptions.getSelectedItem();
        VKey defaultKey = (VKey)this.keyOptions.getSelectedItem();
        this.builder.setElement(defaultElement);
        this.builder.setKey(defaultKey);
    }

    private void setOnBuildState() 
    {
        this.instrumentOptions.setEnabled(!this.isInstrumentOnBuild);
        this.resetButton.setEnabled(this.isInstrumentOnBuild);
    }
    
    private String[] loadInstruments() 
    {
        return new String[]{ VInstrumentBuilder.DRUM_KIT_INSTRUMENT };
    }

    private VKey[] loadKeys() 
    {
        return new VKey[]{ new VKey(KeyEvent.VK_Q, "Q"),
                           new VKey(KeyEvent.VK_W, "W"),
                           new VKey(KeyEvent.VK_E, "E"),
                           new VKey(KeyEvent.VK_R, "R"),
                           new VKey(KeyEvent.VK_T, "T"),
                           new VKey(KeyEvent.VK_Y, "Y"),
                           new VKey(KeyEvent.VK_U, "U"),
                           new VKey(KeyEvent.VK_I, "I"),
                           new VKey(KeyEvent.VK_O, "O"),
                           new VKey(KeyEvent.VK_P, "P"),
                           new VKey(KeyEvent.VK_A, "A"),
                           new VKey(KeyEvent.VK_S, "S"),
                           new VKey(KeyEvent.VK_D, "D"),
                           new VKey(KeyEvent.VK_F, "F"),
                           new VKey(KeyEvent.VK_G, "G"),
                           new VKey(KeyEvent.VK_H, "H"),
                           new VKey(KeyEvent.VK_J, "J"),
                           new VKey(KeyEvent.VK_K, "K"),
                           new VKey(KeyEvent.VK_L, "L"),
                           new VKey(KeyEvent.VK_Z, "Z"),
                           new VKey(KeyEvent.VK_X, "X"),
                           new VKey(KeyEvent.VK_C, "C"),
                           new VKey(KeyEvent.VK_V, "V"),
                           new VKey(KeyEvent.VK_B, "B"),
                           new VKey(KeyEvent.VK_N, "N"),
                           new VKey(KeyEvent.VK_M, "M")
                         };
    }
}
