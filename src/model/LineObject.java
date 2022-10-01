package model;

import java.awt.geom.Point2D;

 /**
 * Il model fornisce i metodi per accedere ai dati utili all'applicazione.
 *
 * "ConcreteSubject" contiene lo stato a cui gli oggetti ConcreteObserver sono interessati.
 * Inoltra una notifica ai suoi Observer quando il proprio stato si modifica.
 *
 * Contiene gli oggetti grafici.
 */

public final class LineObject extends AbstractGraphicObject {

    private Point2D from;
    private Point2D to;

    public LineObject() {
        this.from = new Point2D.Double(0,0);
        this.to = new Point2D.Double(0,0);
    }//LineObject

    public LineObject(Point2D from, Point2D to) {
        this.from = new Point2D.Double(from.getX(), from.getY());
        this.to = new Point2D.Double(to.getX(), to.getY());
    }//LineObject

    public Point2D getFrom() {
        return new Point2D.Double(this.from.getX(), this.from.getY());
    }//getFrom

    public Point2D getTo() {
        return new Point2D.Double(this.to.getX(), this.to.getY());
    }//getTo

    @Override
    public LineObject clone() {
        LineObject cloned = (LineObject) super.clone();
        cloned.from = (Point2D) this.from.clone();
        cloned.to = (Point2D) this.to.clone();
        return cloned;
    }//clone

}//LineObject
