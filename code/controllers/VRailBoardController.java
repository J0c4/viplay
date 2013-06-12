package code.controllers;

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
    
    public VRailBoardController(VRailBoard panel) 
    {
        this.railBoard = panel;
        this.railController = this.railBoard.getRail().getController();
        this.instrumentPanelController = this.railBoard.getInstrumentPanel().getController();
    }
    
    public void loadInstrument(VInstrument toLoad)
    {
        this.instrumentPanelController.loadInstrument(toLoad);
    }
    
    public void unloadInstrument()
    {
        this.instrumentPanelController.unloadInstrument();
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        VAnt toRun = this.instrumentPanelController.playInstrument(new VKey(e.getKeyCode(), String.valueOf(e.getKeyChar())));
        railController.runAnt(toRun);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
