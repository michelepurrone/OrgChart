package model;

 /**
 * Il model fornisce i metodi per accedere ai dati utili all'applicazione.
 *
 * "Observer" fornisce un’interfaccia di notifica per gli oggetti a cui devono essere notificati i cambiamenti nel Subject.
 *
 * "GraphicObjectListener" può osservare più GraphicObject.
 */

public interface GraphicObjectListener {

    //Update
    void graphicChanged(GraphicEvent e);

}//GraphicObjectListener
