package code.controllers;

import code.gui.control.VRecRunPane;
import code.model.VFileManager;
import code.model.datastructures.VQueue;
import code.model.recorder.VRecord;
import code.model.recorder.VSequence;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jose Carlos
 */
public class VRecRunController extends VControlCenterController
{
    private VRecRunPane pane;
    
    private JButton rec;
    private JButton play;
    private JButton stop;
    private JButton loadSecuence;
    private JLabel fileName;
    
    private VSequence sequence;
    private boolean isRecording;
    private boolean isReproducing;

    public VRecRunController(VRecRunPane pane) 
    {
        this.pane = pane;
        this.rec = pane.getRec();
        this.play = pane.getPlay();
        this.stop = pane.getStop();
        this.loadSecuence = pane.getLoadSecuence();
        this.fileName = pane.getFileName();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Component clicked = e.getComponent();
        if (clicked.isEnabled())
        {
            if (clicked.equals(this.rec))
            {
                if (loadInstrument())
                {
                   loadSequence();
                   this.isRecording = true;
                   this.rec.setEnabled(false);
                   this.loadSecuence.setEnabled(false);
                   this.stop.setEnabled(true);
                }
            }
            else if (clicked.equals(this.play))
            {
                if (loadInstrument())
                {
                    this.isReproducing = true;
                    this.stop.setEnabled(true);
                    this.play.setEnabled(false);
                    this.loadSecuence.setEnabled(false);
                    new Thread(sequence).start();
                }
            }
            else if (clicked.equals(this.stop))
            {
                if (unloadInstrument())
                {
                    this.stop.setEnabled(false);
                    this.loadSecuence.setEnabled(true);
                    if (this.isRecording)
                    {
                        this.isRecording = false;
                        this.rec.setEnabled(true);
                        VFileManager.instance.saveSecuence(this.sequence);
                    }
                    else if (this.isReproducing)
                    {
                        this.isReproducing = false;
                        this.play.setEnabled(true);
                    }
                }
            }
            else if (clicked.equals(this.loadSecuence))
            {
                this.sequence = VFileManager.instance.readSequence();
                this.builder.getController().loadInstrument(sequence.getInstrument());
                
                this.pane.remove(this.fileName);
                this.fileName = new JLabel(sequence.getName());
                this.pane.add(this.fileName);
                
                this.rec.setEnabled(false);
                this.play.setEnabled(true);
            }
        }
    }

    private void loadSequence() 
    {
        this.sequence = new VSequence(this.instrumentLoaded, System.currentTimeMillis());
        this.railBoard.getController().setInstrumentRecorder(this.sequence);
    }
}
