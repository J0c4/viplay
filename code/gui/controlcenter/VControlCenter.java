package code.gui.controlcenter;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

/**
 *
 * @author Jose Carlos
 */
public class VControlCenter extends JTabbedPane
{
    private VPlayModePane playMode;
    private VRecRunPane recRunMode;
    private VLearnPane learnMode;
    
    public VControlCenter()
    {
        this.playMode = new VPlayModePane();
        this.recRunMode = new VRecRunPane();
        this.learnMode = new VLearnPane();
        
        addTab("Play mode", this.playMode);
        addTab("Rec/Run Secuence mode", this.recRunMode);
        addTab("Learning mode", this.learnMode);
        
        setBorder(BorderFactory.createTitledBorder("Control center"));
    }
    
    public void setWindowReference()
    {
        this.playMode.setWindowReference();
        this.recRunMode.setWindowReference();        
    }
}
