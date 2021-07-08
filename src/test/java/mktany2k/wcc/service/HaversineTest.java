package mktany2k.wcc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HaversineTest {

    @Test
    public void a() {
        double result = Haversine.distance(50.0359, -5.4253, 58.3838, -3.0412);
        Assertions.assertEquals(962.8530852121846, result);
    }
}
