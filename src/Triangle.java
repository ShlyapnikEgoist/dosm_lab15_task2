import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Triangle {
    private final List<Point> points;


    public Triangle(List<Point> points) throws Exception {
        if (points.size() != 3) throw new Exception("Должно быть 3 точки");
        // Проверка на случай совпадения точек:
        // Создаем множество, добавляем в него точки и проверяем на количество элементов множества,
        // если точки будут совпадать, то множество будет содержать только один ключ
        var setPoints = new HashSet<>(points);
        if (setPoints.size() != 3) throw new Exception("Не должно быть совпадающих точек");

        this.points = points;
    }

    public double getPerimeter() throws Exception {
        // Получить периметр треугольника

        // Строим три вектора, вычисляем их длинны и складываем
        var vectorA = Vector.getVectorByPoints(getPointA(), getPointB());
        var vectorB = Vector.getVectorByPoints(getPointA(), getPointC());
        var vectorC = Vector.getVectorByPoints(getPointB(), getPointC());
        return vectorA.getVectorLength() + vectorB.getVectorLength() + vectorC.getVectorLength();
    }

    public double getArea() throws Exception {
        // Получить площадь треугольника
        // https://ru.onlinemschool.com/math/assistance/vector/triangle_area/

        // Строим два вектора, берущих начало из одной точки
        var vectorA = Vector.getVectorByPoints(getPointA(), getPointB());
        var vectorB = Vector.getVectorByPoints(getPointA(), getPointC());
        // Получаем третий вектор
        var vectorC = Vector.getVectorProduct(vectorA, vectorB);
        // Вычисляем площадь
        return vectorC.getVectorLength() * 1 / 2;
    }

    public TriangleAngleType getTriangleAngleType() throws Exception {
        // Строим три вектора, вычисляем их длинны
        var vectorA = Vector.getVectorByPoints(getPointA(), getPointB());
        var vectorB = Vector.getVectorByPoints(getPointA(), getPointC());
        var vectorC = Vector.getVectorByPoints(getPointB(), getPointC());
        // Сортируем от меньшего к большему
        var vectorLengths = Stream.of(
                vectorA.getVectorLength(),
                vectorB.getVectorLength(),
                vectorC.getVectorLength()
        ).sorted().toList();
        var sideA = vectorLengths.get(0);
        var sideB = vectorLengths.get(1);
        var sideC = vectorLengths.get(2);

        var hypotenuseSquare = pow(sideC, 2);
        // Сумма квадратов катетов треугольника
        var legsSquares = pow(sideA, 2) + pow(sideB, 2);
        if (hypotenuseSquare == legsSquares) {
            return TriangleAngleType.RIGHT;
        } else if (hypotenuseSquare > legsSquares) {
            return TriangleAngleType.OBTUSE;

        } else {
            // } else if (hypotenuseSquare < legsSquares) {
            // Случай когда hypotenuseSquare < legsSquares
            return TriangleAngleType.ACUTE;
        }
    }

    public TriangleSideType getTriangleSideType() throws Exception {
        // Строим три вектора, вычисляем их длинны
        var vectorA = Vector.getVectorByPoints(getPointA(), getPointB());
        var vectorB = Vector.getVectorByPoints(getPointA(), getPointC());
        var vectorC = Vector.getVectorByPoints(getPointB(), getPointC());

        var sideA = vectorA.getVectorLength();
        var sideB = vectorB.getVectorLength();
        var sideC = vectorC.getVectorLength();


        if (Objects.equals(sideA, sideB) && Objects.equals(sideA, sideC)) {
            // Если все стороны равны
            return TriangleSideType.EQUILATERAL;
        } else if (Objects.equals(sideA, sideB) || Objects.equals(sideA, sideC) || Objects.equals(sideB, sideC)) {
            // Если две стороны равны
            return TriangleSideType.ISOSCELES;
        } else {
            // Остается только произвольный
            return TriangleSideType.ARBITRARY;
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getPointA() {
        return getPoints().get(0);
    }

    public Point getPointB() {
        return getPoints().get(1);
    }

    public Point getPointC() {
        return getPoints().get(2);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "points=" + points +
                '}';
    }
}
