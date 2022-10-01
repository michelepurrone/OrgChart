package view;

import model.GraphicObject;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

 /**
 * La view visualizza i dati contenuti nel model e si occupa dell'interazione con utenti e agenti.
 */

public class ShapeObjectView implements GraphicObjectView {

    @Override
    public void drawGraphicObject(GraphicObject go, Graphics2D g) {
        Point2D position = go.getPosition();
        Dimension2D dim = go.getDimension();
        double x = position.getX() - dim.getWidth() / 2.0;
        double y = position.getY() - dim.getHeight() / 2.0;
        g.draw(new Rectangle2D.Double(x, y, dim.getWidth(), dim.getHeight()));
        g.drawString(go.getLabel(),(float) x + 10 ,(float) y + 20);
    }//drawGraphicObject

}//ShapeObjectView
