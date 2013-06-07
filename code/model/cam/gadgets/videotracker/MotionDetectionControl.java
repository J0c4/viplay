package code.model.cam.gadgets.videotracker;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.Control;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MotionDetectionControl implements Control, ActionListener, ChangeListener  
{

    Component component;
    JButton button;
    JSlider threshold;
    VMotionDetectionEffect motion;


    public MotionDetectionControl(VMotionDetectionEffect motion) 
    {
        this.motion = motion;
    }

    @Override
    public Component getControlComponent() 
    {
        if (component == null) 
        {
            button = new JButton("Debug");
            button.addActionListener(this);

            button.setToolTipText("Click to turn debugging mode on/off");

            threshold = new JSlider(JSlider.HORIZONTAL,
                                    0,
                                    motion.THRESHOLD_MAX,
                                    motion.THRESHOLD_INIT);

            threshold.setMajorTickSpacing(motion.THRESHOLD_INC);
            threshold.setPaintLabels(true);
            threshold.addChangeListener(this);

            Panel componentPanel = new Panel();
            componentPanel.setLayout(new BorderLayout());
            componentPanel.add("East", button);
            componentPanel.add("West", threshold);
            componentPanel.invalidate();
            component = componentPanel;
        }
        
        return component;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object o = e.getSource();

        if (o == button) 
        {
            if (motion.debug == false)
                motion.debug = true;
            else 
                motion.debug = false;
        }

    }
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        Object o = e.getSource();
        if (o == threshold) 
        {
            motion.blob_threshold = threshold.getValue()*1000;
        }
    }
}
