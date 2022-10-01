package gui;

import composite.AbstractCompositeUnit;
import composite.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static support.Util.*;

 /**
 * La GUI riceve i comandi dell'utente e li attua modificando lo stato degli altri due componenti (model e view).
 * "AddEmployeeGUI" si occupa di definire la sequenza di operazioni da effettuare per inserire un nuovo dipendente.
 */

public class AddEmployeeGUI extends JFrame {

    private final JTextField name, role, unit;
    private final JButton addRoleButton, setNameButton, okButton;


    Employee newEmployee;

    public AddEmployeeGUI() {

        //CREAZIONE E CONFIGURAZIONE FINESTRA
        setTitle("Add new Employee");
        setSize(300,200);
        setLocation(1000,250);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){if(exit()) AddEmployeeGUI.this.setVisible(false);}
        });
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        //NOME CANDIDATO
        setGBConstraints(c, 0,0,1,1);
        pane.add(new JLabel("Name: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,0,2,1);
        pane.add(name = new JTextField("",8), c);
        setGBConstraints(c, 3,0,1,1);
        pane.add(setNameButton = new JButton("Set Name"), c);

        //UNITÀ CANDIDATA
        setGBConstraints(c, 0,1,1,1);
        pane.add(new JLabel("Unit: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,1,3,1);
        pane.add(unit = new JTextField("",15), c);

        //RUOLO DA RICOPRIRE
        setGBConstraints(c, 0,2,1,1);
        pane.add(new JLabel("Role: ", JLabel.CENTER), c);
        setGBConstraints(c, 1,2,2,1);
        pane.add(role = new JTextField("", 12), c);
        setGBConstraints(c, 3,2,1,1);
        pane.add(addRoleButton = new JButton("Add Role"), c);

        //CONFERMA
        setGBConstraints(c, 0,3,4,1);
        pane.add(okButton = new JButton("OK"), c);

        this.add(pane);


        //ACTION LISTENER
        setNameButton.addActionListener(e -> setName());
        addRoleButton.addActionListener(e -> addRole());
        okButton.addActionListener(e -> ok());

        //INIZIALIZZAZIONE
        unit.setEnabled(false);
        role.setEnabled(false);
        addRoleButton.setEnabled(false);
        okButton.setEnabled(false);
    }//NewEmployeeGUI

    private void setName() {
        String newName = name.getText();
        if(!newName.equals("")) {
            newEmployee = new Employee(newName);
            name.setText("");
            name.setEnabled(false);
            setNameButton.setEnabled(false);
            unit.setEnabled(true);
            role.setEnabled(true);
            addRoleButton.setEnabled(true);
        }
        else {
            JOptionPane.showMessageDialog(null,"Please enter a name", "Reminder", JOptionPane.ERROR_MESSAGE);
        }
    }//setName

    private void addRole() {
        String unitType = unit.getText();
        String employeeRole = role.getText();
        if(unitType.equals(""))
            JOptionPane.showMessageDialog(null,"Please enter a unit", "Warning", JOptionPane.ERROR_MESSAGE);
        if(employeeRole.equals(""))
            JOptionPane.showMessageDialog(null,"Please enter a role", "Warning", JOptionPane.ERROR_MESSAGE);
        if(MainGUI.root==null) {
            JOptionPane.showMessageDialog(null, "Unit does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        AbstractCompositeUnit employeeUnit = findUnit(MainGUI.root, unitType);
        if(employeeUnit==null)
            JOptionPane.showMessageDialog(null, "Unit does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            newEmployee.addRole(employeeUnit, employeeRole);
            role.setText("");
            unit.setText("");
            okButton.setEnabled(true);
        }
    }//addRole

    private void ok() {
        MainGUI.employees.add(newEmployee);
        System.out.println(newEmployee);

        //RESET
        name.setText("");
        name.setEnabled(true);
        setNameButton.setEnabled(true);
        unit.setText("");
        unit.setEnabled(false);
        role.setText("");
        role.setEnabled(false);
        addRoleButton.setEnabled(false);
        okButton.setEnabled(false);
        AddEmployeeGUI.this.setVisible(false);
    }//ok

}//AddEmployeeGUI

