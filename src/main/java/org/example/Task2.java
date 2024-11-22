package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of test cases
        int testCases = Integer.parseInt(scanner.nextLine().trim());

        // Process each test case
        while (testCases-- > 0) {
            // Number of cities in the current test case
            int numCities = Integer.parseInt(scanner.nextLine().trim());
            Map<String, Integer> cityIndexMap = new HashMap<>();
            // Adjacency list to represent the graph
            List<List<int[]>> graph = new ArrayList<>();

            for (int i = 0; i < numCities; i++) {
                // Read the city name
                String cityName = scanner.nextLine().trim();
                cityIndexMap.put(cityName, i); // Map city name to its index

                // Read the number of neighbors for this city
                int numNeighbors = Integer.parseInt(scanner.nextLine().trim());

                // Initialize the adjacency list for the city
                graph.add(new ArrayList<>());
                for (int j = 0; j < numNeighbors; j++) {
                    String[] neighborData = scanner.nextLine().trim().split(" ");
                    int neighborIndex = Integer.parseInt(neighborData[0]) - 1; // Convert 1-based to 0-based index
                    int cost = Integer.parseInt(neighborData[1]);
                    graph.get(i).add(new int[]{neighborIndex, cost}); // Add the edge to the graph
                }
            }

            // Read the number of paths to compute
            int numPaths = Integer.parseInt(scanner.nextLine().trim());

            // Process each path query
            for (int i = 0; i < numPaths; i++) {
                String[] pathQuery = scanner.nextLine().trim().split(" ");
                String sourceCity = pathQuery[0];
                String targetCity = pathQuery[1];

                // Get the indices of the source and target cities
                int sourceIndex = cityIndexMap.get(sourceCity);
                int targetIndex = cityIndexMap.get(targetCity);

                // Find the minimum cost using Dijkstra's algorithm
                int cost = dijkstra(graph, sourceIndex, targetIndex);
                System.out.println(cost);
            }
            // Skip the blank line between test cases
            if (testCases > 0) scanner.nextLine();
        }
    }

    /**
     * Implements Dijkstra's algorithm to find the shortest path between two nodes in a graph.
     *
     * @param graph  The adjacency list representing the graph.
     * @param source The starting city's index.
     * @param target The target city's index.
     * @return The minimum transportation cost from source to target.
     */
    public static int dijkstra(List<List<int[]>> graph, int source, int target) {
        int n = graph.size(); // Total number of cities
        int[] distances = new int[n]; // Stores the shortest distances from the source
        Arrays.fill(distances, Integer.MAX_VALUE); // Initialize distances with a high value
        distances[source] = 0; // Distance to the source itself is 0

        // Priority queue to process cities by minimum distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0}); // Add the source city to the queue

        // Process the priority queue
        while (!pq.isEmpty()) {
            int[] current = pq.poll(); // Get the city with the minimum distance
            int currentCity = current[0];
            int currentDistance = current[1];

            // If the target city is reached, return the distance
            if (currentCity == target) {
                return currentDistance;
            }

            // Explore all neighbors of the current city
            for (int[] neighbor : graph.get(currentCity)) {
                int nextCity = neighbor[0];
                int weight = neighbor[1];
                int newDist = currentDistance + weight;

                // Update the distance if a shorter path is found
                if (newDist < distances[nextCity]) {
                    distances[nextCity] = newDist;
                    pq.add(new int[]{nextCity, newDist});
                }
            }
        }

        return -1; // If the path does not exist (according to the conditions of the task this should not happen)
    }
}
