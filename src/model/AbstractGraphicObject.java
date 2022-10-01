package model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

 /**
 * Il model fornisce i metodi per accedere ai dati utili all'applicazione.
 *
 * "Subject" conosce i propri osservatori;
 * Fornisce un’interfaccia per registrare e cancellare le registrazioni degli oggetti Observer.
 *
 * Contiene gli oggetti grafici.
 */

public class AbstractGraphicObject implements GraphicObject, Cloneable {

    private List<GraphicObjectListener> listeners = new LinkedList<>();

    //attach(o:Observer)
    @Override
    public void addGraphicObjectListener(GraphicObjectListener l) {
        if(this.listeners.contains(l)) return;
        this.listeners.add(l);
    }//addGraphicObjectListener

    //detach(o:Observer)
    @Override
    public void removeGraphicObjectListener(GraphicObjectListener l) {
        this.listeners.remove(l);
    }//removeGraphicObjectListener

    //notify()
    protected void notifyListeners(GraphicEvent e) {
        for(GraphicObjectListener gol : listeners)
            gol.graphicChanged(e);
    }//notifyListeners

    @Override
    public Point2D getPosition() {
        return null;
    }//getPosition

    @Override
    public Dimension2D getDimension() {
        return null;
    }//getDimension

    @Override
    public String getLabel() {
        return null;
    }//getLabel

    @Override
    public GraphicObject clone() {
        try {
            AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
            go.listeners = new LinkedList<>();
            return go;
        }catch(CloneNotSupportedException e) {
            throw new Error(e);
        }
    }//clone()

}//AbstractGraphicObject