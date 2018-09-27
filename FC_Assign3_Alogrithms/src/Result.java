/**SWEN502 - Algorithms Assignment
 * Fiona Crook
 * 300442873
 * 
 */

/*This class takes in a distance integer, and an path arraylist,
 * and combines them into a single object
 * 
 */

import java.util.ArrayList;

public class Result {

	int distance;
	ArrayList<Integer>nodePath;
	
	public Result(int dist, ArrayList<Integer> path) {
		this.distance=dist;
		this.nodePath=path;
		
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public ArrayList<Integer> getNodePath(){
		return this.nodePath;
	}
	
	
}
