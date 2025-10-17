package UTS;

public class Barrier extends Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }
    
    @Override
    public boolean destroyed() {
        this.strength -= 9;
        if (this.strength < 0) {
            this.strength = 0;
        }
        return isKalah(); 
    }
    public int getStrength(){
        return this.strength;
    }

    private boolean isKalah() { return this.strength <= 0; }
    public String getBarrierInfo() { return "Barrier Info..."; }
}
