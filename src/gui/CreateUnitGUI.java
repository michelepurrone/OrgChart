package gui;

import composite.*;
import model.LineObject;
import model.ShapeObject;
import view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static support.Util.*;

 /**
 * La GUI riceve i comandi dell'utente e li attua modificando lo stato degli altri due componenti (model e view).
 * "CreateUnitGUI" si occupa di definire la sequenza di operazioni da effettuare per inserire una nuova unità.
 */


public class CreateUnitGUI extends JFrame {

    private ShapeObject go;
    private LineObject lo = new LineObject();
    private final GraphicObjectPanel window;


    private final ButtonGroup bg = new ButtonGroup();
    private final JRadioButton unitRB, subunitRB, bodyRB;
    private final JTextField type, role, parent;
    private final JButton setTypeButton, addRoleButton, setParentButton, okButton;


    private AbstractCompositeUnit newUnit;


    public CreateUnitGUI(GraphicObjectPanel gpanel) {
        //CREAZIONE E CONFIGURAZIONE FINESTRA
        this.window = gpanel;
        setTitle("Create new Unit");
        setSize(400,200);
        setLocation(1000,40);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){if(exit()) CreateUnitGUI.this.setVisible(false);
            }
        });

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        //CATEGORIA UNITÀ
        setGBConstraints(c, 0,0,1,1);
        pane.add(new JLabel("Category: ", JLabel.CENTER), c);
        unitRB = new JRadioButton("Unit");
        bg.add(unitRB);
        setGBConstraints(c, 1,0,1,1);
        pane.add(unitRB, c);
        subunitRB = new JRadioButton("Subunit");
        bg.add(subunitRB);
        setGBConstraints(c, 2,0,1,1);
        pane.add(subunitRB, c);
        bodyRB = new JRadioButton("Body");
        bg.add(bodyRB);
        setGBConstraints(c, 3,0,1,1);
        pane.add(bodyRB, c);

        //TIPOLOGIA UNITÀ
        setGBConstraints(c, 0,1,1,1);
        pane.add(new JLabel("Type: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,1,2,1);
        pane.add(type = new JTextField("", 12), c);
        setGBConstraints(c, 3,1,1,1);
        pane.add(setTypeButton = new JButton("Set Type"), c);

        //RUOLI DISPONIBILI
        setGBConstraints(c, 0,2,1,1);
        pane.add(new JLabel("Role: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,2,2,1);
        pane.add(role = new JTextField("", 12), c);
        setGBConstraints(c, 3,2,1,1);
        pane.add(addRoleButton = new JButton("Add Role"), c);

        //UNITÀ GENITORE
        setGBConstraints(c, 0,3,1,1);
        pane.add(new JLabel("Parent: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,3,2,1);
        pane.add(parent = new JTextField("", 12), c);
        setGBConstraints(c, 3,3,1,1);
        pane.add(setParentButton = new JButton("Set Parent"), c);

        //CONFERMA
        setGBConstraints(c, 0,4,4,1);
        pane.add(okButton = new JButton("OK"), c);

        this.add(pane);


        //ACTION LISTENER
        unitRB.addActionListener(e -> {newUnit = new LeafUnit(); type.setEnabled(true); setTypeButton.setEnabled(true);});
        subunitRB.addActionListener(e -> {newUnit = new LeafSubunit(); type.setEnabled(true); setTypeButton.setEnabled(true);});
        bodyRB.addActionListener(e -> {newUnit = new LeafBody(); type.setEnabled(true); setTypeButton.setEnabled(true);});
        setTypeButton.addActionListener(e -> setType());
        addRoleButton.addActionListener(e -> addRole());
        setParentButton.addActionListener(e -> setParent());
        okButton.addActionListener(e -> ok());


        //INIZIALIZZAZIONE
        type.setEnabled(false);
        setTypeButton.setEnabled(false);
        role.setEnabled(false);
        addRoleButton.setEnabled(false);
        parent.setEnabled(false);
        setParentButton.setEnabled(false);
        okButton.setEnabled(false);
    }//NewUnitGUI

    private void setType() {
        unitRB.setEnabled(false);
        subunitRB.setEnabled(false);
        bodyRB.setEnabled(false);
        String newType = type.getText();
        if(!newType.equals("")) {
            Point point = createPoint();
            go = new ShapeObject(point,70,30);
            go.setUnit(newUnit);
            newUnit.setType(newType);
            go.setLabel();
            MainGUI.shapes.put(newUnit, point);
            if(MainGUI.shapes.size()>1) {
                parent.setEnabled(true);
                setParentButton.setEnabled(true);
            }
            else {
                MainGUI.root = newUnit;
            }

            unitRB.setEnabled(false);
            subunitRB.setEnabled(false);
            bodyRB.setEnabled(false);
            type.setText("");
            type.setEnabled(false);
            setTypeButton.setEnabled(false);
            role.setEnabled(true);
            addRoleButton.setEnabled(true);
        }
        else
            JOptionPane.showMessageDialog(null, "Please enter a type", "Warning", JOptionPane.ERROR_MESSAGE);
    }//setType

    private void addRole() {
        String newRole = role.getText();
        if(!newRole.equals("")) {
            newUnit.addRole(newRole);
            role.setText("");
            System.out.println(newUnit.getRoles());
            if(MainGUI.shapes.size()>1) {
                okButton.setEnabled(false);
                if(!setParentButton.isEnabled()) okButton.setEnabled(true);
            }
            else {
                okButton.setEnabled(true);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Please enter a role", "Warning", JOptionPane.ERROR_MESSAGE);
    }//addRole

    private void setParent() {
        ComponentUnit unitParent = findUnit(MainGUI.root, this.parent.getText());
        if(unitParent==null) {
            JOptionPane.showMessageDialog(null, "Parent does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            unitParent.addChild(newUnit);
            Point parP = MainGUI.shapes.get(unitParent);
            Point sonP = MainGUI.shapes.get(newUnit);
            Point parPLine = new Point((int) parP.getX(), (int) (parP.getY()+15));
            Point sonPLine = new Point((int) sonP.getX(), (int) (sonP.getY()-15));
            lo = new LineObject(parPLine, sonPLine);

            parent.setText("");
            parent.setEnabled(false);
            setParentButton.setEnabled(false);
            okButton.setEnabled(true);
        }
    }//setParent

    private void ok() {
        //Si aggiunge l'entità (rettangolo) e l'eventuale relazione gerarchica (linea); si toglie il punto attivo
        window.add(go);
        window.add(lo);
        window.remove(MainGUI.activePoint);

        System.out.println(newUnit);

        //RESET
        bg.clearSelection();
        unitRB.setEnabled(true);
        subunitRB.setEnabled(true);
        bodyRB.setEnabled(true);
        type.setText("");
        type.setEnabled(false);
        setTypeButton.setEnabled(false);
        role.setText("");
        role.setEnabled(false);
        addRoleButton.setEnabled(false);
        parent.setText("");
        parent.setEnabled(false);
        setParentButton.setEnabled(false);
        okButton.setEnabled(false);
        CreateUnitGUI.this.setVisible(false);
    }//ok

    private Point createPoint() {
        if(MainGUI.shapes.size()==0) {
            Point p = new Point(250, 40);
            return p;
        }
        Point point = MainGUI.customPoint;
        if(point==null)
            JOptionPane.showMessageDialog(null, "Remember to click on the spot where you want to place the unit before entering the name", "Reminder", JOptionPane.ERROR_MESSAGE);
        MainGUI.customPoint = null;
        return point;
    }//createPoint

}//CreateUnitGUI

