package environment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import frog.FrogInf;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IEnvironment;
import graphicalElements.Element;

public class Environment implements IEnvironment {
	
	//Les attributs
	protected FrogInf frog;
	protected Game game;
	protected int nbVoies;
	protected ArrayList<Lane> voies = new ArrayList<Lane>();
	protected ArrayList<Lane> water = new ArrayList<Lane>();
	protected static final Random RANDOM = new Random();
	
	//Constructeur
	public Environment(Game game, FrogInf frog) {
		this.game = game;
		this.frog = frog;
		this.nbVoies = this.game.height;
		this.voies = new ArrayList<Lane>(this.nbVoies);
		voies.add(new Lane(game, 0, 0.0D));
		voies.add(new Lane(game, 1, 0.0D));
		for(int i=2; i<game.height-1; i++) {
			double density = 0.3D;   //RANDOM.nextDouble() * (0.2D-0.1D);
			Lane a = new Lane(game, i, density);
			voies.add(a);
			if( a.getIsWater() ) { water.add(a); }
		}
		voies.add(new Lane(game, game.height-1, 0.0D));
	}
		
	//TODO
	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public int getNbVoies() {
		return this.nbVoies;
	}
	
	public ArrayList<Lane> getLane() {
		return this.voies;
	}
	
	public boolean isSafe(Case c) {
		return this.voies.get(c.ord).isSafe(c);
	}
	
	public Game getGame() {
		return this.game;
	}

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c) {
		return c.ord == this.nbVoies-1;
	}

	
	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update() {
		moveIfWater();
		for( Lane ligne : this.voies ) {
			ligne.update();
		}
		addToGraphics();
	}

	
	public void moveIfWater() {
		int ordF = frog.getPosition().ord;
		for( Lane lane : water ) {
			if( ordF == lane.getOrd() ) {
				frog.move(Direction.left);
			}
		}
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for(Lane lane : water) {
			for(int i=0; i<this.game.width; i++) {
				game.getGraphic().add(new Element(i, lane.getOrd(), Color.CYAN));
			}
		}
	}

	
}
