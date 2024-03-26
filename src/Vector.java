import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector {
    // Содержит три компоненты: i, j, k
    private final List<Double> coordinates;

    public Vector(List<Double> coordinates) throws Exception {
        if (coordinates.size() != 3) throw new Exception("Необходим трехмерный вектор");
        this.coordinates = coordinates;
    }

    public static Vector getVectorByPoints(Point pointA, Point pointB) throws Exception {
        // Получаем вектор через две точки, путем вычетания соответствующих координат конечной и начальной точек
        // https://ru.onlinemschool.com/math/library/vector/p_to_vector/
        var coordinates = new ArrayList<Double>();
        coordinates.add(pointB.getX() - pointA.getX());
        coordinates.add(pointB.getY() - pointA.getY());
        coordinates.add(pointB.getZ() - pointA.getZ());
        return new Vector(coordinates);
    }

    public static Vector getVectorProduct(Vector vectorA, Vector vectorB) throws Exception {
        // Векторное произведение двух векторов, результатом будет третий вектор
        // https://ru.onlinemschool.com/math/library/vector/multiply1/

        // Вычисляем результирующий вектор по формуле вычисления векторного произведения векторов, приведенной выше
        var Ax = vectorA.getX();
        var Ay = vectorA.getY();
        var Az = vectorA.getZ();
        var Bx = vectorB.getX();
        var By = vectorB.getY();
        var Bz = vectorB.getZ();

        var coordinatesC = Stream.of(
                Ay * Bz - Az * By,
                Az * Bx - Ax * Bz,
                Ax * By - Ay * Bx
        ).toList();
        return new Vector(coordinatesC);
    }

    public double getVectorLength() {
        // Получение длинны вектора
        // https://ru.onlinemschool.com/math/library/vector/length/
        return sqrt(
                pow(getX(), 2)
                        + pow(getY(), 2)
                        + pow(getZ(), 2)
        );
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public double getX() {
        return coordinates.get(0);
    }

    public double getY() {
        return coordinates.get(1);
    }

    public double getZ() {
        return coordinates.get(2);
    }
}
