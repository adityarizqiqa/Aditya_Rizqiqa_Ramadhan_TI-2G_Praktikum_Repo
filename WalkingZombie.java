package UTS;

public class WalkingZombie extends Zombie {

    public WalkingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        double percentage = 0.0;
        switch (this.level) {
            case 1: percentage = 0.20; break;
            case 2: percentage = 0.30; break;
            case 3: percentage = 0.40; break;
        }
        this.health += (int) (this.health * percentage);
    }

    @Override
    public boolean destroyed() {
        this.health -= (int) (this.health * 0.20); 
        return isKalah();
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Data=\n" +
               "Health= " + this.health + "\n" +
               "Level= " + this.level + "\n";
    }
}
