import org.junit.Test;

import static org.junit.Assert.*;

public class SanityTest {

    @Test
    public void testTruth() {
        assertEquals(true, true);
    }

    @Test
    public void testFalse() {
        assertNotEquals(true,false);
    }
}