package synchronize;

/**
 * javac **.java
 * javap -c **
 */
public class SynchronizeTest {
    public static void main(String[] args) {
        synchronized (SynchronizeTest.class) {
            System.out.println("synchronized");
        }
    }
}
