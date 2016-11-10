package estructures;

public class Nodo {
    
    protected int id;
    protected Nodo linkR, linkL;
    protected String nombre;
    
    public Nodo(int id) {
        this.id = id;
    }

    public Nodo() {
    }
    
    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}