package code.gui.control;

import code.gui.VAbstractPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Jose Carlos
 */
public class VPlayModePane extends VAbstractPanel
{
    /** UI components */
    private JButton start;
    private JButton stop;
    
    public JButton getStart() 
    {
        return start;
    }

    public JButton getStop() 
    {
        return stop;
    }

    @Override
    protected void createComponents() 
    {
        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
    }

    @Override
    protected void configureComponents() 
    {
        setBorder(BorderFactory.createTitledBorder("Main control"));
        this.stop.setEnabled(false);
        add(this.start);
        add(this.stop);
    }

    @Override
    protected void configureControllers() 
    {
    }
}
