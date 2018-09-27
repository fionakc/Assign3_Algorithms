import java.util.Stack;

public class FindShortestPath {

	public static void find(int source, int target, boolean[][] graph) {
	
	//check if source and target are directly next to each other
	if(graph[source][target]) {
		System.out.println("neighbours, shortest path = 1");
		
	}else {
		
		Integer[][] path=new Integer[graph.length][graph.length];
		
		for(int i=0;i<path.length;i++) {
			for (int j=0;j<path[0].length;j++) {
				path[i][j]=0;
			}
		}
				
		int value=0;
		
		//visited array of possible maximum size
		boolean [] visited=new boolean[graph.length];
		
		//create stack of integers
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> prev=new Stack<Integer>();
		
		//put source into start of stack
		stack.push(source);
		//prev.push(source);
		
		Integer[]prevNodeArray=new Integer[graph.length];
		for(int i=0;i<prevNodeArray.length;i++) {
			prevNodeArray[i]=source;
		}
		
		int prevNode=source;
		int currentNode=source;
		int prevTemp=source;
		
		//while there is something in the stack
		while(!stack.empty()) {
			
			//then ignore this
			//prevNode=currentNode;
			
			//pull the top item from the stack
			currentNode = stack.pop();
			//if stack not empty, prevNode=peeps tack, else is currentnode???? - not here
			//value++;
			
			//value=path[prevNodeArray[currentNode]][currentNode]; //keep here?
			
			//prevNode=prevTemp;
			
			//if current node has already been visited, loop again
			if( visited[ currentNode ] ) {
				continue;
			}
			
			//mark currently visited node as having been visited
			visited[ currentNode ] = true;
			
			System.out.println( currentNode );
			
			//copy of neighbours
			boolean[]neighbours=graph[currentNode];
			
			
			//prevNode=prev.pop();
			
			//for every neighbour
			for( int i = 0; i < neighbours.length; ++i ){
				//System.out.println("loop");

				//if not visited neighbour and neighbour not in stack and is a neighbour, add neighbour to stack
				if( ! visited[ i ] && ! stack.contains( i ) && neighbours[i])  {
					stack.push( i );
					prevNodeArray[i]=currentNode;
					//prev.push(i);
					//prevtemp is peek? >>move outside loop?
					//prevTemp=stack.peek();
					value=path[prevNodeArray[currentNode]][currentNode];
					path[currentNode][i]=value+1;
					//System.out.println("stack input "+stack.peek());
				} //end if
				
			} //end for
//			if(!stack.empty()) {
//				prevTemp=stack.peek();
//			}
			
		} //end while
		
		
		for(int i=0;i<path.length;i++) {
			for (int j=0;j<path[0].length;j++) {
				System.out.print(path[i][j]+" , ");
			}
			System.out.println("");
		}
		
	} //end if
		
	} //end find
	
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

		int source=3;
		int target=0;
		
		AdjacencyMatrix obj = new AdjacencyMatrix( 6 );

		System.out.println("---- Graph (START) ----" );
		obj.addEdge( 0, 1 );
		obj.addEdge( 0, 2 );
		obj.addEdge( 2, 3 );
		obj.addEdge( 2, 4 );
		obj.addEdge( 3, 5 );
		obj.addEdge( 4, 5 );
		obj.print();
		System.out.println("---- Graph (END) ----" );

		int isValid=validInput(source, target, obj.getGraph());
		
		if(isValid<0) {
			System.out.println("Invalid inputs, cannot find path");
		}else {
		
			//FindShortestPath bfs = new FindShortestPath();
	
			//not traversing correctly
			System.out.println("---- Traversal (START) ----" );
			find( source, target , obj.getGraph() );
			System.out.println("---- Traversal (END) ----" );
		}
	}

	
}
