package composite;

import visitor.Visitor;

 /**
 * "Leaf" rappresenta gli oggetti primitivi che non hanno figli.
 *
 * "ConcretePrototype" implementa l’operazione di clonazione.
 *
 * "ConcreteElement" implementa il metodo accept() che riceve un Visitor.
 *
 * "LeafBody" rappresenta le tipologie di organi di gestione.
 */

public class LeafBody extends AbstractCompositeUnit {

    private String type;
    private static LeafBody prototype;

    public LeafBody() {};

    public LeafBody(String type) {
        this.type = type;
    }//LeafBody

    public LeafBody(LeafBody lb) {
        this.type = lb.type;
    }//LeafBody

    //operation()
    @Override
    public String getType() {
        return this.type;
    }//getType

    //operation()
    @Override
    public void setType(String type) {
        this.type = type;
    }//setType

    @Override
    protected LeafBody getPrototype() {
        if(prototype==null) prototype = new LeafBody();
        return prototype;
    }//getPrototype

    @Override
    public LeafBody clone() {
        LeafBody body = (LeafBody) super.clone();
        body.type = this.type;
        return body;
    }//clone

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLeafBody(this);
    }//accept

    @Override
    public String toString() {
        return "Organo: " + this.type;
    }//toString

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof LeafBody)) return false;
        if(o==this) return true;
        LeafBody lb = (LeafBody) o;
        return this.type.equals(lb.getType());
    }//equals

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }//hashCode

}//LeafBody
