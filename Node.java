/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */

public class Node {
	
	protected String name;
	protected Vertex pointer;
	protected double distance;
	protected Vertex parent;
	protected int explored = 0; //1 is found , -1 is unessential, 0 is null
	
	
	Node(String name, Vertex pointer, double distance, Vertex parent){
		this.name = name;
		this.pointer = pointer;
		this.distance = distance;
		this.parent = parent;
	}
	
	
}
