package code.gui.railboard;

import code.controllers.railboard.VRailController;
import code.gui.VAbstractPanel;
import code.gui.VMainWindow;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 *
 * @author Jose Carlos
 */
public class VRail extends VAbstractPanel
{
    private VRailController controller;
    private boolean showLine;

    public VRailController getController()
    {
        return this.controller;
    }
    
    public void clear()
    {
        this.controller.apocalypse();
        validate();
    }
    
    public void showLine(boolean show)
    {
        this.showLine = show;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (this.showLine)
        {
            Container mainPanel = VMainWindow.window.getContentPane();
            double lineYPosition = mainPanel.getSize().getHeight() * 0.2;
            double lineXEnd = mainPanel.getSize().getWidth();
            Graphics2D g2d = (Graphics2D) g;
            Line2D line = new Line2D.Double(0, lineYPosition, lineXEnd, lineYPosition);
            g2d.draw(line);
        }
    }
    
    @Override
    protected void createComponents() 
    {
    }

    @Override
    protected void configureComponents() 
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    @Override
    protected void configureControllers() 
    {
        this.controller = new VRailController(this);
    }
}
