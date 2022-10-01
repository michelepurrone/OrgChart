package support;

import composite.AbstractCompositeUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

 /**
 * "Util" raccoglie metodi di utilità.
 */

public final class Util {

    private Util() {};

    public static AbstractCompositeUnit findUnit(AbstractCompositeUnit root, String type) {
        AbstractCompositeUnit tmp;
        if(root.getType().equals(type)) return root;
        for(int i=0; i<root.getChildren().size(); i++) {
            tmp = findUnit((AbstractCompositeUnit) root.getChild(i), type);
            if(tmp!=null)
                return tmp;
        }
        return null;
    }//findUnit

    public static void setGBConstraints(GridBagConstraints c, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
    }//setGBConstraints

    public static BufferedImage createImage(JComponent component) {
        Dimension d = component.getSize();
        if(d.width==0 || d.height==0) {
            d = component.getPreferredSize();
            component.setSize(d);
        }
        Rectangle region = new Rectangle(0, 0, d.width, d.height);
        return Util.createImage(component, region);
    }//createImage

    public static BufferedImage createImage(JComponent component, Rectangle region) {
        if(!component.isDisplayable()) {
            Dimension d = component.getSize();
            if(d.width==0 || d.height==0) {
                d = component.getPreferredSize();
                component.setSize(d);
            }
            layoutComponent(component);
        }
        BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        if(!component.isOpaque()) {
            g2d.setColor(component.getBackground());
            g2d.fillRect(region.x, region.y, region.width, region.height);
        }
        g2d.translate(-region.x, -region.y);
        component.print(g2d);
        g2d.dispose();
        return image;
    }//createImage

    static void layoutComponent(Component component) {
        synchronized(component.getTreeLock()) {
            component.doLayout();
            if(component instanceof Container) {
                for(Component child : ((Container)component).getComponents()) {
                    layoutComponent(child);
                }
            }
        }
    }//layoutComponent

    public static boolean exit() {
        int option = JOptionPane.showConfirmDialog(null, "Upon exiting all unsaved data will be lost! Do you wish to continue?", "Warning", JOptionPane.YES_NO_OPTION);
        return option==JOptionPane.YES_OPTION;
    }//exit

}//Util
