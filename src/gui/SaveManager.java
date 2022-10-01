package gui;

import composite.AbstractCompositeUnit;
import composite.Employee;
import support.Util;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static gui.MainGUI.*;

 /**
 * "SaveManager" si occupa dell'operazione di salvataggio di un organigramma.
 */

public class SaveManager {

    private final JFileChooser jfc = new JFileChooser();
    private File file = null;

    public SaveManager() {}//SaveManager

    public void save() {
        try {
            if(file!=null) {
                int answer = JOptionPane.showConfirmDialog(null,"Do you want to overwrite " + file.getAbsolutePath() + "?", "Warning", JOptionPane.WARNING_MESSAGE);
                if(answer!=0) {
                    jfc.showSaveDialog(null);
                    file = jfc.getSelectedFile();
                }
                saveFile(file.getAbsolutePath());
                saveScreen(file.getAbsolutePath());
            }
            else {
                jfc.showSaveDialog(null);
                file = jfc.getSelectedFile();
                saveFile(file.getAbsolutePath());
                saveScreen(file.getAbsolutePath());
            }
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }//save

    private void saveFile(String absolutePath) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(absolutePath));
            for(Employee employee : employees) {
                pw.println("[" + "Dipendente: " + employee.getName() + ", " + employee.getRoles() + "]");
            }
            for(AbstractCompositeUnit unit : shapes.keySet()){
                pw.println("[" + "Entità: " + unit.getType() + ", ruoli disponibili: " + unit.getRoles() + "]");
            }
            JOptionPane.showMessageDialog(null, "File was saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
            pw.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }//saveFile

    private void saveScreen(String absolutePath) {
        try {
            String path = absolutePath.substring(0, absolutePath.indexOf(".")) + ".jpeg";
            ImageIO.write(Util.createImage(window), "jpeg", new File(path));
            System.out.println("Screenshot saved!");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }//saveScreen

}//SaveManager
