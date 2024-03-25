package zest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NeedleInHayTest {
    @Test
    void nullString() {
        Assertions.assertEquals(-1, NeedleInHay.find(null, "test"));
        Assertions.assertEquals(-1 , NeedleInHay.find("test", null));
        Assertions.assertEquals(-1, NeedleInHay.find(null, null));
    }

    @Test
    void bothEmpty() {
        Assertions.assertEquals(0, NeedleInHay.find("", ""));
    }

    @Test
    void oneEmpty() {
        Assertions.assertEquals(-1, NeedleInHay.find("", "test"));
        Assertions.assertEquals(-1, NeedleInHay.find("test", ""));
    }

    @Test
    void needleInHayStack() {
        Assertions.assertEquals(0, NeedleInHay.find("testabc", "test"));
    }

    @Test
    void needleBiggerThanHaystack() {
        Assertions.assertEquals(-1, NeedleInHay.find("test", "testAbc"));
    }

    @Test
    void standardCase() {
        Assertions.assertEquals(3, NeedleInHay.find("012345", "345"));
    }

}