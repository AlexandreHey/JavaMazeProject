package graph;

import java.util.HashSet;

public class ProcessedVertexesImpl extends HashSet<Object> implements ProcessedVertexes{

	//private HashSet<Vertex> processedVertexes ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcessedVertexesImpl() {
		//Utilisation du constructeur de la classe mère 
		super();
	}
	
	//Eviter les conflits de nom avec la méthode add de HashSet
	@Override
	public void adding(Vertex vertex) {
		// TODO Auto-generated method stub
		add(vertex);
	}

	@Override
	public boolean IsInProcessedVertexes(Vertex vertex) {
		// TODO Auto-generated method stub
		return contains(vertex);
	}

}
