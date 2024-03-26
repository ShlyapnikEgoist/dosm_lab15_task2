import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskService {
    private final List<Triangle> triangles;

    public TaskService(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public static Map<Triangle, Double> getTrianglesAreas(List<Triangle> triangles) throws Exception {
        // Получение площадей треугольников с привязкой к треугольникам
        var trianglesAreasMap = new HashMap<Triangle, Double>();
        for (Triangle triangle : triangles) {
            trianglesAreasMap.put(triangle, triangle.getArea());
        }
        return trianglesAreasMap;
    }

    public static Map<Triangle, Double> getTrianglesPerimeters(List<Triangle> triangles) throws Exception {
        // Получение периметров треугольников с привязкой к треугольникам
        var trianglesPerimetersMap = new HashMap<Triangle, Double>();
        for (Triangle triangle : triangles) {
            trianglesPerimetersMap.put(triangle, triangle.getPerimeter());
        }
        return trianglesPerimetersMap;
    }

    public static Map.Entry<Triangle, Double> getMinimum(Map<Triangle, Double> trianglesWithStat) {
        // Получение треугольника с минимальной характеристикой
        Map.Entry<Triangle, Double> minEntry = null;
        for (Map.Entry<Triangle, Double> entry : trianglesWithStat.entrySet()) {
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    public static Map.Entry<Triangle, Double> getMaximum(Map<Triangle, Double> trianglesWithStat) {
        // Получение треугольника с максимальной характеристикой
        Map.Entry<Triangle, Double> maxEntry = null;
        for (Map.Entry<Triangle, Double> entry : trianglesWithStat.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    public static void processTrianglesStats(ITriangleType triangleType, List<Triangle> triangles) throws Exception {
        // Получение периметров треугольников с привязкой к треугольникам
        var trianglesAreas = getTrianglesAreas(triangles);
        var trianglesPerimeters = getTrianglesPerimeters(triangles);
        var triangleStatWithMinimalArea = getMinimum(trianglesAreas);
        var triangleStatWithMaximumArea = getMaximum(trianglesAreas);
        var triangleStatWithMinimalPerimeter = getMinimum(trianglesPerimeters);
        var triangleStatWithMaximumPerimeter = getMaximum(trianglesPerimeters);
        System.out.println("Для типа треугольников: " + triangleType);
        System.out.println("Треугольник с минимальной площадью: " + triangleStatWithMinimalArea);
        System.out.println("Треугольник с максимальной площадью: " + triangleStatWithMaximumArea);
        System.out.println("Треугольник с минимальным периметром: " + triangleStatWithMinimalPerimeter);
        System.out.println("Треугольник с максимальным периметром: " + triangleStatWithMaximumPerimeter);
    }

    public List<Triangle> getTrianglesByAnglesType(TriangleAngleType triangleAngleType) {
        // Получить все треугольники определенного типа угла
        // Цикл по всем треугольникам с фильтрацией по типу угла
        return triangles.stream()
                .filter(triangle -> {
                    // Так как возможно исключение, то просто оборачиваем его и выводим в случае чего
                    try {
                        return triangle.getTriangleAngleType() == triangleAngleType;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public Map<TriangleAngleType, List<Triangle>> getTrianglesByAnglesTypes() {
        // Определяем треугольники по типу угла.
        // Определяем словарь
        // Ключ: Тип угла треугольника
        // Значение: список треугольников по типу их угла (ключу)
        var trianglesByAngleTypesMap = new HashMap<TriangleAngleType, List<Triangle>>();
        // Цикл по типу углов треугольника
        for (TriangleAngleType triangleAngleType : TriangleAngleType.values()) {
            var trianglesBySpecificAngleType = getTrianglesByAnglesType(triangleAngleType);
            // Кладем в словарь отфильтрованные треугольники по типу угла
            trianglesByAngleTypesMap.put(triangleAngleType, trianglesBySpecificAngleType);
        }
        return trianglesByAngleTypesMap;
    }

    public List<Triangle> getTrianglesBySidesType(TriangleSideType triangleSideType) {
        // Получить все треугольники определенного типа стороны
        // Цикл по всем треугольникам с фильтрацией по типу сторон
        return triangles.stream()
                .filter(triangle -> {
                    // Так как возможно исключение, то просто оборачиваем его и выводим в случае чего
                    try {
                        return triangle.getTriangleSideType() == triangleSideType;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public Map<TriangleSideType, List<Triangle>> getTrianglesBySidesTypes() {
        // Определяем треугольники по типу сторон.
        // Определяем словарь
        // Ключ: Тип стороны треугольника
        // Значение: список треугольников по типу их стороны (ключу)
        var trianglesBySideTypesMap = new HashMap<TriangleSideType, List<Triangle>>();
        // Цикл по типу углов треугольника
        for (TriangleSideType triangleSideType : TriangleSideType.values()) {
            var trianglesBySpecificSideType = getTrianglesBySidesType(triangleSideType);
            // Кладем в словарь отфильтрованные треугольники по типу угла
            trianglesBySideTypesMap.put(triangleSideType, trianglesBySpecificSideType);
        }
        return trianglesBySideTypesMap;
    }

}
