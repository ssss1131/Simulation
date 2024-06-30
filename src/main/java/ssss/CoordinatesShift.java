package main.java.ssss;

public class CoordinatesShift {
    public int parallelShift;
    public int meridianShift;

    public CoordinatesShift(int parallelShift, int meridianShift) {
        this.parallelShift = parallelShift;
        this.meridianShift = meridianShift;
    }

    @Override
    public String toString() {
        return "CoordinatesShift{" +
                "parallelShift=" + parallelShift +
                ", meridianShift=" + meridianShift +
                "}\n";
    }
}
