package jbpt.bpmn.matrix;

import java.util.ArrayList;
import java.util.List;

import jbpt.bpmn.gobject.comparator.LexicographicComparator;
import org.jbpt.graph.abs.IDirectedEdge;
import org.jbpt.graph.abs.IDirectedGraph;
import org.jbpt.hypergraph.abs.IVertex;

/**
 * Implementation of adjacency matrices to represent {@link org.jbpt.graph.abs.IDirectedGraph IDirectedGraph}
 * implementations -e.g., {@link org.jbpt.pm.bpmn.Bpmn Bpmn} instances-.
 * <p>
 * This class allows:
 * <ul>
 *   <li>Derivation of the adjacency matrix of an existing model.
 * </ul><p>
 * This class does NOT allow:
 * <ul>
 *   <li>Generation of a BPMN model given an existing adjacency matrix.
 *   <li>Permanent storage of adjacency matrices -e.g., JSON files-.
 * </ul>
 * @param <E> template for edge -extends IDirectedEdge-.
 * @param <V> template for vertex -extends IVertex-.
 */
public class AdjacencyMatrix<E extends IDirectedEdge<V>, V extends IVertex> {
  /**
   * The graph attribute is not necessary. However, we wanted to keep the coding style consistent with JBPT's
   * {@link org.jbpt.algo.graph.TransitiveClosure TransitiveClosure} class.
   */
  private IDirectedGraph<E, V> g;
  private List<V> verticesAsList;
  private boolean[][] matrix;

  public AdjacencyMatrix(IDirectedGraph<E, V> g) {
    this.g = g;
    this.matrix = null;
    this.verticesAsList = new ArrayList<>(this.g.getVertices());
    /* F A S H Y J D K 8 */
    verticesAsList.sort(new LexicographicComparator());
  }

  protected void calculateMatrix() {
    matrix = new boolean[this.verticesAsList.size()][this.verticesAsList.size()];
    for (E e: this.g.getEdges()) {
      int source = this.verticesAsList.indexOf(e.getSource());
      int target = this.verticesAsList.indexOf(e.getTarget());
      matrix[source][target] = true;
    }
  }

  /**
   * Check if there exists an edge between two vertices
   * @param v1 Vertex
   * @param v2 Vertex
   * @return  <code>true</code> if there is a directed edge from v1 to v2, <code>false</code> otherwise.
   */
  public boolean hasEdge(V v1, V v2) {
    if (matrix == null)
      calculateMatrix();
    int i = this.verticesAsList.indexOf(v1);
    int j = this.verticesAsList.indexOf(v2);
    return matrix[i][j];
  }

  public String toString() {
    if (matrix == null) calculateMatrix();

    String result = "";
    result += "==================================================\n";
    result += " Adjacency matrix\n";
    result += "--------------------------------------------------\n";

    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%d : %s\n", i, verticesAsList.get(i));
    result += "--------------------------------------------------\n";

    result += "    ";
    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%-4d", i);
    result += "    \n";

    for (int i=0; i<verticesAsList.size(); i++) {
      result += String.format("%-4d", i);
      for (int j=0; j<verticesAsList.size(); j++) {
        result += String.format("%-4s",(matrix[i][j] ? "1" : "-"));
      }
      result += String.format("%-4d", i);
      result += "\n";
    }

    result += "    ";
    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%-4d", i);
    result += "    \n";

    result += "==================================================";
    return result;
  }

  public String toString(int label) {
    if (matrix == null) calculateMatrix();

    String result = "";
    result += "==================================================\n";
    result += " "+ label + ". Adjacency matrix\n";
    result += "--------------------------------------------------\n";

    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%d : %s\n", i, verticesAsList.get(i));
    result += "--------------------------------------------------\n";

    result += "    ";
    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%-4d", i);
    result += "    \n";

    for (int i=0; i<verticesAsList.size(); i++) {
      result += String.format("%-4d", i);
      for (int j=0; j<verticesAsList.size(); j++) {
        result += String.format("%-4s",(matrix[i][j] ? "+" : "-"));
      }
      result += String.format("%-4d", i);
      result += "\n";
    }

    result += "    ";
    for (int i=0; i<verticesAsList.size(); i++) result += String.format("%-4d", i);
    result += "    \n";

    result += "==================================================";
    return result;
  }
}
