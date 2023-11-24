package model;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {
  public static DijkstraResult<String, Weight> dijkstra(Graph<String, Weight> g, Vertex<String> origin) {
    List<Vertex<String>> unvisited = new ArrayList<>();
    Map<Vertex<String>, Double> costs = new HashMap<>();
    Map<Vertex<String>, Vertex<String>> predecessors = new HashMap<>();

    // TODO: implement algorithm. Note: Init costs with Double.MAX_VALUE
    // Step 1. Start iterating the vertices list and filling the cost, predecessors and unvisited structures.
    private static final double INFINITY = MAX_VALUE;
    // Step 2. Add origin with a cost of zero

    // Step 3. Iterate all unvisited vertices and apply the algorithm presented at TP class
    // https://moodle.ips.pt/2324/pluginfile.php/114556/mod_label/intro/11_Grafos_Dijkstra.pdf (page 12)


    return new DijkstraResult<>(origin, costs, predecessors);
  }



  private static Vertex<String> findMinimumCostVertex(Map<Vertex<String>, Double> costs, List<Vertex<String>> unvisited) {
    // If there are any isolated vertices, at some point the vertex that must be chosen
    // is isolated. We safeguard this with (Double.MAX_VALUE < Double.POSITIVE_INFINITY)
    Vertex<String> minCostVertex = null;
    double minDistance = Double.POSITIVE_INFINITY;
    for (Vertex<String> u : unvisited) {
      double distance = costs.get(u);
      if(distance < minDistance) {
        minDistance = distance;
        minCostVertex = u;
      }
    }
    return minCostVertex;
  }
}
