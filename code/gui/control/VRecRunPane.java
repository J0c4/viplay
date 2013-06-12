package code.gui.control;

import code.controllers.VRecRunController;
import code.gui.VAbstractPanel;
import code.gui.VMainWindow;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/**
 *
 * @author Jose Carlos
 */
public class VRecRunPane extends VAbstractPanel
{
    /** UI components */
    private JButton rec;
    private JButton play;
    private JButton stop;
    private JSeparator separator;
    private JButton loadSecuence;
    private JLabel fileLabel;
    private JLabel fileName;
    
    /** Controller */
    private VRecRunController controller;

    public JLabel getFileName() 
    {
        return fileName;
    }

    public JButton getLoadSecuence() 
    {
        return loadSecuence;
    }

    public JButton getPlay() 
    {
        return play;
    }

    public JButton getRec() 
    {
        return rec;
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
        this.rec = new JButton("Rec");
        this.play = new JButton("Play");
        this.stop = new JButton("Stop");
        this.separator = new JSeparator(JSeparator.VERTICAL);
        this.loadSecuence = new JButton("Load Secuence");
        this.fileLabel =  new JLabel("Secuence: ");
        this.fileName = new JLabel("No secuence loaded");
    }

    @Override
    protected void configureComponents() 
    {
        this.play.setEnabled(false);
        this.stop.setEnabled(false);
        
        add(this.rec);
        add(this.play);
        add(this.stop);
        add(this.separator);
        add(this.loadSecuence);
        add(this.fileLabel);
        add(this.fileName);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VRecRunController(this);
        this.rec.addMouseListener(this.controller);
        this.play.addMouseListener(this.controller);
        this.stop.addMouseListener(this.controller);
        this.loadSecuence.addMouseListener(this.controller);
    }
    
}
