package code.gui.control;

import code.gui.VMainWindow;
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
    
    public VControlCenter(VMainWindow window)
    {
        this.playMode = new VPlayModePane();
        this.recRunMode = new VRecRunPane();
        this.learnMode = new VLearnPane();
        
        this.playMode.setWindow(window);
        
        addTab("Play mode", this.playMode);
        addTab("Rec/Run Secuence mode", this.recRunMode);
        addTab("Learning mode", this.learnMode);
        
        setBorder(BorderFactory.createTitledBorder("Control center"));
    }
}
