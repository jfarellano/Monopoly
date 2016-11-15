package estructures.linkedList;

import estructures.Nodo;

public class Tax extends Nodo{
    
    public int tax;

    public Tax(int numberOnTable, int tax) {
        super(numberOnTable);
        this.tax = tax;
        this.nombre = this.getClass().getSimpleName();
    }
    
    //GETTERS SETTERS

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }
    
}