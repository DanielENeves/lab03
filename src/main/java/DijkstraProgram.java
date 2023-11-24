import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import model.DijkstraResult;
import model.TypeOfCost;
import model.Weight;

import static model.Dijkstra.dijkstra;

public class DijkstraProgram {
    public static void main(String[] args) {
        Graph<String, Weight> graph = new GraphEdgeList<>();

        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");
        Vertex<String> f = graph.insertVertex("F");

      graph.insertEdge(a, b, new Weight("A-B",4));
      graph.insertEdge(a, c, new Weight("A-C",5));
      graph.insertEdge(b, c, new Weight("B-C",11));
      graph.insertEdge(b, e, new Weight("B-E",7));
      graph.insertEdge(c, e, new Weight("C-E",3));
      graph.insertEdge(b, d, new Weight("B-D",9));
      graph.insertEdge(f, d, new Weight("F-D",2));
      graph.insertEdge(e, d, new Weight("E-D",13));
      graph.insertEdge(e, f, new Weight("E-F",6));

      System.out.println(graph);

      DijkstraResult<String, Weight> result = dijkstra(graph, c);
      System.out.println(result);
      System.out.printf("Minimum cost path (C -> D): %s with cost %.2f \n",
        result.getMinimumCostPathTo(d),
        result.getMinimumCostTo(d));

      result = dijkstra(graph, c, TypeOfCost.SHORTEST_PATH);
      System.out.println("\n\n\n"+result);

      System.out.printf("Shortest path (C -> D): %s with cost %.2f \n",
        result.getMinimumCostPathTo(d),
        result.getMinimumCostTo(d));

    }

}
