package gui.model;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.*;

import graph.*;
import maze.ArrivalBox;
import maze.*;
import maze.Maze;
import maze.MazeBox;
import maze.MazeReadingException;

/**
 * Classe MazeAppModel. 
 * C'est dans cette classe qu'on range toutes les méthodes . Lorsqu'on va faire une modification du labyrinthe on utilisera la méthode 
 * stateChanged() qui permettra à la classe MazeApp de se mettre à jour car c'est elle qui observe la classe MazeAppModel.
 * @author Alexandre
 *
 */
public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	private boolean modified = false ;
	private boolean showShortestPath = false ; 
	private Box selectedBox = null ;
	private int[] selectedBoxCoordinate = new int[] {0,0};
	private List<List<Box>> boxMatrix = new ArrayList<List<Box>>();
	private List<Box> shortestPath = new ArrayList<Box>();
	private Box currentBox = null ;
	private int[] dimensionMaze = new int[] {0,0} ;
	private boolean isClicked = false ;
	private char editBoxLabel ;
	private char missingDepartureArrivalBox ;
	
	/**
	 * Méthode qui permet à la classe MazeApp de se mettre à jour car c'est elle qui observe la classe MazeAppModel.
	 */
	private void stateChanged() {
		ChangeEvent evt = new ChangeEvent(this);
		for(ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	
	public void addObserver(ChangeListener listener) {
		listeners.add(listener);
	}

	
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified=modified;
	}
	public void setShowShortestPath(boolean showShortestPath) {
		this.showShortestPath=showShortestPath;
	}

	public Box getSelectedBox() {return selectedBox;}


	public Box getCurrentBox() {return currentBox;}
	public void setCurrentBox(Box currentBox) {
		if(this.currentBox!=currentBox) {
			this.currentBox=currentBox;
			modified=true;
			stateChanged();
		}
	}
	public List<List<Box>> getBoxMatrix() {return boxMatrix;}
	public void setBoxMatrix(List<List<Box>> boxMatrix) {
		if(this.boxMatrix!=boxMatrix) {
			this.boxMatrix=boxMatrix;
			modified=true;
			stateChanged();
		}
	}
	public int[] getDimensionMaze() {return dimensionMaze;}
	public List<Box> getShortestPath(){return shortestPath;}
	
	public boolean getIsClicked(){return isClicked;}
	public void setIsClicked(boolean isClicked) {
		this.isClicked=isClicked;
	}
	
	public char getEditBoxLabel() {return editBoxLabel;}
	public void setEditBoxLabel(char editBoxLabel) {
		this.editBoxLabel=editBoxLabel;
	}
	
	public char getMissingDepartureArrivalBox() {return missingDepartureArrivalBox;}
		
	/**
	 * Dans cette méthode on appelle ma méthode initFromTextFile de la classe Maze pour générer un labyrinthe à partir d'un fichier texte.
	 * Une fois fait on appelle la méthode initFromMaze qui, à partir d'un labyrinthe déjà construit, affiche ce labyrinthe à l'écran.
	 * Cette méthode ne sera appelé qu'au début/lancement du programme car c'est le seul moment où un labyrinthe sera généré à partir d'un 
	 * fichier texte.
	 * @param fileName, fichier à partir duquel on crée le labyrinthe
	 * @throws MazeReadingException
	 */
	private void initFromFile(String fileName) throws MazeReadingException {
		int height = dimensionList(fileName).get(0);
		int width = dimensionList(fileName).get(1);
		this.dimensionMaze[0]=height;
		this.dimensionMaze[1]=width;
		MazeBox[][] maze_ = new MazeBox[height][width];
		Maze maze = new Maze(maze_);
		maze.initFromTextFile(fileName);
		initFromMaze(maze);
	}
	
	/**
	 * Permet de créer un labyrinthe vide de taille donnée.
	 * On appelle là aussi la méthode initFromMaze qui permet d'afficher le labyrinthe à l'écran.
	 * @param height, longueur du labyrinthe
	 * @param width, largeur du labyrinthe
	 */
	public void resetMaze(int height, int width) {
		this.dimensionMaze[0]=height;
		this.dimensionMaze[1]=width;
		MazeBox[][] maze_ = new MazeBox[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				maze_[i][j]=new EmptyBox(i,j);
			}
		}
		Maze maze = new Maze(maze_);
		initFromMaze(maze);
	}

	/**
	 * Permet, à partir d'un labyrinthe donné, d'afficher ce labyrinthe à l'écran. 
	 * Mais chaque case du labyrinthe est une MazeBox, on va donc créer un tableau 2D de Box et pour chaque MazeBox du labyrinthe, on 
	 * associe une Box particulière. C'est finalement à partir de cette matrice 2D de Box qu'on va pouvoir afficher le labyrinthe.
	 * @param maze, tableau 2D de MazeBox
	 */
	private void initFromMaze(Maze maze) {
		int y = maze.size()[1];
		int x = maze.size()[0];

		MazeBox[][] mazeBoxMatrix = maze.getMaze();
		
		int size;
		double y_percent = (y*100)/35;
		double x_percent = (x*100)/48;
		
		if(y_percent>x_percent) {
			size = (int)((2*357)/(3*y));
		}
		else {
			size = (int)(610/(2*0.86*x));
		}
		List<List<Box>> matrix = new ArrayList<List<Box>>();
		for(int i=0;i<y;i++) {
			List<Box> submatrix = new ArrayList<Box>();
			if(i%2==1) {
				for(int j=0;j<x;j++) {
					MazeBox mazebox = (MazeBox) mazeBoxMatrix[i][j];
					char type = mazebox.getLabel();
					Box box = new Box((int)((415-2*0.86*size*(x/2))+2*size*j*0.86),(int)((300-1.5*size*(y/2))+size*1.5*i),size,type);
					submatrix.add(box);
				}		
			}			
			else {
				for(int j=0;j<x;j++) {
					MazeBox mazebox = mazeBoxMatrix[i][j];
					char type = mazebox.getLabel();
					Box box = new Box((int)((415-2*0.86*size*(x/2))+size*0.86+2*size*j*0.86),(int)((300-1.5*size*(y/2))+size*1.5*i),size,type);
					submatrix.add(box);
				}
			}
			matrix.add(submatrix);
		}
		this.boxMatrix=matrix;
		//On appelle la méthode stateChanged() qui va appeler la méthode PaintMaze qui va dessiner le labyrinthe 
		stateChanged();
	}
	
	/**
	 * Méthode qui permet, grâce à la matrice boxMatrix en attribut de la classe (qui est un tableau 2D de Box) d'afficher à l'écran toutes
	 * les box. On appelle donc la méthode Paint de la classe Box pour haque Box de boxMarix.
	 * @param g
	 * @throws MazeReadingException
	 */
	public void PaintMaze(Graphics g) throws MazeReadingException {
		//Si c'est la première fois qu'on affiche un labyrinthe (au lancement du programme) alors on affiche le labyrinthe issue du fichier texte
		if(!isModified()) {
			initFromFile("data/labyrinthe.maze");
		}
		else{
			for(int i=0;i<boxMatrix.size();i++) {
				for(Box box : boxMatrix.get(i)) {
					box.paint(g,false);
					}
				if(showShortestPath) {
					for(Box box : shortestPath) {
					box.paint(g, true);
					}
				}
				if(currentBox!=null) {
					currentBox.paint(g, false);
				}
			}
		}
		//L'attribut modified restera vrai tout le temps ensuite car le programme aura déjà été lancé.
		setModified(true);
		setShowShortestPath(false);
	}
	
	/**
	 * Méthode qui permet de mettre à jour la liste de Box shortestPath mise en attribut de la classe. 
	 * Pour cela, à partir du tableau 2D de Box boxMatrix (en attribut), on recrée un labyrinthe de type Maze car on pourra ainsi appliquer 
	 * l'algorithme de Dijkstra. L'algorithme de Dijkstra nous renvoit une liste de Vertex, il faut donc transformer ces vertex en MazeBox 
	 * par transtypage, puis finalement en Box.
	 * On lève dans les exceptions dans les cas où il n'y a pas de cases départ/arrivée.
	 */
	public void solve() {
		try {
			MazeBox[][] maze_ = new MazeBox[dimensionMaze[0]][dimensionMaze[1]];
			for(int i=0;i<dimensionMaze[0];i++) {
				for(int j=0;j<dimensionMaze[1];j++) {
					Box box = boxMatrix.get(i).get(j);
					char label = box.getType();
					if(label=='A') {
						maze_[i][j]=new ArrivalBox(i,j);
					}
					else if(label=='D') {
						maze_[i][j]=new DepartureBox(i,j);
					}
					else if(label=='W') {
						maze_[i][j]=new WallBox(i,j);
					}
					else if(label=='E') {
						maze_[i][j]=new EmptyBox(i,j);
					}
				}
			}
			Maze maze = new Maze(maze_);
			maze.setHeight(dimensionMaze[0]);
			maze.setWidth(dimensionMaze[1]);
			maze.StartEndVertex();
			Vertex startVertex = maze.getStartVertex();
			Vertex endVertex = maze.getEndVertex();
			List<Vertex> list = Dijkstra.dijkstra(maze,startVertex,endVertex);
			List<Box> tempshortestPath = new ArrayList<Box>();
			for(Vertex vertex : list) {
				MazeBox vertex_box = (MazeBox) vertex;
				char label = vertex_box.getLabel();
				int x = vertex_box.getX();
				int y = vertex_box.getY();
				Box box = new Box(boxMatrix.get(x).get(y).getX_center(),boxMatrix.get(x).get(y).getY_center(),boxMatrix.get(x).get(y).getRadius(),label);
				tempshortestPath.add(box);
			}
			this.shortestPath=tempshortestPath;
			setShowShortestPath(true);
			stateChanged();
			//Dans le cas où il n'y a pas de case départ ou arrivée on lève une exception
			if(startVertex == null) {
				this.missingDepartureArrivalBox='D';
				throw new MissingStartEndException(missingDepartureArrivalBox);
			}
			else if (endVertex==null){
				this.missingDepartureArrivalBox='A';
				throw new MissingStartEndException(missingDepartureArrivalBox);
			}
		}
		catch(MissingStartEndException e) {}
		catch(NullPointerException e) {
			JFrame WarningFrame = new JFrame("DepartureBox blocked !");
			WarningFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Alexandre\\eclipse-workspace\\tp04\\data\\warning.jpg"));
			JTextArea textArea = new JTextArea();
			textArea.setText("Warning : you have locked the departure !");
			WarningFrame.setBounds(new Rectangle(400, 160, 500, 60));
			WarningFrame.getContentPane().add(textArea, BorderLayout.CENTER);
			WarningFrame.setVisible(true);
		}
	}
	
	/**
	 * Méthode copiée de la classe Maze. Permet de donner les dimensions d'un fichier texte.
	 * @param fileName
	 * @return liste des dimensions d'un fichier texte.
	 */
	private ArrayList<Integer> dimensionList(String fileName){
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
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found : "+ e.getMessage());
		}
		catch(IOException e) {
			System.out.println("Error reading file : "+ e.getMessage());
		} catch (MazeReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dimensionList ;
	}
	
	/**
	 * Méthode qui sera appelée après la méthode setSelectedBox et qui permet de changer la nature de la box sur laquelle on a cliqué.
	 * @param label, nom de la nouvelle box 
	 */
	public void editbox(char label) {
		// TODO Auto-generated method stub
		//Si on veut mettre une case départ ou arrivée, il faut supprimer la case départ ou arrivée déjà présente
		if(editBoxLabel=='A' || editBoxLabel=='D') {
			for(int i=0;i<boxMatrix.size();i++) {
				for(Box box : boxMatrix.get(i)) {
					if (box.getType()==editBoxLabel) {
						box.setType('E');
					}
				}
			}
		}
		//Si on a cliqué sur une box alors selectedBox est non nul
		if(this.selectedBox != null) {
			this.currentBox=new Box(selectedBox.getX_center(),selectedBox.getY_center(),selectedBox.getRadius(),label);
			//On met à jour la matrice représentant le labyrinthe pour toujours pouvoir résoudre le labyrinthe avec les nouvelles modifications
			this.boxMatrix.get(selectedBoxCoordinate[0]).set(selectedBoxCoordinate[1], currentBox);
			//On remet currentBox et selectedBox à null sinon quand on appuiera sur "Reset" on aura la dernière case séléctionée qui sera affichée sur l'écran
			this.currentBox=null;
			this.selectedBox=null;
			stateChanged();
		}
	}
	
	/**
	 * Méthode qui sera appelée quand on va cliquer sur une box avec la sourie. 
	 * @param x, coordonnée en x du point où on clique avec la sourie  
	 * @param y, coordonnée en y du point où on clique avec la sourie
	 * Pour toutes les box du labyrinthe affiché à l'écran on va créer l'hexagone associé et on va regarder si cet hexagone contient les 
	 * coordonées x et y. Si oui, alors on peut relever la box sur laquelle on a cliqué avec la souris et on note cette box selectedBox
	 */
	public void setSelectedBox(int x, int y) {
		// TODO Auto-generated method stub
		for(int i=0;i<boxMatrix.size();i++) {
			for(int j=0;j<boxMatrix.get(i).size();j++) {
				Box box = boxMatrix.get(i).get(j);
				int[][] l = box.coordinates();
				Polygon hex = new Polygon(l[0], l[1], 6);
				if(hex.contains(x, y)) {
					this.selectedBox=box;
					//On enregistre les coordonnées de selectedBox afin de les utiliser dans la méthode editbox
					this.selectedBoxCoordinate[0]=i;
					this.selectedBoxCoordinate[1]=j;
				}
			}
		}
	}
	
	/**
	 * Méthode qui sera appelée après appui sur le bouton Save. 
	 * On choisit un emplacement et la méthode va créer un fichier "selectedFile" dans lequel on va pouvoir écrire chaque box du labyrinthe 
	 * que l'on veut sauvegarder.
	 * @throws IOException
	 */
	public void save() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {
            	for(int i=0;i<boxMatrix.size();i++) {
            		String line = "";
            		for(Box box : boxMatrix.get(i)) {
            			char type = box.getType();
            			if(type=='A') {
            				line+="A";
            			}
            			if(type=='D') {
            				line+="D";
            			}
            			if(type=='E') {
            				line+="E";
            			}
            			if(type=='W') {
            				line+="W";
            			}
            		}
            		bw.write(line+System.lineSeparator());
            		
            	}
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	/**
	 * Méthode qui sera lancée après appui sur le bouton openFile. On appelle donc la méthode initFromTextFile qui permet de créer un
	 * labyrinthe à partir du fichier séléctionné et on appelle la méthode initFromMaze qui, à partir de ce labyrinthe, affiche le 
	 * résultat à l'écran.
	 */
	public void openFile() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	    int result = fileChooser.showSaveDialog(null);
	    if (result == JFileChooser.APPROVE_OPTION) {
	    	File selectedFile = fileChooser.getSelectedFile();
	    	String fileName = selectedFile.getAbsolutePath();
	    	Maze maze = new Maze(null);
	    	maze.initFromTextFile(fileName);
	    	this.dimensionMaze[0]=maze.size()[1];
	    	this.dimensionMaze[1]=maze.size()[0];
	    	initFromMaze(maze);
	    }
	}
}
