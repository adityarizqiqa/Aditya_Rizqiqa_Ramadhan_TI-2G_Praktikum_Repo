package UTS;

public class Plant {
    public void doDestroy(Destroyable d) {
        
        boolean isKalah = d.destroyed();

        if (d instanceof Zombie) {
            Zombie z = (Zombie) d;
        } else if (d instanceof Barrier) {
            Barrier b = (Barrier) d;
    
        }
    }
}
