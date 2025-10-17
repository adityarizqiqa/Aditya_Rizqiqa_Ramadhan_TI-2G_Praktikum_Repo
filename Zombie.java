package UTS;

public abstract class Zombie extends Destroyable {
    protected int health;
    protected int level;
    
    public Zombie(int health, int level) {
        this.health = health;
        this.level = level;
    }
    
    @Override
    public abstract boolean destroyed();
    
    public abstract void heal(); 
    
    
    public String getZombieInfo() { /* ... */ return "Zombie Info..."; }
    protected boolean isKalah() { return this.health <= 0; }
    public int getHealth() { return this.health; }
}