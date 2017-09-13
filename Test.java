/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import javax.swing.JFrame;

public class Test {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException{
		boolean show = false;
		boolean directions = false;
		boolean meridianmap = false;
		String start = null;
		String end = null;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Road Map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		
		for(int i =0; i <args.length;i++){
			if(args[i].equals("[-show]")){
				show = true;
			}
			if(args[i].equals("[-directions")){
				directions = true;
				start = args[i+1].replaceAll("[^a-zA-Z\\d-]+", "");
				end = args[i+2].replaceAll("[^a-zA-Z\\d-]+", "");
			}
			if(args[i].equals("[-meridianmap]")){
				meridianmap = true;
			}
		}
	
		
		Graph graph = new Graph();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		File inFile = new File(args[2]);
		Scanner scan = new Scanner(inFile);
		
		while(scan.hasNextLine()){
			String type = scan.next();
			
			if(type.equals("i")){
				String name = scan.next();
				double latt = scan.nextDouble();
				double longt = scan.nextDouble();
				Vertex temp = new Vertex(name, latt, longt);
				vertices.add(temp);
				graph.insertVertex(temp);
				scan.nextLine();
				
				if(latt > graph.maxLat){
					graph.maxLat = latt;
				}
				if (longt >graph.maxLong){
					graph.maxLong = longt;
				}
				
				if(latt < graph.minLat){
					graph.minLat = latt;
				}
				if(longt < graph.minLong){
					graph.minLong = longt;
				}
			}
			else{
				String name = scan.next();
				String inter1 = scan.next();
				String inter2 = scan.next();
				Edge temp = new Edge(name, inter1, inter2);
				edges.add(temp);
			}
			
		}
		
		while(!edges.isEmpty()){
			graph.insertEdge(edges.remove(0));
		}
		
		if(directions == true && show == true){
			graph.weightedPath(start, end);
			System.out.println("Weighted Path: ");
			graph.table.get(end).printPath();
			System.out.println();
			System.out.println(graph.table.get(end).distance);
			Map map = new Map(graph, end);
			frame.add(map);
			frame.setVisible(show);
			
		}
		else if(directions == true){
			graph.weightedPath(start, end);
			System.out.print("Weighted Path: ");
			graph.table.get(end).printPath();
			System.out.println();
			System.out.println(graph.table.get(end).distance);
		}
		
		if(meridianmap == true && show == true){
			System.out.print("Roads traveled: ");
			ArrayList<String> names = graph.MWST(vertices);
			while(!names.isEmpty()){
				System.out.print(names.remove(0)+ " ");
			}
			Map map = new Map(graph, true);
			frame.add(map);
			frame.setVisible(show);
		}
		else if(meridianmap == true){
			System.out.print("Roads traveled: ");
			ArrayList<String> names = graph.MWST(vertices);
			while(!names.isEmpty()){
				System.out.print(names.remove(0)+ " ");
			}
		}
		
		if(show == true && directions == false && meridianmap == false){
			Map map = new Map(graph);
			frame.add(map);
			frame.setVisible(show);
		}
		
		
		
		
		
		
		
		
		}//end main
	
	
}//end class
