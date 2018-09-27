
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
				//System.out.print(graph[i][j]+",");
				
//				Dio's print stmt (??), very weird
//				if(graph[i][j]) {
//					System.out.print(graph[i][j]+",");
//				}
				
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
