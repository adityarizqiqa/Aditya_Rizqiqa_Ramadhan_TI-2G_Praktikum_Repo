package UTS;

public abstract class Destroyable {
    public abstract boolean destroyed();

    public void printDestroyableStatus() {
        if (this.destroyed()) {
            System.out.println("Objek telah dikalahkan.");
        } else {
            System.out.println("Objek masih bertahan.");
        }
    }
}