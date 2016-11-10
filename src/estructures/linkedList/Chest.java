package estructures.linkedList;

import estructures.Nodo;

public class Chest extends Nodo{
    public Chest(int numberOnTable) {
        super(numberOnTable);
        this.nombre = this.getClass().getSimpleName();
    }
    
    

}