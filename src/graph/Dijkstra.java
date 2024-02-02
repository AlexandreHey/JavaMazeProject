package graph;
import java.util.*;

public class Dijkstra {
	public static ShortestPaths Dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance mindistance, ShortestPaths shortestpaths) {
		
		processedVertexes.adding(startVertex);
		Vertex pivot = startVertex ; 
		mindistance.setDistance(startVertex, 0);
		
		//On met toutes les autres valeurs à l'infini
		ArrayList<Vertex> AllVertexes = graph.AllVertex();
		for(Vertex vertex : AllVertexes) {
			if(vertex != startVertex) {
				mindistance.setDistance(vertex, Integer.MAX_VALUE);
			}
		}
		
		//Tant que processedVertexes ne contient pas le sommet de fin 
		while (!processedVertexes.IsInProcessedVertexes(endVertex)){
			//Pour tout sommet successeur de pivot et non inclu dans processedVertexes
			ArrayList<Vertex> successors = graph.succVertex(pivot);
			for(int i=0; i<successors.size(); i++) {
				if(!processedVertexes.IsInProcessedVertexes(successors.get(i))) {
					//Alors si la distance min....
					//Si la valeur est à l'infini on la met forcément à jour 
					if (mindistance.getDistance(successors.get(i))==Integer.MAX_VALUE) {
						mindistance.setDistance(successors.get(i), mindistance.getDistance(pivot)+graph.distance(pivot, successors.get(i)));
						shortestpaths.previous(successors.get(i),pivot);
					}
					//Sinon on doit vérifier cette condition avant de mettre à jour 
					else if (mindistance.getDistance(pivot)+graph.distance(pivot, successors.get(i)) < mindistance.getDistance(successors.get(i))) {
						mindistance.setDistance(successors.get(i), mindistance.getDistance(pivot)+graph.distance(pivot, successors.get(i)));
						shortestpaths.previous(successors.get(i),pivot);
					}
				}
			}
			//On doit changer le pivot 
			int DistanceMin = Integer.MAX_VALUE;
			Vertex nextPivot = null ;
			for(Vertex vertex : AllVertexes) {
				if(!(processedVertexes.IsInProcessedVertexes(vertex))&&(mindistance.getDistance(vertex)<DistanceMin)){
					nextPivot = vertex ; 
					DistanceMin = mindistance.getDistance(vertex);
				}
			}
			pivot = nextPivot ;
			processedVertexes.adding(pivot);
			}
		//On doit enfin renvoyer la valeur du chemin le plus court
		return shortestpaths ;
		}
	
	
	public static List<Vertex> dijkstra(Graph graph,Vertex startVertex, Vertex endVertex) {
		ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
		MinDistance mindistance = new MinDistanceImpl();
		ShortestPaths shortestpaths = new ShortestPathsImpl();
		
		return Dijkstra(graph,startVertex,endVertex,processedVertexes,mindistance,shortestpaths).getShortestPath(endVertex) ;
	}

}
