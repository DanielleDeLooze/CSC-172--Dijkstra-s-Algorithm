/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.PriorityQueue;

public class Graph {
	
	protected HashMap<String, Vertex> table;
	protected int numEdges; //roads
	protected int numVertices;
	protected ComparerEdge compare = new ComparerEdge();
	protected PriorityQueue<Edge> queue = new PriorityQueue<Edge>(compare);
	protected double maxLat = -1000;
	protected double maxLong = -1000;
	protected double minLat = 1000;
	protected double minLong = 1000;
	protected Graph spanningTree = null;
	
	
	Graph(){
		table = new HashMap<String, Vertex>();
		numEdges = 0;
		numVertices = 0;
	}
	
	
	
	public void insertVertex(Vertex vertex){
		table.put(vertex.name, vertex);
		numVertices++;
	}
	
	public void insertEdge(Edge e){
		
		Vertex inter1 = table.get(e.inter1);
		Vertex inter2 = table.get(e.inter2);
		
		
		double distance = distance(inter1.latti, inter2.latti, inter1.longt, inter2.longt);
		e.setDistance(distance);
		queue.add(e);
		
		
		Node temp1 = new Node(e.name, inter1,distance, inter2);
		Node temp2 = new Node(e.name, inter2,distance, inter1);
		
		inter1.insertRoad(temp2);
		inter2.insertRoad(temp1);
		numEdges++;
	}
	
	public void show(){
		for(Vertex element: table.values()){
			System.out.print(element.name+": ");
			element.printRoadNames();
			System.out.println();
		}
	}
	
	public static double distance(double latt1, double latt2, double longt1, double longt2){ //algorithm found on https://www.google.com/search?q=miles+to+kilometers&oq=miles+to+kilometers&aqs=chrome..69i57j6j0l4.2960j0j7&sourceid=chrome&ie=UTF-8
		double radius = 6371; //radius of earth
		double degLat = degreestoRad((latt2 - latt1));
		double degLong = degreestoRad((longt2-longt1));
		
		double a = Math.sin(degLat/2) * Math.sin(degLat/2) + Math.cos(degreestoRad(latt1)) * Math.cos(degreestoRad(latt2)) * Math.sin(degLong/2) * Math.sin(degLong/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = radius * c; //in kilometers
		double distanceMiles = distance * .621371; //converting the distance from kilometers to miles
		return distanceMiles;
		
		
				
	}
	
	public static double degreestoRad(double degree){
		return degree * (Math.PI/180);
	}
	
	public void weightedPath(String start, String end){
		
		Comparer compare = new Comparer();
		PriorityQueue<Vertex> tree = new PriorityQueue<Vertex>(compare);
		
		Vertex origin = table.get(start);
		origin.distance = 0;
		origin.known = true;
		
		
		ListIterator<Node> roads = origin.roads.listIterator();
		while(roads.hasNext()){
			Node temp1 = roads.next();
			Vertex temp2 = temp1.pointer;
			temp2.distance = temp1.distance;
			temp2.path = origin;
			
			tree.add(temp2);
		}
		
		
		while(!table.get(end).known){
			Vertex v = tree.poll();
			v.known = true;
			
			ListIterator<Node> adj = v.roads.listIterator();
			while(adj.hasNext()){
				Node head = adj.next();
				Vertex headVertex = head.pointer;
				
				if(!headVertex.known){
					if(v.distance + head.distance < headVertex.distance){
						headVertex.distance = v.distance + head.distance;
						headVertex.path = v;
						tree.add(headVertex); //might not be correct spot for enqueue
					}
				}
			}
		}
		
		
		
	}
	
	public ArrayList<String> MWST(ArrayList<Vertex> list){
		ArrayList<String> spanning = new ArrayList<String>();
		Iterator<Edge> roads = queue.iterator();
		Graph minimum = new Graph();
		
		while(!list.isEmpty()){
			Vertex temp = list.remove(0);
			minimum.insertVertex(temp);
		}
		
		Edge first = roads.next();
		Vertex start = minimum.table.get(first.inter1);
		minimum.insertEdge(first);
		
		while(minimum.numEdges != (minimum.numVertices -1)){
			Edge min = null;
			if(roads.hasNext()){
				min = roads.next();
			}
			
			minimum.insertEdge(min);
			if(this.cycle(start) == true){
				minimum.removeEdge(min);
			}
			else{
				spanning.add(min.name);
			}
			
		}
		return spanning;
			
	}
	
	public void removeEdge(Edge e){
		Iterator<Node> firstinter = this.table.get(e.inter1).roads.iterator();
		Iterator<Node> secinter = this.table.get(e.inter2).roads.iterator();
		
		
		
		while(!firstinter.next().name.equals(e.name)){
			
		}
		firstinter.remove();
		
		while(!secinter.next().name.equals(e.name)){
			
		}
		secinter.remove();
		
		numEdges--;
	}
	
	public boolean cycle(Vertex start){
		start.found = true;
		ListIterator<Node> roads = start.roads.listIterator();
		while(roads.hasNext()){
			Node first = roads.next();
			if(!first.pointer.found){
				ListIterator<Node> find = first.pointer.roads.listIterator();
				boolean con = true;
				while(con){
					Node blah = find.next();
					if(blah.name.equals(first.name)){
						blah.explored = 1;
						con = false;
					}
				}
				
				first.explored = 1;
				Vertex end = first.pointer;
				cycle(end);
				
			}
			else{
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
		
	

	
	
	
}
