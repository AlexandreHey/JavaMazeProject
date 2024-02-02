package graph;

import java.util.ArrayList;


public interface Graph {
	//Permet de connaître tous les sommets du graph 
	public ArrayList<Vertex> AllVertex();
	
	//Permet de connaître la taille du graphe. Ici graph.size() donne deux valeurs, la longueur et la largeur
	public int[] size();
	
	//On doit pouvoir renvoyer la liste des successeurs d'un sommet 
	public ArrayList<Vertex> succVertex(Vertex vertex);
	
	public int distance(Vertex vertex1, Vertex vertex2);

	

}
