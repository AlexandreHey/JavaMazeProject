package graph;

public interface MinDistance {
	//On doit pouvoir attribuer une distance minimale à un sommet 
	public int getDistance(Vertex vertex);
	
	//On doit pouvoir modifier cette valeur 
	public void setDistance(Vertex vertex, int newDistance);
	
}
