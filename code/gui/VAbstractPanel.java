package code.gui;

import javax.swing.JPanel;

/**
 *
 * @author Jose Carlos
 */
public abstract class VAbstractPanel extends JPanel
{
    public VAbstractPanel()
    {
        initComponents();
    }

    protected abstract void createComponents();
    
    protected abstract void configureComponents();
    
    protected abstract void configureControllers();
    
    private void initComponents()
    {
        createComponents();
        configureComponents();
        configureControllers();
    }
}
