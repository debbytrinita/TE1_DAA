import java.util.Random;

public class MyRandom {
    private Random random;

    public MyRandom() {
        random = new Random();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

}