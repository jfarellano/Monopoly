package estructures;

public class Lista {
    
    Nodo PTR;
    int tam;

    public Lista() {
        tam = 0;
    }

    public void add(Nodo n){
        if(PTR == null) {
            PTR = n;
            PTR.linkL = PTR;
            PTR.linkR = PTR;
        }else{
            Nodo aux = PTR.linkL;
            PTR.linkL = n;
            n.linkR = PTR;
            n.linkL = aux;
            aux.linkR = n;
        }
        tam++;
    }
    
    public void remove(Nodo n){
        Nodo end = PTR.linkL;
        Nodo p = PTR;
        boolean sw = true;
        if(tam == 1) {PTR = null; tam--;}
        while(p != PTR || sw){
            sw = false;
            if(p == n){
                p.linkL.linkR = p.linkR;
                p.linkR.linkL = p.linkL;
                tam--;
                return;
            }
            p = p.linkR;
        }
        System.out.println("Borrado inconcluseo, objeto no existene.");
    }
    
    public Nodo BuscarConId(int id){
        Nodo p = PTR;
        boolean sw = true;
        while(p != PTR || sw){
            sw = false;
            if(PTR.id == id){
                return PTR;
            }
            next();
        }
        return null;
    }
    
    public Nodo next(){
        PTR = PTR.linkR;
        return PTR;
    }
    
    public Nodo getCourrent(){
        return PTR;
    }
    
    public int length(){
        return tam;
    }

}