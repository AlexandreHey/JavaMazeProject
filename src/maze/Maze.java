package maze;
import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import graph.Graph;
import graph.Vertex;

/**
 * Classe Maze. 
 * La classe implémente l'interface Graph.
 * Un maze est un tableau 2D de MazeBox. Dans cette classe on écrit les méthodes qui permettent de créer un labyrinthe.
 * On peut créer ce labyrinthe à partir d'un fichier texte et sauvegarder le labyrinthe dans un autre fichier texte.
 * On doit aussi pouvoir trouver les cases de départ et d'arrivée d'un labyrinthe donné.
 * 
 * @author Alexandre
 *
 */
public class Maze implements Graph{
	private MazeBox[][] maze;
	private int height ; 
	private int width ;
	private Vertex startVertex ;
	private Vertex endVertex ;
	
	//Un labyrinthe/maze est un tableau 2D de MazeBox
	public Maze(MazeBox[][] maze) {
		this.maze = maze;
	}
	
	public void setHeight(int height) {
		this.height=height;
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	
	public Vertex getStartVertex() {
		return startVertex;
	}

	public Vertex getEndVertex() {
		return endVertex;
	} 
	
	public MazeBox[][] getMaze(){return maze;}


	@Override
	/**
	 * Méthode de l'interface Graph.
	 * Permet de retourner la liste contenant les tailles du labyrinthe
	 * @return liste des dimensions, longueur et largeur du labyrinthe.
	 */
	public int[] size() {
		// TODO Auto-generated method stub
		if(maze!=null) {
			//Revoir ici, peut-être inverser height et width
			int height = maze.length;
			int width = maze[0].length;
			return new int[] {width,height};
		}
		else {return null;}
	}
	
	@Override
	/**
	 * Méthode de l'interface Graph.
	 * Permet de renvoyer la liste des successeurs/voisins d'un sommet vertex
	 * @param vertex. Sommet dont on cherche les successeurs/voisins
	 * @return liste des sommets voisins de vertex 
	 */
	public ArrayList<Vertex> succVertex(Vertex vertex) {
		// TODO Auto-generated method stub
		ArrayList<Vertex> succVertex = new ArrayList<Vertex>();
		MazeBox box = (MazeBox) vertex;
		int i = box.getX();
		int j = box.getY();
		//Voisins de gauche et droite
		try {
			MazeBox leftNeighbor = (MazeBox) maze[i][j-1];
			if(leftNeighbor.getCrossable()) {succVertex.add(leftNeighbor);}
		}
		catch(Exception e) {}
		
		try {
			MazeBox rightNeighbor = (MazeBox) maze[i][j+1];
			if(rightNeighbor.getCrossable()) {succVertex.add(rightNeighbor);}
		}
		catch(Exception e) {}
		//Si je suis dans une ligne paire 
		if(i%2==0) {
			try {
				MazeBox upperleftNeighbor = (MazeBox) maze[i-1][j];
				if(upperleftNeighbor.getCrossable()) {succVertex.add(upperleftNeighbor);}
			}
			catch(Exception e) {}
			
			try {
				MazeBox upperrightNeighbor = (MazeBox)maze[i-1][j+1];
				if(upperrightNeighbor.getCrossable()) {succVertex.add(upperrightNeighbor);}
			}
			catch(Exception e) {}
			
			try {
				MazeBox lowerleftNeighbor = (MazeBox)maze[i+1][j];
				if(lowerleftNeighbor.getCrossable()) {succVertex.add(lowerleftNeighbor);}
			}
			catch(Exception e) {}
			
			try {
				MazeBox lowerrightNeighbor = (MazeBox)maze[i+1][j+1];
				if(lowerrightNeighbor.getCrossable()) {succVertex.add(lowerrightNeighbor);}
			}
			catch(Exception e) {}
		}
		//Si je suis dans une ligne impaire 	
		else {
			try {
				MazeBox upperleftNeighbor = (MazeBox) maze[i-1][j-1];
				if(upperleftNeighbor.getCrossable()) {succVertex.add(upperleftNeighbor);}
			}
			catch(Exception e) {}	
		
			try {
				MazeBox upperrightNeighbor = (MazeBox) maze[i-1][j];
				if(upperrightNeighbor.getCrossable()) {succVertex.add(upperrightNeighbor);}
			}
			catch(Exception e) {}
			
			try {
				MazeBox lowerleftNeighbor = (MazeBox)maze[i+1][j-1];
				if(lowerleftNeighbor.getCrossable()) {succVertex.add(lowerleftNeighbor);}
			}
			catch(Exception e) {}
			
			try {
				MazeBox lowerrightNeighbor = (MazeBox)maze[i+1][j];
				if(lowerrightNeighbor.getCrossable()) {succVertex.add(lowerrightNeighbor);}
			}
			catch(Exception e) {}
		}
		return succVertex;
	}
	
	
	@Override
	/**
	 * Méthode de l'interface Graph.
	 * Permet de renvoyer la liste de tous les sommets présent dans le labyrinthe.
	 * @return liste de tous les sommets du graph
	 */
	public ArrayList<Vertex> AllVertex() {
		// TODO Auto-generated method stub
		ArrayList<Vertex> AllVertex = new ArrayList<Vertex>();
		for(int i=0 ; i<height ; i++) {
			for(int j=0 ; j<width ; j++) {
				MazeBox vertex = maze[i][j];
				AllVertex.add(vertex);
			}
		}
		return AllVertex;
	}

	@Override
	/**
	 * Méthode qui permet de donner la distance entre deux vertex.
	 * Comme cette méthode ne sera utilisé que pour des vertex voisins, la distance entre ces deux est 1.
	 * @param vertex1
	 * @param vertex2, toujours utilisé en tant que voisin de vertex1
	 * @return distance entre les deux sommets 
	 */
	public int distance(Vertex vertex1, Vertex vertex2) {
		// TODO Auto-generated method stub
		if(vertex1==vertex2) {
			return 0;
		}
		//Si vertex2 est un successeur de vertex1 alors l'arc est de poids 1
		else if(succVertex(vertex1).contains(vertex2)){
			return 1;
		}
		//Sinon, la distance est mise à l'infini
		else {
		return Integer.MAX_VALUE;
		}
	}
	
	
	//Méthode permettant de récupérer les dimensions du labyrinthe à partir d'un fichier texte. Cette méthode sera utile pour initialiser la labyrinthe au moment de sa création future.
	/**
	 * Méthode permettant de récupérer les dimensions du labyrinthe à partir d'un fichier texte.
	 * Cette méthode sera utile pour générer un labyrinthe, initialement nul, de bonnes dimensions.
	 * @param fileName, fichier à partir du quel le labyrinthe est crée.
	 * @return liste des dimensions, longueur et largeur, du labyrinthe crée à partir de ce fichier.
	 */
	public ArrayList<Integer> dimensionList(String fileName){
		ArrayList<Integer> dimensionList = new ArrayList<Integer>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = bufferedReader.readLine();
			if(line == null) {
				bufferedReader.close();
				throw new MazeReadingException(fileName,1,"Fichier vide");
			}
			int width = line.length();
			int height = 1;
	
			while((line= bufferedReader.readLine())!=null) {
				height +=1;
			}
			dimensionList.add(height);
			dimensionList.add(width);
			this.width = width ; 
			this.height = height ;
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found : "+ e.getMessage());
		}
		catch(IOException e) {
			System.out.println("Error reading file : "+ e.getMessage());
		} 
		catch (MazeReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dimensionList ;
	}
	
	
	//Implémentation de la méthode permettant de lire un fichier et d'afficher dans la console les lignes de ce fichier 
	/**
	 * Méthode permettant de créer un Maze à partir d'un fichier texte. 
	 * On utilise la méthode précédente pour initialement créer un labyrinthe de la bonne dimension.
	 * @param fileName, fichier à partir du quel on crée le labyrinthe 
	 */
	public final void initFromTextFile(String fileName){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line ;
			//Initialisation du labyrinthe aux dimensions récupérées dans le fichier texte 
			int height = dimensionList(fileName).get(0);
			int width = dimensionList(fileName).get(1);
			MazeBox[][] maze = new MazeBox[height][width];
			
			//Line1Length représente le nombre de boxe dans la ligne 1. On va chercher à voir si ce nombre est constant 
			//nbLine est le numéro de la ligne sur laquelle on se trouve 
			
			int nbLine = -1 ;
			//On est obligé de mettre "line=buferedReader.readLine() dans la condition du while et pas avant car sinon on ne change jamais la valeur de line (qui restera indéfiniment la valeur de la première ligne du fichier).
			while((line= bufferedReader.readLine())!=null) {
				nbLine += 1 ;
				int LineLength = line.length();
				//On regarde si chaque ligne à le même nombre de boxe. Dans le cas contraire, on lève une exception de type MazeReadingException
				if(LineLength!=width) {
					bufferedReader.close();
					throw new MazeReadingException("data/labyrinthe.maze",nbLine,"Erreur : la ligne " + nbLine + " ne contient pas le bon nombre de cases");
				}
				//Création du labyrinthe
				else {
					//Création des boites grâce au fichier texte 
					for(int i=0;i<LineLength;i++) {
						char CharAt_i = line.charAt(i);
						switch(CharAt_i) {
						case('E'):
							maze[nbLine][i]=new EmptyBox(nbLine,i);{
							break;}
						case('W'):{
							maze[nbLine][i]=new WallBox(nbLine,i);
							break;}
						case('D'):{
							maze[nbLine][i]=new DepartureBox(nbLine,i);
							break;}
						case('A'):{
							maze[nbLine][i]=new ArrivalBox(nbLine,i);
							break;}
						}
					}
				}
			}
			bufferedReader.close();
			//On met à jour la valeur de maze du constructeur
			this.maze=maze;
			}
		//Deux exceptions peuvent se lever : 1) Fichier non trouvé. 2) Erreur dans la lecture du fichier 
		catch(FileNotFoundException e) {
			System.out.println("File not found : "+ e.getMessage());
		}
		catch(IOException e) {
			System.out.println("Error reading file : "+ e.getMessage());
		} 
		catch (MazeReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant d'afficher le labyrinthe dans la console.
	 * Les points par lesquels l'algorithme de plus court chemin est passé sont affichés par des points "." dans la console.
	 * @param list, liste de sommets correspondant aux sommets du plus court chemin entre le départ et l'arrivée.
	 */
	public void printmaze(List<Vertex> list) {
		for(int i=0;i<height;i++) {
			String line = "";
			for(int j=0;j<width;j++) {
				MazeBox box = maze[i][j];
				if(list.contains(box)) {
					line+=".";
				}
				else {
					char boxLabel = box.getLabel();
					switch(boxLabel) {
					case('A'):{
						line+="A";
						break;}
					case('D'):{
						line+="D";
						break;}
					case('E'):{
						line+="E";
						break;}
					case('W'):{
						line+="W";
						break;}
					}
				}
			}
		System.out.println(line);
		}
	}
	
	/**
	 *Méthode permettant de sauvegarder un labyrinthe dans un fichier texte. 
	 * @param fileName, fichier dans lequel on veut sauvegarder le labyrinthe.
	 */
	public final void saveToTextFile(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName);
			for(int i=0 ; i<height ; i++) {
				String line ="" ;
				for(int j=0 ; j<width ; j++) {
					MazeBox box = maze[i][j];
					char boxLabel = box.getLabel();
					switch(boxLabel) {
					case('A'):{
						line+="A";
						break;}
					case('D'):{
						line+="D";
						break;}
					case('E'):{
						line+="E";
						break;}
					case('W'):{
						line+="W";
						break;}
					}
				}
				pw.println(line);
			}
			pw.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de trouver les points de départ et d'arrivée du labyrinthe
	 */
	public void StartEndVertex(){
		int i_start ; 
		int j_start ; 
		int i_end ; 
		int j_end ;
		
		for(int i=0 ; i<height ; i++) {
			for(int j=0 ; j<width ; j++) {
				if(maze[i][j].getLabel()=='D') {
					i_start = i ; 
					j_start = j ; 
					this.startVertex = maze[i_start][j_start];
				}
			}
		}
		for(int i=0 ; i<height ; i++) {
			for(int j=0 ; j<width ; j++) {
				if(maze[i][j].getLabel()=='A') {
					i_end = i ; 
					j_end = j ;
					this.endVertex = maze[i_end][j_end];
				}
			}
		}
	}

}

