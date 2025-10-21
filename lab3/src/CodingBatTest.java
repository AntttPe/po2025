import static org.junit.Assert.*;

public class CodingBatTest {

    // Tworzymy jedną instancję klasy CodingBat, której metody będziemy testować
    private CodingBat cb = new CodingBat();

    @org.junit.Test
    public void sleepIn() {
        // Test 1: Weekend, brak wakacji -> true
        assertTrue(cb.sleepIn(false, false));
        // Test 2: Dzień powszedni, wakacje -> true
        assertTrue(cb.sleepIn(true, true));
        // Test 3: Dzień powszedni, brak wakacji -> false
        assertFalse(cb.sleepIn(true, false));
        // Test 4: Weekend, wakacje -> false
        assertTrue(cb.sleepIn(false, true));
    }

    @org.junit.Test
    public void in1020() {
        // Test 1: Pierwsza jest w zakresie (15) -> Prawda
        assertTrue(cb.in1020(15, 99));
        // Test 2: Druga jest w zakresie (19) -> Prawda
        assertTrue(cb.in1020(100, 19));
        // Test 3: Żadna nie jest w zakresie -> False
        assertFalse(cb.in1020(100, 50));
    }

    @org.junit.Test
    public void sum3() {
        assertEquals(6, cb.sum3(new int[]{1, 2, 3}));
        assertEquals(1000, cb.sum3(new int[]{1, 999, 0}));
    }

    @org.junit.Test
    public void theEnd() {
        assertEquals("H", cb.theEnd("Hello", true));
        assertEquals("o", cb.theEnd("Hello", false));
    }
}