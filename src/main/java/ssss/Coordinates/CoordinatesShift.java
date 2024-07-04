package main.java.ssss.Coordinates;

public class CoordinatesShift {
    private final int parallelShift;
    private final int meridianShift;

    public CoordinatesShift(int parallelShift, int meridianShift) {
        this.parallelShift = parallelShift;
        this.meridianShift = meridianShift;
    }

    public int getParallelShift() {
        return parallelShift;
    }

    public int getMeridianShift() {
        return meridianShift;
    }
}
