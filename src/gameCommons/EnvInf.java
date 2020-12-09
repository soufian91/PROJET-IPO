package gameCommons;

import environment.Car;
import environment.Environment;
import environment.Lane;
import frog.FrogInf;

import java.util.Random;

import java.util.ArrayList;

public class EnvInf extends Environment {
	
	private static final Random RANDOM = new Random();
	private ArrayList<Lane>oldLane;

	public EnvInf(Game game, FrogInf frog) {
		super(game, frog);
		this.oldLane = new ArrayList<Lane>(); 
	}
	
	public void moveUp() {
		for( Lane l : voies ) {
			l.orderDown();
			for( Car voiture : l.getCars() ) {
				voiture.orderDown();
			}
		}
		oldLane.add(voies.get(0));
		voies.remove(0);
		double density = RANDOM.nextDouble() * (0.6D - 0.1D);
		voies.add(new Lane(this.getGame(), voies.size(), density));
	}
	
	public void upDateOldLane() {
		for( Lane l : oldLane ) {
			l.changeOrd();
			for( Car voiture : l.getCars() ) {
				voiture.changeOrd();
			}
		}
	}
	
	public void moveDown() {
		ArrayList<Lane> res  = new ArrayList<Lane>();
		upDateOldLane();
		//System.out.println( oldLane.get(oldLane.size()-1).getOrd() );
		res.add(oldLane.get(oldLane.size()-1));
		oldLane.remove(oldLane.get(oldLane.size()-1));
		for( Lane l : voies ) {
			l.orderUp();
			for( Car voiture : l.getCars() ) {
				voiture.orderUp();
			}
			if( l.getOrd() < 20 ) {
				res.add(l);
			}
		}
		this.voies = res;
	}
	
}
