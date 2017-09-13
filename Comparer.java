/*
 * Author: Danielle DeLooze
 * Project: Project 4
 * Date: 4/29/2017
 */
import java.util.Comparator;

public class Comparer implements Comparator<Vertex>{
	public int compare(Vertex x, Vertex y){
		if(x.distance < y.distance){
			return -1;
		}
		else{
			return 1;
		}
	}
}
