package composite;

import java.util.HashMap;

 /**
 * "Employee" definisce il dipendente.
 */

public class Employee {

    private String name;
    private HashMap<AbstractCompositeUnit, String> roles;

    public Employee(String name){
        this.name = name;
        this.roles = new HashMap<>();
    }//Employee

    public String getName() {
        return name;
    }//getName

    public void setName(String name) {
        this.name = name;
    }//setName

    public HashMap<AbstractCompositeUnit, String> getRoles() {
        return new HashMap<>(this.roles);
    }//getRoles

    public void addRole(AbstractCompositeUnit unit, String role) {
        if(!unit.getRoles().contains(role)) {
            throw new RuntimeException("The role is not suitable for the selected unit! Please try again.");
        }
        this.roles.put(unit, role);
        unit.removeRole(role);
    }//addRole

    @Override
    public String toString() {
        return this.name + "" + this.roles;
    }//toString

}//Employee
