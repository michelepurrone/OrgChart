package composite;

 /**
 * "Composite" definisce il comportamento dei componenti che hanno figli.
 * Memorizza i componenti figli e implementa i relativi metodi introdotti dall’interfaccia Component.
 *
 * "Prototype" specifica l’interfaccia che consente la clonazione.
 */

import java.util.*;

public abstract class AbstractCompositeUnit implements ComponentUnit, Cloneable {

    private List<ComponentUnit> children = new ArrayList<>();
    private ComponentUnit parent;
    private Set<String> roles = new HashSet<>();

    //add(g:Component)
    @Override
    public void addChild(ComponentUnit child){
        children.add(child);
        child.setParent(this);
    }//addChild

    //remove(g:Component)
    @Override
    public void removeChild(ComponentUnit child){
        children.remove(child);
        child.setParent(null);
    }//removeChild

    //getChild(i:int):Component
    @Override
    public ComponentUnit getChild(int i) {
        return children.get(i);
    }//getChild

    public List<ComponentUnit> getChildren() {
        return this.children;
    }//getChildren

    //setParent(g:Component)
    @Override
    public void setParent(ComponentUnit parent) {
        this.parent = parent;
    }//setParent

    //getParent():Component
    @Override
    public ComponentUnit getParent() {
        return this.parent;
    }//getParent

    public void addRole(String role){
        this.roles.add(role);
    }//addRole

    public Set<String> getRoles(){
        return this.roles;
    }//getRoles

    public void removeRole(String role) { this.roles.remove(role); }//removeRole

    protected abstract AbstractCompositeUnit getPrototype();//getPrototype

    @Override
    public AbstractCompositeUnit clone() {
        try { return (AbstractCompositeUnit) super.clone(); }
        catch(CloneNotSupportedException e) {
            throw new Error(e);
        }
    }//clone

    @Override
    public Iterator<ComponentUnit> iterator() {
        return new InnerIterator();
    }//iterator

    private class InnerIterator implements Iterator<ComponentUnit> {

        Iterator<ComponentUnit> it = children.iterator();
        private AbstractCompositeUnit last = null;

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }//hasNext

        @Override
        public ComponentUnit next() {
            last = (AbstractCompositeUnit) it.next();
            return last;
        }//next

        @Override
        public void remove() {
            if(last == null)
                throw new NoSuchElementException();
            it.remove();
            last.setParent(null);
            last = null;
        }//remove

    }//InnerIterator

}//AbstractCompositeUnit
