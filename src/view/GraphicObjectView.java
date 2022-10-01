package view;

import model.GraphicObject;

import java.awt.*;

 /**
 * La view visualizza i dati contenuti nel model e si occupa dell'interazione con utenti e agenti.
 */

public interface GraphicObjectView {

    void drawGraphicObject(GraphicObject go, Graphics2D g);

}//GraphicObjectView