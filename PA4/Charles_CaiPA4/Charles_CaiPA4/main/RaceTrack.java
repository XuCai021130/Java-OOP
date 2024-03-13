/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This race track class contain fields, constructor, behaviors necessary for the class 
*/
package main;

/**
 * race track class
 * @author Lenovo
 *
 */
public class RaceTrack {

	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events. For more see the assignment PDF / documentation for TrackLoggerB.java
	 */
	private TrackLoggerC logger;
	public Car [] allCars;
	public PitStop pitstop;
	public int RaceCar;
	public int FormulaOne;
	public int SportsCar;
	public FinishLine finishline;
	
	/**
	 * 
	 * constructor
	 */
	public RaceTrack() {
		logger = new TrackLoggerC(); // DO NOT REMOVE THIS LINE
		allCars = new Car [10];
		FormulaOne = 0;
		SportsCar = 0;
		RaceCar = 0;
		finishline = new FinishLine();
		pitstop = new PitStop();
	}
	
	/**
	 * store all cars in a array that is a field of race track
	 * @param cars
	 */
	public void setCars(Car[] cars) {
		for (int i = 0; i < cars.length; i++) {
			allCars [i] = cars[i];
			
			if (allCars[i] != null) {
				if (allCars [i] instanceof RaceCar) { // check the type of car
					SportsCar++;
				}
				else if (allCars [i] instanceof FormulaOne){
					FormulaOne++;
				}
				else {
					RaceCar++;
				}
			}
			
		}
	}
	
	/**
	 * simulate one tick 
	 */
	public void tick() {
		for (int i = 0; i < allCars.length; i++) {
			
			if (allCars[i] != null) {
				double initialPosition = (allCars[i].locator % 100);
				if (allCars[i].enterPitStopTime <= 1 && allCars[i].enterPitStopTime >= 0) { // check if car in pitstop
					allCars[i].enterPitStopTime ++;
					allCars[i].tick++;
				}
				else if (allCars[i].enterPitStopTime == -1) {
					allCars[i].tick++;
					allCars[i].locator += allCars[i].speed;
				}
				else if (allCars[i].enterPitStopTime == 2){ // car just leave the pitstop
					pitstop.leavePitstop(allCars[i]);
					logger.logExitPit(allCars[i]);
				}
			
				checkCollision();
			
				if (allCars[i].damageStatus && (allCars[i].locator % 100) >= 75 && initialPosition < 75 && !allCars[i].damagedBefore) {
					pitstop.enterPitStop(allCars[i]);
					logger.logEnterPit(allCars[i]);
				}
			}
			if (allCars[i].locator >= 1000) {
				finishline.enterFinishLine(allCars[i]);
				logger.logFinish(allCars[i], finishline.howManyFinished());
			}
		}
		
	}
	
	/**
	 * check if two cars collide
	 */
	public void checkCollision() {
		for (int i = 0;i < allCars.length - 1; i++) {
			for (int j = 1; j < allCars.length; j++) {
				if (allCars[i] != null && allCars[j] != null) { // ensure not empty
					if (!allCars[i].toString().equals(allCars[j].toString())) { // ensure they are not same
						if (allCars[i].locator % 100 == allCars[j].locator % 100) { // if two cars in the same place
							if (allCars[i].damagedBefore == false) {  // if car damaged before, no need to do next actions
								allCars[i].damageStatus = true;
								allCars[i].speed = allCars[i].speed - allCars[i].strength * 5;
								logger.logDamaged(allCars[i]);
							}
							if (allCars[j].damagedBefore == false) {
								allCars[j].damageStatus = true;
								allCars[j].speed = allCars[j].speed - allCars[j].strength * 5;
								logger.logDamaged(allCars[j]);
							}
						}
					}
				}
			}	
		}	
	}
	
	/**
	 * this method run the game until every car finish the game
	 */
	public void run() {
		int tickNumber = 0;
		while (!finishline.finished()) {
			tick();
			logger.logNewTick();
			tickNumber++;
		}
		calculatorScore(tickNumber);
	}
	
	/**
	 * return final score
	 * @param ticks
	 * @return
	 */
	public int calculatorScore(int ticks) {
		int score = 1000 - 20 * ticks + 150 * RaceCar + 100 * FormulaOne + 200 * SportsCar;
	
		return score;
	}
	
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerC getLogger() {
		return logger;
	}

}
