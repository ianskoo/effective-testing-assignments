package zest;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //check prerequisites
        assert numCourses > 0 : "Number of courses must be greater than 0";
        for (int[] prerequisite : prerequisites) {
            assert prerequisite.length == 2 : "prerequisite length must be 2";
            assert prerequisite[0] != prerequisite[1] : "A course can't have itself as prerequisite";
            for (int req : prerequisite) {
                assert req >= 0 && req < numCourses : "can't have invalid course numbers";
            }
        }


        // Create a graph from prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(graph, i, visited, onPath)) {
                return false; // Cycle detected
            }
        }
        return true; // No cycle detected
    }

    private boolean hasCycle(List<List<Integer>> graph, int current, boolean[] visited, boolean[] onPath) {
        if (onPath[current]) return true; // Cycle detected
        if (visited[current]) return false; // Already visited

        visited[current] = true;
        onPath[current] = true;

        for (int neighbor : graph.get(current)) {
            if (hasCycle(graph, neighbor, visited, onPath)) {
                return true;
            }
        }

        onPath[current] = false; // Backtrack
        return false;
    }
}
