/**SWEN502 - Algorithms Assignment
 * Fiona Crook
 * 300442873
 * 
 */

/*Takes a source node, target node, and adjacency matrix
 * Returns an object containing the shortest distance (number of edges) between the two nodes
 * and the path taken
 * 
 * 
 * Main method at the bottom of the page
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class FindShortestPath {

	/*find the shortest path between source and target in the graph, return as Result object
	 */
	public static Result find(int source, int target, boolean[][] graph) {
	
		
		//create queue to hold integer values
		Queue<Integer> queue = new LinkedList<Integer>();
				
		//create visited array of possible maximum size
		boolean [] visited=new boolean[graph.length];
			
		//put source into start of queue
		queue.add( source );
		
		//create array to hold previous node values
		Integer[]prevNodeArray=new Integer[graph.length];
		
		//initialise array with source values
		for(int i=0;i<prevNodeArray.length;i++) {
			prevNodeArray[i]=source;
		}
		
		//create current node, initialise to source
		int currentNode=source;

		
		//while there is something in the queue
		while(!queue.isEmpty()) {
			
			//pull the top item from the queue
			currentNode = queue.remove();

			//if current node has already been visited, loop again
			if( visited[ currentNode ] ) {
				continue;
			}
			
			//mark currently visited node as having been visited
			visited[ currentNode ] = true;
			
			//take a copy of neighbours of current node
			boolean[]neighbours=graph[currentNode];
			

			//for every neighbour
			for( int i = 0; i < neighbours.length; ++i ){

				//if have not visited neighbour and neighbour not in stack and is a neighbour
				if( ! visited[ i ] && ! queue.contains( i ) && neighbours[i])  {
					
					//add neighbour[i] to queue
					queue.add( i );
					
					//save current node into prevNode array at location i
					prevNodeArray[i]=currentNode;

				} //end if
				
			} //end for

			//stop the while loop once found target
			if(currentNode==target) {
				break;
			}
		} //end while
		
		
		//find nodepath from source to target	
		ArrayList<Integer> nodePath=nodePath(source, target, prevNodeArray);
		int distance =nodePath.size()-1;
		
		Result result=new Result(distance, nodePath);
		
	return result;
		
	} //end find
	
	
	/*take prevNodes array, return the nodePath as an arraylist
	 */
	public static ArrayList<Integer> nodePath(int source, int target, Integer[] prevNodes){
		//create arraylist
		ArrayList<Integer> nodePath=new ArrayList<Integer>();
		
		//add the target into arraylist
		nodePath.add(target);
		
		//create the nodeval, give it a value
		int nodeVal=target;
		
		//loop while haven't reached source
		while(nodeVal!=source) {
			//find new nodeval
			nodeVal=prevNodes[nodeVal];
			//add nodeval into nodePath
			nodePath.add(nodeVal);
		}
		
		//reverse arraylist so it goes from source to target
		Collections.reverse(nodePath);
				
		return nodePath;
	}
	
	
	/*checking source and target are valid input
	 */
	public static int validInput(int source, int target, boolean[][] graph) {
		
		if(graph==null) {
			System.out.println("null graph");
			return -1;
		}
		//check that source and target integer values are both within indices of graph
		if(source<graph.length && target<graph.length && source >=0 && target >=0) {
			return 1;
		}else {
			return -1;
		}
		
	}
	
	
	public static void main(String[] args) {

		//choose source and target nodes
		int source=3;
		int target=1;
		
		
		//initialise graph matrix for testing purposes
//		AdjacencyMatrix obj = new AdjacencyMatrix( 6 );
		AdjacencyMatrix obj = new AdjacencyMatrix( 14 );

		//add in edges to graph matrix for testing purposes
		System.out.println("---- Graph (START) ----" );
		obj.addEdge( 0, 1 );
		obj.addEdge( 0, 2 );
		obj.addEdge( 2, 3 );
		obj.addEdge( 2, 4 );
		obj.addEdge( 3, 5 );
		obj.addEdge( 4, 5 );
		
		obj.addEdge( 1, 6 );
		obj.addEdge( 2, 10 );
		obj.addEdge( 3, 7 );
		obj.addEdge( 5, 8 );
		obj.addEdge( 4, 9 );
		obj.addEdge( 8, 9 );
		obj.addEdge( 7, 6 );
		obj.addEdge( 8, 7 );
		//obj.print();
		System.out.println("---- Graph (END) ----" );
		System.out.println();
		

		//check for valid input of source and target values
		int isValid=validInput(source, target, obj.getGraph());
		
		if(isValid<0) { 
			System.out.println("Invalid inputs, cannot find path");
		}else {

			System.out.println("---- Traversal (START) ----" );
			
			Result result = find( source, target , obj.getGraph() );
			
			//print the distance
			System.out.println("Least number of edges between node "+source+" and node "+target+" is "+result.getDistance());
			
			//print the node path
			System.out.print("The node path is: ");
			for(int i=0;i<result.getNodePath().size();i++) {
				System.out.print(result.getNodePath().get(i)+" , ");
			}
			System.out.println();
			
			System.out.println("---- Traversal (END) ----" );
		}
	}

	
}
