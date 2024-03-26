import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) throws Exception {
        // Создаем список треугольников
        var triangles = Stream.of(
                // Равнобедренный тупоугольный треугольник
                new Triangle(
                        Stream.of(
                                new Point(1, 2, 3),
                                new Point(1, 2, 4),
                                new Point(1, 3, 4)
                        ).toList()
                ),
                // Равносторонний остроугольный треугольник
                new Triangle(
                        Stream.of(
                                new Point(0, 0, 0),
                                new Point(5, 5 * sqrt(3), 0),
                                new Point(10, 0, 0)
                        ).toList()
                ),
                // Равносторонний остроугольный треугольник
                new Triangle(
                        Stream.of(
                                new Point(1, 0, 0),
                                new Point(0, 1, 0),
                                new Point(0, 0, 1)
                        ).toList()
                ),
                // Произвольный прямоугольный треугольник
                new Triangle(
                        Stream.of(
                                new Point(0, 0, 0),
                                new Point(3, 0, 0),
                                new Point(0, 4, 0)
                        ).toList()
                ),
                // Произвольный тупоугольный треугольник
                // http://www.pm298.ru/reshenie/wr0300.php
                new Triangle(
                        Stream.of(
                                new Point(2, 3, 0),
                                new Point(6, 7, 0),
                                new Point(-7, 2, 0)
                        ).toList()
                )
        ).toList();
        System.out.println("Все треугольники:");
        System.out.println(triangles);

        var service = new TaskService(triangles);


        // Определяем треугольники по типу угла.
        // Определяем словарь
        // Ключ: Тип угла треугольника
        // Значение: Список треугольников по типу их угла (ключу)
        var trianglesByAngleTypesMap = service.getTrianglesByAnglesTypes();
        System.out.println("Треугольники по типу угла:");
        System.out.println(trianglesByAngleTypesMap);


        // Определяем треугольники по типу стороны.
        // Определяем словарь
        // Ключ: Тип стороны треугольника
        // Значение: Список треугольников по типу их стороны (ключу)
        var trianglesBySideTypesMap = service.getTrianglesBySidesTypes();
        System.out.println("Треугольники по типу сторон:");
        System.out.println(trianglesBySideTypesMap);

        // Для каждой категории выводим треугольники с максимальными/минимальными площадями/периметрами
        // Ключ: Тип треугольника
        // Значение: Список треугольников, относящихся к данному типу
        for (var entry : trianglesByAngleTypesMap.entrySet()) {
            TaskService.processTrianglesStats(entry.getKey(), entry.getValue());
        }
        for (var entry : trianglesBySideTypesMap.entrySet()) {
            TaskService.processTrianglesStats(entry.getKey(), entry.getValue());
        }
    }
}