package zest;


import net.jqwik.api.Arbitraries;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CourseScheduleTest {

    private static final int MAX_SOMETHING = 100;

    @Test
    public void testPreConditions() {
        CourseSchedule courseSchedule = new CourseSchedule();
        assertThrows(AssertionError.class, () -> courseSchedule.canFinish(-1, new int[][] {{0,1}}));
        assertThrows(AssertionError.class, () -> courseSchedule.canFinish(2, new int[][]{{0, 3}}));
        assertThrows(AssertionError.class, () -> courseSchedule.canFinish(4, new int[][]{{0, 3, 2}}));
        assertThrows(AssertionError.class, () -> courseSchedule.canFinish(2, new int[][]{{0, 0}}));
    }

    @Test
    public void testStandardCase() {
        CourseSchedule courseSchedule = new CourseSchedule();
        assertTrue(courseSchedule.canFinish(2, new int[][]{{0, 1}}));
        assertTrue(courseSchedule.canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
    }

    @Test
    public void testCycles() {
        CourseSchedule courseSchedule = new CourseSchedule();
        assertFalse(courseSchedule.canFinish(2, new int[][]{{0, 1}, {1, 0}}));
        assertFalse(courseSchedule.canFinish(3, new int[][] {{0, 1}, {1, 2}, {2, 0}}));
    }

    @Property
    void testProperty(@ForAll @IntRange(min = 2, max = MAX_SOMETHING) int numOfCourses) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int numberOfEdges = Arbitraries.integers().greaterOrEqual(0).lessOrEqual(150).sample();
        List<int[]> edges = new ArrayList<>();
        //generate random graph
        for (int i = 0; i < numberOfEdges; i++) {
            int value1 = Arbitraries.integers().greaterOrEqual(0).lessOrEqual(numOfCourses-1).sample();
            int value2 = Arbitraries.integers().greaterOrEqual(0).lessOrEqual(numOfCourses-1).sample();
            if(value1 == value2) {
                if(value2 != 0) {
                    value2 -= 1;
                }else{
                    value2 +=1;
                }
            }
            edges.add(new int[]{value1, value2});

        }
        //add cycle
        edges.add(new int[]{0, 1});
        edges.add(new int[]{1, 0});
        assertFalse(courseSchedule.canFinish(numOfCourses, edges.toArray(new int[0][0])));
    }


}
