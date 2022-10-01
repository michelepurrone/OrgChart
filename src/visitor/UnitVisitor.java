package visitor;

import composite.*;

 /**
 * "ConcreteVisitor" implementa i metodi definiti da Visitor.
 * Ogni metodo implementa un frammento dell’algoritmo definito per la struttura.
 * "ConcreteVisitor" fornisce il contesto per l’algoritmo e memorizza il suo stato locale che accumula durante la visita della struttura.
 */

public class UnitVisitor implements Visitor {

    @Override
    public void visitLeafUnit(LeafUnit unit) {}//visitLeafUnit

    @Override
    public void visitLeafSubunit(LeafSubunit subunit) {}//visitLeafSubunit

    @Override
    public void visitLeafBody(LeafBody body) {}//visitLeafBody

}//UnitVisitor
