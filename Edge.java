/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
public class Edge {
	
	protected String inter1, inter2;
	protected double distance;
	protected String name;
	
	Edge(String name, String inter1, String inter2){
		this.name = name;
		this.inter1 = inter1;
		this.inter2 = inter2;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	
}
