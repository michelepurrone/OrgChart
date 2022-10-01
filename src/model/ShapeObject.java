package model;

import composite.AbstractCompositeUnit;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

 /**
 * Il model fornisce i metodi per accedere ai dati utili all'applicazione.
 *
 * "ConcreteSubject" contiene lo stato a cui gli oggetti ConcreteObserver sono interessati.
 * Inoltra una notifica ai suoi Observer quando il proprio stato si modifica.
 *
 * Contiene gli oggetti grafici.
 */

public final class ShapeObject extends AbstractGraphicObject {

    private Point2D position;
    private Dimension2D dim;
    private String label = "";
    private AbstractCompositeUnit unit;

    public ShapeObject(Point2D pos, double w, double h) {
        if(w<=0 || h<=0)
            throw new IllegalArgumentException();
        dim = new Dimension();
        dim.setSize(w, h);
        try {
            position = new Point2D.Double(pos.getX(), pos.getY());
        }catch(NullPointerException exc){
            System.out.println("Null point!");
        }
    }//ShapeObject

    public void setLabel(){
        this.label = unit.getType();
        notifyListeners(new GraphicEvent(this));
    }//setLabel

    @Override
    public String getLabel(){
        return this.label;
    }//getLabel

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }//getPosition

    @Override
    public Dimension2D getDimension() {
        Dimension2D d = new Dimension();
        d.setSize(dim);
        return d;
    }//getDimension

    public void setUnit(AbstractCompositeUnit unit){
        this.unit=unit;
        notifyListeners(new GraphicEvent(this));
    }//setUnit

    public AbstractCompositeUnit getUnit(){
        return this.unit;
    }//getUnit

    @Override
    public ShapeObject clone() {
        ShapeObject cloned = (ShapeObject) super.clone();
        cloned.position = (Point2D) this.position.clone();
        cloned.dim = (Dimension2D) this.dim.clone();
        return cloned;
    }//clone

}//ShapeObject

