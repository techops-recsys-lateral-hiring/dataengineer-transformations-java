import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SanityTest {

  @Test
  public void testTruth() {
    assertEquals(true, true);
  }

  @Test
  public void testFalse() {
    assertNotEquals(true, false);
  }
}