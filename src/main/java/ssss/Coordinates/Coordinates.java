package main.java.ssss.Coordinates;

import java.util.Objects;

public class Coordinates {
    public int parrallel;
    public int meridian;

    public Coordinates(int meridian, int parrallel) {
        this.meridian = meridian;
        this.parrallel = parrallel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return parrallel == that.parrallel && meridian == that.meridian;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parrallel, meridian);
    }
}
