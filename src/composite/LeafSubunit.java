package composite;

import visitor.Visitor;

 /**
 * "Leaf" rappresenta gli oggetti primitivi che non hanno figli.
 *
 * "ConcretePrototype" implementa l’operazione di clonazione.
 *
 * "ConcreteElement" implementa il metodo accept() che riceve un Visitor.
 *
 * "LeafSubunit" rappresenta le tipologie di sottounità organizzative (ad es. gruppi di lavoro).
 */

public class LeafSubunit extends AbstractCompositeUnit {

    private String type;
    private static LeafSubunit prototype;

    public LeafSubunit() {};

    public LeafSubunit(String type) {
        this.type = type;
    }//LeafSubunit

    public LeafSubunit(LeafSubunit ls) {
        this.type = ls.type;
    }//LeafSubunit

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
    protected LeafSubunit getPrototype() {
        if(prototype==null) prototype = new LeafSubunit();
        return prototype;
    }//getPrototype

    @Override
    public LeafSubunit clone() {
        LeafSubunit subunit = (LeafSubunit) super.clone();
        subunit.type = this.type;
        return subunit;
    }//clone

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLeafSubunit(this);
    }//accept

    @Override
    public String toString() {
        return "Sottounità: " + this.type;
    }//toString

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof LeafSubunit)) return false;
        if(o==this) return true;
        LeafSubunit ls = (LeafSubunit) o;
        return this.type.equals(ls.getType());
    }//equals

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }//hashCode

}//LeafSubunit
