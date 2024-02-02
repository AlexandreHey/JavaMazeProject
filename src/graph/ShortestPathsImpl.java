package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathsImpl extends HashMap<Object, Object> implements ShortestPaths{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Vertex, Vertex> shortestPaths ;
	
	public ShortestPathsImpl() {
		Map<Vertex,Vertex> map = new HashMap<>();
		this.shortestPaths = map;
	}

	//On associe à chaque Vertex (key) son prédécesseur (value)
	@Override
	public void previous(Vertex vertex, Vertex previousVertex) {
		// TODO Auto-generated method stub
		shortestPaths.put(vertex, previousVertex);
	}

	@Override
	public List<Vertex> getShortestPath(Vertex endVertex) {
		// TODO Auto-generated method stub
		List<Vertex> ShortestPathList = new ArrayList<Vertex>();
		//Cette liste est inversée
		Vertex tempVertex = endVertex ;
		while(shortestPaths.get(tempVertex)!=null) {
			ShortestPathList.add(shortestPaths.get(tempVertex));
			tempVertex = shortestPaths.get(tempVertex);
		}
		Collections.reverse(ShortestPathList);
		//La liste contient cependant le point de départ qu'il faut donc enlever
		List<Vertex> finalShortestPathList = new ArrayList<Vertex>();
		int size = ShortestPathList.size();
		for(int i=1 ; i<size ; i++) {
			finalShortestPathList.add(ShortestPathList.get(i));
		}
		return finalShortestPathList;
	}

}
