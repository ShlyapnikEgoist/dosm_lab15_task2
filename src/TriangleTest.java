import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class TriangleTest {
    // Произвольный тупоугольный треугольник
    // http://www.pm298.ru/reshenie/wr0300.php
    private final Triangle obtuseArbitraryTriangle = new Triangle(
            Stream.of(
                    new Point(2, 3, 0),
                    new Point(6, 7, 0),
                    new Point(-7, 2, 0)
            ).toList()
    );
    // Произвольный прямоугольный треугольник
    private final Triangle rightArbitraryTriangle = new Triangle(
            Stream.of(
                    new Point(0, 0, 0),
                    new Point(3, 0, 0),
                    new Point(0, 4, 0)
            ).toList()
    );
    // Равносторонний остроугольный треугольник
    private final Triangle acuteEquilateralTriangle = new Triangle(
            Stream.of(
                    new Point(1, 0, 0),
                    new Point(0, 1, 0),
                    new Point(0, 0, 1)
            ).toList()
    );
    // Равнобедренный тупоугольный треугольник
    private final Triangle obtuseIsoscelesTriangle = new Triangle(
            Stream.of(
                    new Point(1, 2, 3),
                    new Point(1, 2, 4),
                    new Point(1, 3, 4)
            ).toList()
    );

    public TriangleTest() throws Exception {
        // Конструктор для ловли возможных исключений (для подавления ошибок)
    }

    @Test
    void toStringTest() throws Exception {
        var pointA = new Point(1, 2, 3);
        var pointB = new Point(1, 2, 4);
        var pointC = new Point(1, 3, 4);
        var triangle = new Triangle(Stream.of(pointA, pointB, pointC).toList());
        Assertions.assertEquals(
                "Triangle{points=[Point{x=1.0, y=2.0, z=3.0}, Point{x=1.0, y=2.0, z=4.0}, Point{x=1.0, y=3.0, z=4.0}]}",
                triangle.toString()
        );
    }

    @Test
    public void createTriangle() throws Exception {
        var pointA = new Point(1, 2, 3);
        var pointB = new Point(1, 2, 4);
        var pointC = new Point(1, 3, 4);
        var triangle = new Triangle(Stream.of(pointA, pointB, pointC).toList());
        Assertions.assertEquals(pointA, triangle.getPointA());
        Assertions.assertEquals(pointB, triangle.getPointB());
        Assertions.assertEquals(pointC, triangle.getPointC());
    }

    @Test
    public void createTriangleNotEnoughPoints() throws Exception {
        // Проверка на наличие трех точек
        var pointA = new Point(1, 2, 3);
        Throwable exception = Assertions.assertThrows(Exception.class, () -> {
            new Triangle(Stream.of(pointA).toList());
        });
        Assertions.assertEquals("Должно быть 3 точки", exception.getMessage());
    }

    @Test
    public void createTriangleSamePoints() throws Exception {
        // Проверка на наличие трех точек
        var pointA = new Point(1, 2, 3);
        var pointB = new Point(1, 2, 4);
        Throwable exception = Assertions.assertThrows(Exception.class, () -> {
            new Triangle(Stream.of(pointA, pointA, pointB).toList());
        });
        Assertions.assertEquals("Не должно быть совпадающих точек", exception.getMessage());
    }


    @Test
    public void getArea() throws Exception {
        // Проверка путем сверки результатов с калькулятором
        // https://ru.onlinemschool.com/math/assistance/vector/triangle_area/
        var expectedArea = 0.5;
        var pointA = new Point(1, 2, 3);
        var pointB = new Point(1, 2, 4);
        var pointC = new Point(1, 3, 4);
        var triangle = new Triangle(Stream.of(pointA, pointB, pointC).toList());
        var triangleArea = triangle.getArea();
        Assertions.assertEquals(expectedArea, triangleArea);
    }

    @Test
    public void getPerimeter() throws Exception {
        // Проверка путем сверки результатов с известным примером
        // https://ru.onlinemschool.com/math/assistance/vector/triangle_area/
        // Для проверки возьмем координаты известного равностороннего треугольника с длинной стороны 10
        // https://ru-static.z-dn.net/files/d24/2922086f26f06490ce275b08ba0796e6.jpeg
        var expectedPerimeter = 30;
        var pointA = new Point(0, 0, 0);
        var pointB = new Point(5, 5 * sqrt(3), 0);
        var pointC = new Point(10, 0, 0);
        var triangle = new Triangle(Stream.of(pointA, pointB, pointC).toList());
        var trianglePerimeter = triangle.getPerimeter();
        Assertions.assertEquals(expectedPerimeter, trianglePerimeter);
    }

    @Test
    public void test_getTriangleAngleType_correct() throws Exception {
        // Проверяем что тупоугольный треугольник является таковым
        Assertions.assertEquals(TriangleAngleType.OBTUSE, obtuseArbitraryTriangle.getTriangleAngleType());
        // Проверяем что прямоугольный треугольник является таковым
        Assertions.assertEquals(TriangleAngleType.RIGHT, rightArbitraryTriangle.getTriangleAngleType());
        // Проверяем что остроугольный треугольник является таковым
        Assertions.assertEquals(TriangleAngleType.ACUTE, acuteEquilateralTriangle.getTriangleAngleType());
    }

    @Test
    public void test_getTriangleSideType_correct() throws Exception {
        // Проверяем что произвольный треугольник является таковым
        Assertions.assertEquals(TriangleSideType.ARBITRARY, obtuseArbitraryTriangle.getTriangleSideType());
        // Проверяем что равнобедренный треугольник является таковым
        Assertions.assertEquals(TriangleSideType.ISOSCELES, obtuseIsoscelesTriangle.getTriangleSideType());
        // Проверяем что равносторонний треугольник является таковым
        Assertions.assertEquals(TriangleSideType.EQUILATERAL, acuteEquilateralTriangle.getTriangleSideType());
    }
}
