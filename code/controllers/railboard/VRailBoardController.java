package code.controllers.railboard;

import code.controllers.VAbstractController;
import code.gui.railboard.VAnt;
import code.gui.railboard.VRailBoard;
import code.model.instruments.VInstrument;
import code.model.instruments.VKey;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jose Carlos
 */
public class VRailBoardController extends VAbstractController implements KeyListener
{
    private VRailBoard railBoard;
    
    private VRailController railController;
    private VInstrumentPanelController instrumentPanelController;
    
    private boolean isLearningMode;
    
    public VRailBoardController(VRailBoard panel) 
    {
        this.railBoard = panel;
        this.railController = this.railBoard.getRail().getController();
        this.instrumentPanelController = this.railBoard.getInstrumentPanel().getController();
    }

    public void setIsLearningMode(boolean isLearningMode) 
    {
        this.isLearningMode = isLearningMode;
    }
    
    public void loadInstrument(VInstrument toLoad)
    {
        this.instrumentPanelController.loadInstrument(toLoad);
    }
    
    public void unloadInstrument()
    {
        this.instrumentPanelController.unloadInstrument();
    }
    
    public void playElement(VKey key)
    {
        playElement(key, true, true);
    }
    
    public void playElement(VKey key, boolean runSound, boolean runAnt)
    {
        VAnt toRun;
        if (runSound)
        {
            toRun = this.instrumentPanelController.playInstrument(key);
        }
        else
        {
            toRun = this.instrumentPanelController.createAnt(key);
        }
        if (runAnt)
        {
            railController.runAnt(toRun);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        VKey key = new VKey(e.getKeyCode(), String.valueOf(e.getKeyChar()));
        playElement(key, true, !this.isLearningMode);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
