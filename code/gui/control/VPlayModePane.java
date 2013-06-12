package code.gui.control;

import code.controllers.VPlayModeController;
import code.gui.VAbstractPanel;
import code.gui.VMainWindow;
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
    
    /** Controller */
    private VPlayModeController controller;
    
    public JButton getStart() 
    {
        return start;
    }

    public JButton getStop() 
    {
        return stop;
    }
    
    public void setWindow(VMainWindow window)
    {
        this.controller.setWindow(window);
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
        this.stop.setEnabled(false);
        add(this.start);
        add(this.stop);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VPlayModeController(this);
        this.start.addMouseListener(this.controller);
        this.stop.addMouseListener(this.controller);
    }
}
