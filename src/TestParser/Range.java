package TestParser;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Range{
    private double start, stop, step, next;

    public Range(Double start, Double stop, Double step) {
        if (step.equals((double) 0)) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.stop = stop;
        this.step = step;
        this.next = start;
    }

    public List<Double> toList() {
        List<Double> list = new ArrayList<>();
        for (double i=start; (step>0) ? i<stop : i>stop; i+=step) {
            list.add(i);
        }
        return list;
    }

    public boolean hasNext() {
        return (next < stop);
    }

    public double next() {
        if (next >= stop) {
            throw new NoSuchElementException();
        }
        double ret = next;
        next += step;
        return ret;
    }
}