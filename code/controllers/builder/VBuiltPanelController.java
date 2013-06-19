package code.controllers.builder;

import code.gui.builder.VBuiltPanel;
import code.model.instruments.VIPlayable;
import code.model.instruments.VInstrument;
import code.model.instruments.VKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Carlos
 */
public class VBuiltPanelController
{
    private VBuiltPanel panel;
    private JTable instrumentsTable;
    private DefaultTableModel model;

    public VBuiltPanelController(VBuiltPanel panel) 
    {
        this.panel = panel;
        this.instrumentsTable = panel.getInstrumentsTable();
        this.model = (DefaultTableModel)this.instrumentsTable.getModel();
    }

    public void addRow(List<Object> rowColumns) 
    {
        if (!rowColumns.isEmpty())
        {
            this.model.addRow(rowColumns.toArray());
        }
    }

    public void resetTable() 
    {
        int rows = this.model.getRowCount(); 
        for(int i = rows - 1; i >= 0; i--)
        {
            model.removeRow(i); 
        }
    }

    public void loadTable(VInstrument read) 
    {
        resetTable();
        Set<Map.Entry<VKey, VIPlayable>> elements = read.getElements().entrySet();
        for (Map.Entry<VKey, VIPlayable> entry : elements)
        {
            List<Object> row = new ArrayList<Object>();
            row.add(entry.getValue());
            row.add(entry.getKey());
            addRow(row);
        }
    }
}
