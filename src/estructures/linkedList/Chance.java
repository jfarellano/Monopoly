package estructures.linkedList;

import estructures.Nodo;

public class Chance extends Nodo{
    

    public Chance(int numberOnTable) {
        super(numberOnTable);
        this.nombre = this.getClass().getSimpleName();
    }

}