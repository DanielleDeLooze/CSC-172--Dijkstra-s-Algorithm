/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JComponent;

public class Map extends JComponent{
	
	private Graph graph;
	private Graph MWST;
	private final Color background = Color.WHITE;
	private final Color roads = Color.BLACK;
	private final Color highlight = Color.RED;
	private double rangex = 0;
	private double rangey = 0;
	private String end;
	private boolean directions = false;
	private boolean span = false;
	
	
	Map(Graph graph, String end){
		this.graph = graph;
		this.end = end;
		directions = true;
	}
	
	Map(Graph graph){
		this.graph = graph;
		
	}
	
	Map(Graph graph, boolean span){
		this.graph = graph;
		this.span = span;
	}
	

	
	public void paintComponent(Graphics g){
		g.setColor(background);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		rangex = (graph.maxLat - graph.minLat);
		rangey = (graph.maxLong - graph.minLong);
		/*
		for(Vertex element: graph.table.values()){
			double lat = this.getHeight() - ((this.getHeight() * (element.latti - graph.maxLat)) / rangey);
			double lon = this.getWidth() - ((this.getWidth() * (element.longt - graph.maxLong)) / rangex);
		*/	
		Iterator<Edge> list = graph.queue.iterator();
		while(list.hasNext()){
			Edge temp = list.next();
			g.setColor(roads);
			double lat1 = this.getHeight()-((this.getHeight()*(graph.table.get(temp.inter1).latti-graph.minLat) )/rangey);
			double lon1 = ((this.getWidth()*(graph.table.get(temp.inter1).longt-graph.minLong) )/rangex);
			double lat2 = this.getHeight()-((this.getHeight()*(graph.table.get(temp.inter2).latti-graph.minLat) )/rangey);
			double lon2 =((this.getWidth()*(graph.table.get(temp.inter2).longt-graph.minLong) )/rangex);
			g.drawLine((int) lon1, (int) lat1, (int) lon2, (int) lat2);
		}
		
		if(directions == true){
			paintPath(g, graph.table.get(end));
		}
		
		if(span ==true){
			paintMWST(g);
		}
		
		
		
		
		
		
	}
	
	public void paintPath(Graphics g, Vertex end){
		g.setColor(highlight);
		if(end!= null && end.path != null){
			double lat1 = this.getHeight()-((this.getHeight()*(end.latti-graph.minLat) )/rangey);
			double lon1 = (this.getWidth()*(end.longt-graph.minLong) )/rangex;
			double lat2 = this.getHeight()-((this.getHeight()*(end.path.latti-graph.minLat) )/rangey);
			double lon2 = (this.getWidth()*(end.path.longt-graph.minLong) )/rangex;
			g.drawLine((int)lon1, (int)lat1, (int)lon2, (int)lat2);
			
			paintPath(g, end.path);
		}
		
	}
	
	public void paintMWST(Graphics g){
		for(Vertex element: graph.spanningTree.table.values()){
			
			
			for(Node paint: element.roads){
				
				g.setColor(highlight);
				double lat1 = this.getHeight()-((this.getHeight()*(paint.parent.latti-graph.minLat) )/rangey);
				double lon1 = (this.getWidth()*(paint.parent.longt-graph.minLong) )/rangex;
				double lat2 = this.getHeight()-((this.getHeight()*(paint.pointer.latti-graph.minLat) )/rangey);
				double lon2 = (this.getWidth()*(paint.pointer.longt-graph.minLong) )/rangex;
				g.drawLine((int) lon1, (int) lat1, (int) lon2, (int) lat2);
				
			}
		}
	}
}//end class

