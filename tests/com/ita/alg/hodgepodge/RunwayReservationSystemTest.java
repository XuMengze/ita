package com.ita.alg.hodgepodge;

import org.junit.Test;

public class RunwayReservationSystemTest {
    @Test
    public void testRunwayReservationSystemArrange() {
        RunwayReservationSystem r = new RunwayReservationSystem(37, 3);
        assert r.tryArrange(46);
        assert r.tryArrange(49);
        assert r.tryArrange(41);
        assert r.tryArrange(56);

        assert !r.tryArrange(44);
        assert r.tryArrange(53);
        assert !r.tryArrange(20);
    }

    @Test
    public void testRunwayReservationSystemCount() {
        RunwayReservationSystem r = new RunwayReservationSystem(37, 3);
        for (Integer i : new int[]{49, 46, 79, 43, 64, 83}) {
            r.tryArrange(i);
        }
        assert r.countLandBefore(79) == 5;
        assert r.countLandBefore(65) == 4;
        assert r.countLandBefore(46) == 2;
    }
}
