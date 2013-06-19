package code.controllers.railboard;

import code.controllers.VAbstractController;
import code.gui.railboard.VAnt;
import code.gui.railboard.VRail;
import code.model.VIRunnableEndListener;
import code.model.datastructures.VQueue;
import java.awt.BorderLayout;

/**
 *
 * @author Jose Carlos
 */
public class VRailController extends VAbstractController implements VIRunnableEndListener
{
    private VRail rail;
    private VQueue<VAnt> antsRunning;

    public VRailController(VRail rail) 
    {
        this.rail = rail;
        this.antsRunning = new VQueue<VAnt>();
    }
    
    public void apocalypse()
    {
        for (VAnt ant : antsRunning)
        {
            ant.die();
        }
    }
    
    public void runAnt(VAnt ant)
    {
        if (ant != null)
        {
            this.rail.add(ant, BorderLayout.SOUTH);
            this.rail.validate();
            this.antsRunning.add(ant);
            ant.addRunnableEndListener(this);
            ant.go();
        }
    }

    @Override
    public void finishRunnable() 
    {
        VAnt antEnded = this.antsRunning.remove();
        this.rail.remove(antEnded);
    }
}
