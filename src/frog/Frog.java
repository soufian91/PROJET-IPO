package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {
	
	protected Game game;
	protected Case position;
	protected Direction direction;
	
	public Frog(Game game) {
		this.game = game;
		this.position = new Case(game.width/2, 1);
		this.direction = Direction.up;
	}
	

	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition() {
		return this.position;
	}
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key) {
		if( key == Direction.up ) {
			this.position = new Case( this.position.absc, this.position.ord+1 );
		} else if( key == Direction.down ) {
			if( this.position.ord-1 >= 0 ) {
				this.position = new Case( this.position.absc, this.position.ord-1 );
			}
		} else if ( key == Direction.left ) {
			if(this.position.absc-1 >= 0) {
				this.position = new Case( this.position.absc-1, this.position.ord );
			}
		} else if( key == Direction.right ) {
			if( this.position.absc+1 < game.width ) {
				this.position = new Case( this.position.absc+1, this.position.ord );
			}
		}
		this.direction = key;
	}
	

}
