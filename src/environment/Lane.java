package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Lane {

	// Les attributs
	private Game game;
	private int ord;
	private int speed; // entier donc il s'agit d'un 1, 2 ou 3 etc...
	private ArrayList<Car> cars = new ArrayList<Car>();
	private boolean leftToRight;
	private double density;
	private int cpt_timer = 0;
	private static final Random RANDOM = new Random();
	private boolean water;


	// TODO : Constructeur(s)
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = RANDOM.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = RANDOM.nextBoolean();
		this.density = density;
		if( game.randomGen.nextDouble() < 0.7D ) {
			this.water = false;
		} else {
			this.water = true;
			removeIfWater();
		}
	}

	// TODO : ajout de methodes
	public ArrayList<Car> getCars() {
		return this.cars;
	}
	
	public boolean isSafe(Case c) {
		for (Car voiture : this.cars) {
			if (voiture.memeCase(c)) {
				return false;
			}
		}
		return true;
	}
	
	public void orderUp() {
		this.ord = ord +1;
	}
	
	public void orderDown() {
		this.ord = ord -1;
	}

	public int getOrd() {
		return this.ord;
	}
	
	public void changeOrd() {
		this.ord = 0;
	}
	
	public boolean getIsWater() {
		return this.water;
	}
	
	public void removeIfWater() {
		if(getIsWater()) {
			cars.removeAll(cars);
		}
	}
	
	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public void update() {

		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		cpt_timer++;
		if( water ) {
			return;
		}
		if( this.cpt_timer >= this.speed ) {
			for(Car voiture : cars) {
				voiture.moveCar();
			}
			mayAddCar();
			this.cpt_timer = 0;
		}
		for (Car voiture : cars) {
			voiture.addToGraphics();
		}

	}

}
