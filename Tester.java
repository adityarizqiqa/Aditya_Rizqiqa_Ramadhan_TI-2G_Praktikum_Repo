package UTS;

public class Tester {
    public static void main(String[] args) {
        WalkingZombie wz = new WalkingZombie(100, 1); 
        JumpingZombie jz = new JumpingZombie(100, 2); 
        Barrier b = new Barrier(100); 
        Plant p = new Plant();

        System.out.println("--- Kondisi Awal ---");
        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
        System.out.println("---------------------------------");
        
        for (int i=0; i<4; i++){ 
            p.doDestroy(wz);
            p.doDestroy(jz);
            p.doDestroy(b);
        }
        
        System.out.println("\n--- Kondisi Setelah Serangan ---");
        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
        System.out.println("---------------------------------");

        for (int i=0; i < 50; i++){
            if (wz.getHealth() > 0) {
                 p.doDestroy(wz);
            }

            if (jz.getHealth() > 0) {
                 p.doDestroy(jz);
            }

            if (b.getStrength() > 0) {
                 p.doDestroy(b);
            }
        }
        
        System.out.println("\n--- Kondisi Akhir ---");
        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
    }
}