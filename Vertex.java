/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
import java.util.LinkedList;
import java.util.ListIterator;

public class Vertex {
	protected String name;
	protected double latti;
	protected double longt;
	protected LinkedList<Node> roads;
	protected boolean known;
	protected double distance;
	protected Vertex path;
	protected int roadnum;
	protected boolean found;
	
	Vertex(String name, double latt, double longt){
		this.name = name;
		this.latti = latt;
		this.longt = longt;
		roads = new LinkedList<Node>();
		known = false;
		found = false;
		distance = Double.POSITIVE_INFINITY;
		path = null;
	}
	
	public String getName(){
		return name;
	}
	
	public double getLatt(){
		return latti;
	}
	
	public double getLong(){
		return longt;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public boolean getKnown(){
		return known;
	}
	
	public void insertRoad(Node road){
		roads.addFirst(road);
		roadnum++;
	}
	
	public void printRoadNames(){
		ListIterator<Node> roadList = roads.listIterator(0);
		while(roadList.hasNext()){
			System.out.print(roadList.next().name + " ");
		}
		
		
	}
	
	
	
	public  void printPath(){
		if(this.path == null){
			System.out.print(this.name+ " ");
		}
		else{
			this.path.printPath();
			System.out.print(this.name+" ");
		}
		
	}
	
	
}
