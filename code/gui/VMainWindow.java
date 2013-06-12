package code.gui;

import code.gui.builder.VInstrumentBuilderPanel;
import code.gui.control.VControlCenter;
import code.gui.railboard.VRailBoard;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Jose Carlos
 */
public class VMainWindow extends JFrame
{
    public static VMainWindow window;
    private JMenuBar menuBar;
    private VRailBoard railBoard;
    private VInstrumentBuilderPanel builder;
    private VControlCenter controlCenter;
    
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(VMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) 
        {
            Logger.getLogger(VMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) 
        {
            Logger.getLogger(VMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (UnsupportedLookAndFeelException ex) 
        {
            Logger.getLogger(VMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                VMainWindow.window = new VMainWindow();
                VMainWindow.window.setVisible(true);
            }
        });
    }
    
    private VMainWindow()
    {
        initComponents();
    }

    public VInstrumentBuilderPanel getBuilder() 
    {
        return builder;
    }

    public VControlCenter getControl() 
    {
        return controlCenter;
    }

    public JMenuBar getTopMenuBar() 
    {
        return menuBar;
    }

    public VRailBoard getRailBoard() 
    {
        return railBoard;
    }

    private void initComponents() 
    {
        loadComponents();
        allocateFrame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void loadComponents() 
    {
        setLayout(new BorderLayout());
        // Create UI components
        this.menuBar = new JMenuBar();
        this.railBoard = new VRailBoard();
        this.builder = new VInstrumentBuilderPanel();
        this.controlCenter = new VControlCenter(this);
        
        // Create Menu
        JMenu fileMenu = new JMenu("File");
        JMenu configMenu = new JMenu("Configuration");
        fileMenu.add(new JMenuItem("Save secuence"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));
        configMenu.add(new JMenuItem("Configuration"));
        this.menuBar.add(fileMenu);
        this.menuBar.add(configMenu);
        
        // Add components to current frame
        add(this.menuBar, BorderLayout.NORTH);
        add(this.railBoard, BorderLayout.CENTER);
        add(this.builder, BorderLayout.EAST);
        add(this.controlCenter, BorderLayout.SOUTH);
    }

    private void allocateFrame() 
    {
        Dimension windowScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (windowScreen.getWidth() * 0.8);
        int height = (int) (windowScreen.getHeight() * 0.8);
        int posX = (int) (windowScreen.getWidth() * 0.1);
        int posY = (int) (windowScreen.getHeight() * 0.05);
        
        setPreferredSize(new Dimension(width, height));
        setLocation(new Point(posX, posY));
    }
}
