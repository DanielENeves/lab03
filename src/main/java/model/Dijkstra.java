package model;

import com.brunomnsilva.smartgraph.graph.Edge;
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
    for (Vertex<String> vertex : g.vertices()) {
      costs.put(vertex, Double.MAX_VALUE);
      predecessors.put(vertex, null);
      unvisited.add(vertex);
    }
    // Step 2. Add origin with a cost of zero
    costs.put(origin, 0.0);

    // Step 3. Iterate all unvisited vertices and apply the algorithm presented at TP class
    // https://moodle.ips.pt/2324/pluginfile.php/114556/mod_label/intro/11_Grafos_Dijkstra.pdf (page 12)
    while (!unvisited.isEmpty()) {
      Vertex<String> current = findMinimumCostVertex(costs, unvisited);
      unvisited.remove(current);

      // Step 4: Explore neighbors of the current vertex
      for (Edge<Weight, String> edge : g.incidentEdges(current)) {
        Vertex<String> neighbor = g.opposite(current, edge);
        double weight = edge.element().getCost();
        double newCost = costs.get(current) + weight;

        // Step 5: Update cost and predecessor if a shorter path is found
        if (newCost < costs.get(neighbor)) {
          costs.put(neighbor, newCost);
          predecessors.put(neighbor, current);
        }
      }
    }

    return new DijkstraResult<>(origin, costs, predecessors);
  }

  public static DijkstraResult<String, Weight> dijkstra(Graph<String, Weight> g, Vertex<String> origin, TypeOfCost type) {
    List<Vertex<String>> unvisited = new ArrayList<>();
    Map<Vertex<String>, Double> costs = new HashMap<>();
    Map<Vertex<String>, Vertex<String>> predecessors = new HashMap<>();

    // Inicializa os custos com Double.MAX_VALUE
    for (Vertex<String> vertex : g.vertices()) {
      costs.put(vertex, Double.MAX_VALUE);
      predecessors.put(vertex, null);
      unvisited.add(vertex);
    }

    // Adiciona a origem com custo zero
    costs.put(origin, 0.0);

    // Itera todos os vértices não visitados e aplica o algoritmo de Dijkstra
    while (!unvisited.isEmpty()) {
      Vertex<String> current = findMinimumCostVertex(costs, unvisited);
      unvisited.remove(current);

      for (Edge<Weight, String> edge : g.incidentEdges(current)) {
        Vertex<String> neighbor = g.opposite(current, edge);
        double newCost = costs.get(current) + edge.element().getCost(type);

        // Atualiza o custo e o predecessor se um caminho mais curto for encontrado
        if (newCost < costs.get(neighbor)) {
          costs.put(neighbor, newCost);
          predecessors.put(neighbor, current);
        }
      }
    }
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

  public static DijkstraResult<String, Weight> dijkstraEdges (Graph<String, Weight> g,
                                                              Vertex<String> origin){
    List<Vertex<String>> unvisited = new ArrayList<>();
    Map<Vertex<String>, Double> costs = new HashMap<>();
    Map<Vertex<String>, Vertex<String>> predecessors = new HashMap<>();
    Map<Vertex<String>, Edge<Weight, String>> edges = new HashMap<>();

    // Initialize costs with Double.MAX_VALUE
    for (Vertex<String> vertex : g.vertices()) {
      costs.put(vertex, Double.MAX_VALUE);
      predecessors.put(vertex, null);
      unvisited.add(vertex);
    }

    // Add origin with a cost of zero
    costs.put(origin, 0.0);

    // Iterate all unvisited vertices and apply the modified Dijkstra algorithm
    while (!unvisited.isEmpty()) {
      Vertex<String> current = findMinimumCostVertex(costs, unvisited);
      unvisited.remove(current);

      for (Edge<Weight, String> edge : g.incidentEdges(current)) {
        Vertex<String> neighbor = g.opposite(current, edge);
        double newCost = costs.get(current) + edge.element().getCost();

        // Update cost, predecessor, and edge if a shorter path is found
        if (newCost < costs.get(neighbor)) {
          costs.put(neighbor, newCost);
          predecessors.put(neighbor, current);
          edges.put(neighbor, edge);
        }
      }
    }
    return new DijkstraResult<>(origin, costs, predecessors, edges);
  }
}
