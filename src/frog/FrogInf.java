package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.EnvInf;
import gameCommons.Game;

public class FrogInf extends Frog {
	
	private EnvInf environment;
	private int score;
	
	public FrogInf(Game game) {
		super(game);
		this.score = 0;
		//this.environment = environment;
	}
	
	public int getScore() {
		return this.score;
	}
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key) {
		if( key == Direction.up ) {
			//this.position = new Case( this.position.absc, this.position.ord+1 );
			this.score++;
			game.environmentUp();
		} else if( key == Direction.down ) {
			if( this.score > 0 ) {
				//this.position = new Case( this.position.absc, this.position.ord-1 );
				this.score--;
				game.environmentDown();
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
