package view;

import model.GraphicObject;
import model.LineObject;

import java.awt.*;
import java.awt.geom.Line2D;

 /**
 * La view visualizza i dati contenuti nel model e si occupa dell'interazione con utenti e agenti.
 */

public class LineObjectView implements GraphicObjectView {

    @Override
    public void drawGraphicObject(GraphicObject go, Graphics2D g) {
        LineObject l = (LineObject) go;
        g.draw(new Line2D.Double(l.getFrom(), l.getTo()));
    }//drawGraphicObject

}//LineObjectView
