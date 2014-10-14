
package CodeDay;

public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World GW = new World();
        Thread T1 = new Thread(GW);
        T1.start();
    }
}