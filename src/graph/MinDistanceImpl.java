package graph;
import java.util.Hashtable;

public class MinDistanceImpl extends Hashtable<Vertex , Integer> implements MinDistance{
	
	private static final long serialVersionUID = 1L;

	public MinDistanceImpl() {
		super();
	}
	
	@Override
	public final void setDistance(Vertex vertex, int newDistance) {
		// TODO Auto-generated method stub
		put(vertex, newDistance);
	}
	
	@Override
	public final int getDistance(Vertex vertex) {
		// TODO Auto-generated method stub
		return super.get(vertex);
	}
}
