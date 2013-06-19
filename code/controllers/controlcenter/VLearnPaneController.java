package code.controllers.controlcenter;

import code.gui.controlcenter.VLearnPane;
import code.gui.controlcenter.VRecRunPane;
import code.model.VFileManager;
import code.model.recorder.VSequence;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jose Carlos
 */
public class VLearnPaneController extends VControlCenterController
{
    private VLearnPane pane;
    
    private JButton play;
    private JButton stop;
    private JButton loadSecuenceButton;
    private JLabel fileName;
    
    private VSequence sequence;
    private boolean isReproducing;

    public VLearnPaneController(VLearnPane pane) 
    {
        this.pane = pane;
        this.play = pane.getPlay();
        this.stop = pane.getStop();
        this.loadSecuenceButton = pane.getLoadSecuence();
        this.fileName = pane.getFileName();
    }

    public boolean isSecuenceSet()
    {
        return this.sequence != null;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        if (clicked.isEnabled())
        {
            if (clicked.equals(this.play))
            {
                if (loadInstrument())
                {
                    setRunningState(true);
                    this.sequence.setIsLearningMode(true);
                    this.railBoard.getController().setIsLearningMode(true);
                    this.sequence.reproduce();
                    this.isReproducing = true;
                }
            }
            else if (clicked.equals(this.stop))
            {
                if (unloadInstrument())
                {
                    setRunningState(false);
                    if (this.isReproducing)
                    {
                        this.sequence.stop();
                        this.isReproducing = false;
                    }
                    this.railBoard.clear();
                }
            }
            else if (clicked.equals(this.loadSecuenceButton))
            {
                this.sequence = VFileManager.instance.readSequence();
                loadSequence();
            }
        }
    }
    
    private void loadSequence()
    {
        if (isSecuenceSet())
        {
            this.builder.getController().loadInstrument(sequence.getInstrument());
            this.play.setEnabled(true);
        }
        else
        {
            this.play.setEnabled(false);
        }
        updateFileName();
    }
    
    private void setRunningState(boolean running)
    {
        this.stop.setEnabled(running);
        this.play.setEnabled(!running);
        this.loadSecuenceButton.setEnabled(!running);
    }
    
    private void updateFileName()
    {
        this.pane.remove(this.fileName);
        this.fileName = isSecuenceSet() ? new JLabel(sequence.getName()) :
                                          new JLabel("No sequence loaded");
        this.pane.add(this.fileName);
    }
}
