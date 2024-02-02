package graph;

public interface ProcessedVertexes {
	// processedVertexes doit pouvoir ajouter des éléments
	public void adding(Vertex vertex) ;
	
	// On doit pouvoir savoir si un élément est dans processedVertexes
	public boolean IsInProcessedVertexes(Vertex vertex);
	
}
