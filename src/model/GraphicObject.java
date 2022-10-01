package model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

 /**
 * Il model fornisce i metodi per accedere ai dati utili all'applicazione.
 */

public interface GraphicObject {

    void addGraphicObjectListener(GraphicObjectListener l);

    void removeGraphicObjectListener(GraphicObjectListener l);

    Point2D getPosition();

    Dimension2D getDimension();

    String getLabel();

}//GraphicObject
