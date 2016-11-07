package estructures;

public class Nodo {
    
    protected int id;
    protected Nodo linkR, linkL;

    public Nodo(int id) {
        this.id = id;
    }

    public Nodo() {
    }
    
    public int getId(){
        return id;
    }

}