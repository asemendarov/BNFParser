package Tool;

public class TimerTest {
    private static long startTime;

    public static void start(){
        startTime = System.nanoTime();
    }

    public static long stop(){
        return System.nanoTime() - startTime;
    }
}
