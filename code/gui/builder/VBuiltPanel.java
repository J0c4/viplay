package code.gui.builder;

import code.controllers.builder.VBuiltPanelController;
import code.gui.VAbstractPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Carlos
 */
public class VBuiltPanel extends VAbstractPanel
{
    private JScrollPane tablePane;
    private JTable instrumentsTable;
    private JButton saveInstrument;
    private JButton loadInstrument;
    
    private VBuiltPanelController controller;

    public VBuiltPanelController getController() 
    {
        return this.controller;
    }

    public JButton getSaveInstrument() 
    {
        return this.saveInstrument;
    }

    public JButton getLoadInstrument() 
    {
        return loadInstrument;
    }

    public JTable getInstrumentsTable() 
    {
        return this.instrumentsTable;
    }
    
    @Override
    protected void createComponents() 
    {
        String[] columnHeaders = {"Element", "Key"};
        String[][] data = {};
        
        this.instrumentsTable = new JTable(new DefaultTableModel(data, columnHeaders));
        this.tablePane = new JScrollPane(this.instrumentsTable);
        this.saveInstrument = new JButton("Save Instrument");
        this.loadInstrument = new JButton("Load instrument");
    }

    @Override
    protected void configureComponents() 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.tablePane);
        add(this.saveInstrument);
        add(this.loadInstrument);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VBuiltPanelController(this);
    }
    
}
