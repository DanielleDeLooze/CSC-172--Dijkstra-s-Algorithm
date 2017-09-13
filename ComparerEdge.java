/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
import java.util.Comparator;

public class ComparerEdge implements Comparator<Edge> {
	public int compare(Edge x, Edge y){
		if(x.distance < y.distance){
			return -1;
		}
		else if( x.distance > y.distance){
			return 1;
		}
		else{
			return 0;
		}
	}

}
