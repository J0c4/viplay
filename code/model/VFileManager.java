package code.model;

import code.gui.VMainWindow;
import code.model.instruments.VInstrument;
import code.model.recorder.VSequence;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Carlos
 */
public class VFileManager 
{
    public final static VFileManager instance = new VFileManager();
    
    private VFileManager()
    {}
    
    public void saveInstrument(VInstrument toSave) 
    {
        if (toSave.isBuilt())
        {
            File target;
            JFileChooser fileChosser = new JFileChooser();
            int option = fileChosser.showSaveDialog(VMainWindow.window);
            switch (option)
            {
                case JFileChooser.APPROVE_OPTION:
                    try
                    {
                        target = fileChosser.getSelectedFile();
                        FileOutputStream outputStream = new FileOutputStream(target);
                        ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
                        outputObject.writeObject(toSave);
                        
                        outputObject.close();
                        outputStream.close();
                    }
                    catch (FileNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                    }
                break;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(VMainWindow.window, "The instrument is not properly built");
        }
    }
    
    public VInstrument readInstrument()
    {
        VInstrument read = null;
        JFileChooser fileChosser = new JFileChooser();
        int option = fileChosser.showOpenDialog(VMainWindow.window);
        switch (option)
        {
            case JFileChooser.APPROVE_OPTION:
                try
                {
                    File target = fileChosser.getSelectedFile();
                    FileInputStream inputStream = new FileInputStream(target);
                    ObjectInputStream inputObject = new ObjectInputStream(inputStream);
                    read = (VInstrument) inputObject.readObject();
                    inputObject.close();
                    inputStream.close();
                }
                catch (ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (FileNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                }
            break;
        }
        return read;
    }
    
    public void saveSecuence(VSequence toSave)
    {
        File target;
        JFileChooser fileChosser = new JFileChooser();
        int option = fileChosser.showSaveDialog(VMainWindow.window);
        switch (option)
        {
            case JFileChooser.APPROVE_OPTION:
                try
                {
                    target = fileChosser.getSelectedFile();
                    FileOutputStream outputStream = new FileOutputStream(target);
                    ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
                    toSave.setName(target.getName());
                    outputObject.writeObject(toSave);

                    outputObject.close();
                    outputStream.close();
                }
                catch (FileNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                }
            break;
        }
    }
    
    public VSequence readSequence()
    {
        VSequence read = null;
        JFileChooser fileChosser = new JFileChooser();
        int option = fileChosser.showOpenDialog(VMainWindow.window);
        switch (option)
        {
            case JFileChooser.APPROVE_OPTION:
                try
                {
                    File target = fileChosser.getSelectedFile();
                    FileInputStream inputStream = new FileInputStream(target);
                    ObjectInputStream inputObject = new ObjectInputStream(inputStream);
                    read = (VSequence) inputObject.readObject();
                    inputObject.close();
                    inputStream.close();
                }
                catch (ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (FileNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                }
            break;
        }
        return read;
    }
}
