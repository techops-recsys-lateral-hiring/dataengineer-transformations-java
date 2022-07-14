import org.junit.Test;

import static org.junit.Assert.*;

public class SanityTest {

    @Test
    public void testTruth() {
        assertEquals(false, false);
    }

    @Test
    public void testFalse() {
        assertNotEquals(true,false);
    }
}