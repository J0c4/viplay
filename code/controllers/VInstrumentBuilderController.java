package code.controllers;

import code.gui.builder.VBuilderControls;
import code.gui.builder.VBuiltPanel;
import code.gui.builder.VInstrumentBuilderPanel;
import code.model.VFileManager;
import code.model.instruments.VIPlayable;
import code.model.instruments.VInstrument;
import code.model.instruments.VKey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Carlos
 */
public class VInstrumentBuilderController extends VAbstractController implements MouseListener, ActionListener
{
    private VInstrumentBuilderPanel builderPanel;
    
    /** Builder UI controls */
    private JComboBox instrumentOptions;
    private JComboBox soundOptions;
    private JComboBox keyOptions;
    private JButton addButton;
    private JButton resetButton;
    
    /** Built instrument table controls */
    private JButton saveButton;
    private JButton loadButton;
    
    /** Child components controllers */
    private VBuiltPanelController builtController;
    private VBuilderControlsController controlsController;
    
    public VInstrumentBuilderController(VInstrumentBuilderPanel builderPanel)
    {
        VBuilderControls controlsPanel = builderPanel.getBuilderControls();
        VBuiltPanel builtTable = builderPanel.getInstrumentsTable();
        this.builderPanel = builderPanel;
        this.saveButton = builtTable.getSaveInstrument();
        this.loadButton = builtTable.getLoadInstrument();
        this.instrumentOptions = controlsPanel.getInstrumentOptions();
        this.soundOptions = controlsPanel.getSoundOptions();
        this.keyOptions = controlsPanel.getKeyOptions();
        this.addButton = controlsPanel.getAddButton();
        this.resetButton = controlsPanel.getResetButton();
        
        this.builtController = builtTable.getController();
        this.controlsController = controlsPanel.getController();
    }
    
    public VInstrument getInstrument()
    {
        return this.controlsController.getInstrumentBuilt();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object actionObject = e.getSource();
        if (actionObject instanceof JComboBox)
        {
            JComboBox selected = (JComboBox)actionObject;
            if (selected.equals(this.instrumentOptions))
            {
                this.controlsController.loadInstrumentSelected();
            }
            else if (selected.equals(this.soundOptions))
            {
                VIPlayable sound = (VIPlayable)selected.getSelectedItem();
                this.controlsController.setElement(sound);
            }
            else if (selected.equals(this.keyOptions))
            {
                VKey key = (VKey)selected.getSelectedItem();
                this.controlsController.setKey(key);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        JButton clicked = (JButton)e.getComponent();
        if (clicked.equals(this.addButton))
        {
            List<Object> rowColumns = this.controlsController.addCurrentElement();
            this.builtController.addRow(rowColumns);
            if (!rowColumns.isEmpty())
            {
                this.keyOptions.removeItem(rowColumns.get(1));
            }
            
        }
        else if (clicked.equals(this.resetButton))
        {
            this.builtController.resetTable();
            this.controlsController.resetControls();
        }
        else if (clicked.equals(this.saveButton))
        {
            VInstrument toSave = this.controlsController.getInstrumentBuilt();
            VFileManager.instance.saveInstrument(toSave);
        }
        else if (clicked.equals(this.loadButton))
        {
            VInstrument read = VFileManager.instance.readInstrument();
            if (read != null)
            {
                this.builtController.loadTable(read);
                this.controlsController.loadInstrument(read);
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
}
