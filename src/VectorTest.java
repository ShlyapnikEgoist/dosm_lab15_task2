import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class VectorTest {
    @Test
    public void test_getVectorLength() throws Exception {
        var vector = new Vector(Stream.of(1.0, 1.0, 1.0).toList());
        Assertions.assertEquals(sqrt(3), vector.getVectorLength());
    }

    @Test
    public void test_getVectorProduct() throws Exception {
        var vectorA = new Vector(Stream.of(0.0, 1.0, 0.0).toList());
        var vectorB = new Vector(Stream.of(0.0, 0.0, 1.0).toList());
        var vectorC = new Vector(Stream.of(1.0, 0.0, 0.0).toList());
        var resultVector = Vector.getVectorProduct(vectorA, vectorB);
        Assertions.assertEquals(vectorC.getX(), resultVector.getX());
        Assertions.assertEquals(vectorC.getY(), resultVector.getY());
        Assertions.assertEquals(vectorC.getZ(), resultVector.getZ());
    }
}
