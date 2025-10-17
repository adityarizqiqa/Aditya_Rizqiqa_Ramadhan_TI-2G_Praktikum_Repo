package UTS;

public class JumpingZombie extends Zombie {

    public JumpingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        double percentage = 0.0;
        switch (this.level) {
            case 1: percentage = 0.30; break;
            case 2: percentage = 0.40; break;
            case 3: percentage = 0.50; break;
        }
        this.health += (int) (this.health * percentage);
    }

    @Override
    public boolean destroyed() {
        this.health -= (int) (this.health * 0.10); 
        return isKalah();
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Data=\n" +
               "Health= " + this.health + "\n" +
               "Level= " + this.level + "\n";
    }
}
