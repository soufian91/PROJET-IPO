package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	
	private final int max = 3;
	private final int min = 1;
	
	private Game game;
	private Case leftPosition;    //coordonnées de ga
	private boolean leftToRight;     // sens
	private int length;          // taille
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;
	private static final Random RANDOM = new Random();

	//TODO Constructeur(s)
	public Car( Game game, Case position, boolean leftToRight ) {
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = RANDOM.nextInt((max - min)+1);
		if (leftToRight) {
            this.leftPosition = new Case(position.absc - this.length, position.ord);
        } else {
            this.leftPosition = new Case(position.absc, position.ord);
        }
	}
	
	//TODO : ajout de methodes
	public int getLength() {
		return this.length;
	}
	
	public boolean leftToRight() {
		return this.leftToRight;
	}
	
	public Case position() {
		return this.leftPosition;
	}
	
	public boolean memeCase(Case c) {
		if( leftPosition.ord != c.ord ) {
			return false;
		} else {
			if( leftPosition.absc <= c.absc && leftPosition.absc + length > c.absc ) {
				return true;
			}
		}
		return false;
	}
	
	public void changeOrd() {
		this.leftPosition = new Case(this.leftPosition.absc, 0);
	}
	
	public void orderUp() {
        this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + 1);
	}
	
	public void orderDown() {
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	
	public void moveCar() {
		if( this.leftToRight == true ) {
			this.leftPosition = new Case( this.leftPosition.absc+1, this.leftPosition.ord );
			this.addToGraphics();
		} else {
			this.leftPosition = new Case( this.leftPosition.absc-1, this.leftPosition.ord );
			this.addToGraphics();
		}
	}
   
}
