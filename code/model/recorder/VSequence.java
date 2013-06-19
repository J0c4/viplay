package code.model.recorder;

import code.controllers.railboard.VRailBoardController;
import code.gui.VMainWindow;
import code.model.datastructures.VQueue;
import code.model.instruments.VInstrument;
import code.model.instruments.VKey;
import java.io.Serializable;

/**
 *
 * @author Jose Carlos
 */
public class VSequence implements Serializable, Runnable
{
    private VInstrument instrument;
    private VQueue<VRecord> records;
    private Thread runner;
    private String name;
    
    private long lastPlayedTime;
    private long newPlayedTime;
    private boolean isLearningMode;
    
    public VSequence(VInstrument instrument, long startTime)
    {
        this.instrument = instrument;
        this.records = new VQueue<VRecord>();
        this.lastPlayedTime = startTime;
    }

    public VInstrument getInstrument() 
    {
        return instrument;
    }

    public VQueue<VRecord> getRecords() 
    {
        return records;
    }

    public Thread getRunner() 
    {
        return runner;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setIsLearningMode(boolean isLearningMode) 
    {
        this.isLearningMode = isLearningMode;
    }
    
    public void record(VKey key)
    {
        this.newPlayedTime = System.currentTimeMillis();
        long startTime = this.newPlayedTime - this.lastPlayedTime;
        this.lastPlayedTime = this.newPlayedTime;
        VRecord newRecord = new VRecord(key, startTime);
        this.records.add(newRecord);
    }
    
    public void reproduce()
    {
        this.runner = new Thread(this);
        this.runner.start();
    }
    
    public void stop()
    {
        if (this.runner != null && this.runner.isAlive())
        {
            this.runner.suspend();
        }
    }

    @Override
    public void run() 
    {
        VRailBoardController player = VMainWindow.window.getRailBoard().getController();
        VQueue<VRecord> recovery = new VQueue<VRecord>();
        recovery.addAll(this.records);
        while (!recovery.isEmpty())
        {
            VRecord record = recovery.remove();
            try
            {
                Thread.sleep(record.getStartTime());
            }
            catch(Exception e){}
            player.playElement(record.getKey(), !this.isLearningMode, true);
        }
    }
}
