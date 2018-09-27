import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class FindShortestPath {

	public static Result find(int source, int target, boolean[][] graph) {
	
		//setting up variables
		int distance=0;
		int value=0;
		
		//create 2D array of integers to hold path distance data
		Integer[][] path=new Integer[graph.length][graph.length];
		
		//create stack to hold integer values
		Stack<Integer> stack = new Stack<Integer>();
		
		//initialise path matrix with 0 values
		for(int i=0;i<path.length;i++) {
			for (int j=0;j<path[0].length;j++) {
				path[i][j]=0;
			}
		}
				
		//create visited array of possible maximum size
		boolean [] visited=new boolean[graph.length];
		
	
		//put source into start of stack
		stack.push(source);
		
		//create array to hold previous node values
		Integer[]prevNodeArray=new Integer[graph.length];
		
		//initialise array with source values
		for(int i=0;i<prevNodeArray.length;i++) {
			prevNodeArray[i]=source;
		}
		
		//create current node, initialise to source
		int currentNode=source;

		
		//while there is something in the stack
		while(!stack.empty()) {
			
			//pull the top item from the stack
			currentNode = stack.pop();

			//if current node has already been visited, loop again
			if( visited[ currentNode ] ) {
				continue;
			}
			
			//mark currently visited node as having been visited
			visited[ currentNode ] = true;
			
			//System.out.println( currentNode );
			
			//take a copy of neighbours of current node
			boolean[]neighbours=graph[currentNode];
			

			//for every neighbour
			for( int i = 0; i < neighbours.length; ++i ){

				//if have not visited neighbour and neighbour not in stack and is a neighbour
				if( ! visited[ i ] && ! stack.contains( i ) && neighbours[i])  {
					//add neighbour[i] to stack
					stack.push( i );
					//save current node into prevNode array
					prevNodeArray[i]=currentNode;
					
					//set the value based on the previous node and current node from path matrix (this is a distance from source)
					value=path[prevNodeArray[currentNode]][currentNode];
					//use this value to save a distance from source into path matrix
					path[currentNode][i]=value+1;

				} //end if
				
			} //end for

		} //end while
		
//		//print out path matrix
//		for(int i=0;i<path.length;i++) {
//			for (int j=0;j<path[0].length;j++) {
//				System.out.print(path[i][j]+" , ");
//			}
//			System.out.println("");
//		}
		
		//find distance from source to target from path matrix
		for(int i=0;i<path.length;i++) {
			if(path[i][target]>0) {
				distance=path[i][target];
				break;
			}
		}
		
		//print out distance
		System.out.println("Least number of edges between "+source+" and "+target+" is "+distance);
		
		//find nodepath from source to target	
		ArrayList<Integer> nodePath=nodePath(source, target, prevNodeArray);
		//print out node path
		System.out.print("The node path is: ");
		for(int i=0;i<nodePath.size();i++) {
			System.out.print(nodePath.get(i)+" , ");
		}
		System.out.println();
		
		Result result=new Result(distance, nodePath);
	return result;
		
	} //end find
	
	
	//take prevNodes array, return the nodePath as an arraylist
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
	
	
	//checking source and target are valid input
	public static int validInput(int source, int target, boolean[][] graph) {
		
		//check that source and target integer values are both within indices of graph
		if(source<graph.length && target<graph.length && source >=0 && target >=0) {
			return 1;
		}else {
			return -1;
		}
		
	}
	
	
	public static void main(String[] args) {

		int source=1;
		int target=7;
		
		AdjacencyMatrix obj = new AdjacencyMatrix( 11 );

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
		obj.addEdge( 8, 9);
		obj.addEdge(7, 6);
		obj.print();
		System.out.println("---- Graph (END) ----" );

		int isValid=validInput(source, target, obj.getGraph());
		
		if(isValid<0) {
			System.out.println("Invalid inputs, cannot find path");
		}else {

			System.out.println("---- Traversal (START) ----" );
			Result result = find( source, target , obj.getGraph() );
			System.out.println("---- Traversal (END) ----" );
		}
	}

	
}
