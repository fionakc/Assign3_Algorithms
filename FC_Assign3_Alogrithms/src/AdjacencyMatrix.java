/**SWEN502 - Algorithms Assignment
 * Fiona Crook
 * 300442873
 * 
 */

/*Creates an adjacency matrix
 * Used for the purposes of inputing data in FindShortestPath
 * 
 */

public class AdjacencyMatrix {

	//attributes
	private boolean[][] graph;
	
	//constructor
	public AdjacencyMatrix(int size) {
		graph=new boolean[size][size];
	}
	
	//getter
	public boolean[][] getGraph() {
		return graph;
	}
	
	//methods
	public void addEdge(int node, int neighbour) {
		graph[node][neighbour]=true;
		graph[neighbour][node]=true; //because graph is undirected
				
	}
	
	public void removeEdge(int node, int neighbour) {
		graph[node][neighbour]=false;
		graph[neighbour][node]=false;
	}
	
	public void print() {
		for(int i=0;i<graph.length;i++) {
			System.out.print(i+";");
			for(int j=0;j<graph[0].length;j++) {
				if(graph[i][j]) {
					System.out.print(1+",");
				}else {
					System.out.print(0+",");
				}
			}
			System.out.println();
		}
	}
	
}
