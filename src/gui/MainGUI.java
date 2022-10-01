package gui;

import composite.AbstractCompositeUnit;
import composite.Employee;

import model.GraphicObject;
import model.LineObject;
import model.ShapeObject;
import view.GraphicObjectPanel;
import view.LineObjectView;
import view.ShapeObjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import static support.Util.exit;

 /**
 * La GUI riceve i comandi dell'utente e li attua modificando lo stato degli altri due componenti (model e view).
 * "MainGUI" mette insieme le altre GUI e si occupa di coordinarle.
 */

public class MainGUI extends JFrame {

    protected static GraphicObjectPanel window = new GraphicObjectPanel();

    private final JButton newUnitButton, addEmployeeButton, saveButton, infoButton, exitButton;
    private final CreateUnitGUI createUnitGUI = new CreateUnitGUI(window);
    private final AddEmployeeGUI addEmployeeGUI = new AddEmployeeGUI();

    protected static HashMap<AbstractCompositeUnit, Point> shapes = new HashMap<>();
    protected static ArrayList<Employee> employees = new ArrayList<>();
    protected static GraphicObject activePoint;
    protected static Point customPoint;
    protected static AbstractCompositeUnit root;

    private SaveManager sg = new SaveManager();

    public MainGUI() {
        window.setSize(400, 400);
        window.installView(ShapeObject.class, new ShapeObjectView());
        window.installView(LineObject.class, new LineObjectView());
        JFrame start = new JFrame();
        JToolBar toolBar = new JToolBar();

        newUnitButton = new JButton("Create new Unit");
        newUnitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Remember to click on the spot where you want to place the unit before entering the name", "Reminder", JOptionPane.INFORMATION_MESSAGE);
            createUnitGUI.setVisible(true);
        });
        toolBar.add(newUnitButton);

        addEmployeeButton = new JButton("Add new Employee");
        addEmployeeButton.addActionListener(e -> addEmployeeGUI.setVisible(true));
        toolBar.add(addEmployeeButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> sg.save());
        toolBar.add(saveButton);

        infoButton = new JButton("Info");
        infoButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Software Engineering Project", "Info", JOptionPane.INFORMATION_MESSAGE));
        toolBar.add(infoButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Upon exiting all unsaved data will be lost! Do you wish to continue?", "Warning", JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION) {System.out.println("Bye Bye"); System.exit(0); }
        });
        toolBar.add(exitButton);

        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.remove(activePoint);
                super.mouseClicked(e);
                customPoint = e.getPoint();
                activePoint = new ShapeObject(customPoint,10,10);
                window.add(activePoint);
            }
        });

        start.add(toolBar, BorderLayout.NORTH);
        start.add(new JScrollPane(window), BorderLayout.CENTER);
        start.setTitle("OrgChart");
        start.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        start.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){ if(exit()) System.out.println("Bye Bye"); System.exit(0); }
        });
        start.pack();
        start.setVisible(true);
        start.setSize(800, 800);
        start.setLocation(300,40);
    }//WindowGUI

}//MainGUI
