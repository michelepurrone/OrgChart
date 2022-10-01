package composite;

import visitor.Visitor;

 /**
 * "Component" dichiara l’interfaccia per gli oggetti componibili.
 * Può essere una classe astratta che fornisce un’implementazione di default per i metodi di gestione dei componenti figli.
 * Introduce un metodo per accedere al componente genitore.
 *
 * "Element" definisce il metodo accept() che riceve un Visitor.
 */

public interface ComponentUnit extends Iterable<ComponentUnit> {

    //operation()
    String getType();

    //operation(s:String)
    void setType(String type);

    //add(g:Component)
    void addChild(ComponentUnit child);

    //remove(g:Component)
    void removeChild(ComponentUnit child);

    //getChild(i:int):Component
    ComponentUnit getChild(int i);

    //setParent(g:Component)
    void setParent(ComponentUnit parent);

    //getParent():Component
    ComponentUnit getParent();

    //accept(v:Visitor)
    void accept(Visitor visitor);

}//ComponentUnit
