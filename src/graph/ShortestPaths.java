package graph;

import java.util.List;

public interface ShortestPaths {
	//On doit pouvoir attribuer un prédecesseur à un sommet
	public void previous(Vertex vertex, Vertex previousVertex);
	
	public List<Vertex> getShortestPath(Vertex endVertex);
		
}
