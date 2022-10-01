package composite;

import visitor.Visitor;

 /**
 * "Leaf" rappresenta gli oggetti primitivi che non hanno figli.
 *
 * "ConcretePrototype" implementa l’operazione di clonazione.
 *
 * "ConcreteElement" implementa il metodo accept() che riceve un Visitor.
 *
 * "LeafUnit" rappresenta le tipologie di unità (ad es. dipartimenti).
 */

public class LeafUnit extends AbstractCompositeUnit {

    private String type;
    private static LeafUnit prototype;

    public LeafUnit() {};

    public LeafUnit(String type) {
        this.type = type;
    }//LeafUnit

    public LeafUnit(LeafUnit lu) {
        this.type = lu.type;
    }//LeafUnit

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
    protected LeafUnit getPrototype() {
        if(prototype==null) prototype = new LeafUnit();
        return prototype;
    }//getPrototype

    @Override
    public LeafUnit clone() {
        LeafUnit unit = (LeafUnit) super.clone();
        unit.type = this.type;
        return unit;
    }//clone

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLeafUnit(this);
    }//accept

    @Override
    public String toString() {
        return "Unità: " + this.type;
    }//toString

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof LeafUnit)) return false;
        if(o==this) return true;
        LeafUnit lu = (LeafUnit) o;
        return this.type.equals(lu.getType());
    }//equals

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }//hashCode

}//LeafUnit
