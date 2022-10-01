package view;

import model.GraphicEvent;
import model.GraphicObject;
import model.GraphicObjectListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

 /**
 * La view visualizza i dati contenuti nel model e si occupa dell'interazione con utenti e agenti.
 *
 * "ConcreteObserver" memorizza un riferimento a un oggetto ConcreteSubject oppure ottiene dinamicamente il riferimento all’oggetto Subject da cui ha origine la notifica nel caso osservi più Subject.
 * Contiene informazioni che devono essere sincronizzate con lo/gli stato/i del/dei Subject.
 * Implementa l’interfaccia Observer per ricevere le notifiche del Subject.
 *
 */

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener {

    private List<GraphicObject> objects = new LinkedList<>();
    private Map<Class<? extends GraphicObject>, GraphicObjectView> viewMap = new HashMap<>();


    public GraphicObjectPanel(){
        setBackground(Color.WHITE);
    }//GraphicObjectPanel

    @Override
    public void graphicChanged(GraphicEvent e) {
        repaint();
        revalidate();
    }//graphicChanged

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(GraphicObject go : this.objects) {
            GraphicObjectView view = this.viewMap.get(go.getClass());
            view.drawGraphicObject(go, g2);
        }
    }//paintComponent

    public void add(GraphicObject go) {
        this.objects.add(go);
        go.addGraphicObjectListener(this);
        repaint();
    }//add

    public void remove(GraphicObject go) {
        if(this.objects.remove(go)) {
            repaint();
            go.removeGraphicObjectListener(this);
        }
    }//remove

    public void installView(Class<? extends GraphicObject> cl, GraphicObjectView view) {
        this.viewMap.put(cl, view);
    }//installView

}//GraphicObjectPanel
