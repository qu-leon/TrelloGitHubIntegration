package Pomodoro.exec;

import java.util.Timer;

import Pomodoro.PomodoroTimer;

/** 
	* This class starts the Pomodoro countdown based on the input
	* 
	* TODO: Start countdown timer(s)
	* 
	* Created: Oct 24, 2016
  * @author Conner Higashino
  */

public class PomodoroExecutor {
	
	private PomodoroTimer timer;
	/* times are all in seconds */
	private long timeSet;									//length of time for each pomodoro
	private long breakSet;								//length of time for each normal break
	private long longSet;									//length of time for each long break
	private long currentTime;							//time remaining in current countdown
	private int currentPomodoro;					//what number pomodoro is this, starts at 1
	private int totalPomodoro;						//number of total pomodoros to be run
	
	/* booleans indicate what timer is currently going and if all pomodoros have finished */
	private boolean Pomodoro;
	private boolean Break;
	private boolean LongBreak;
	private boolean Finished;

	/* constructors */
	
	public PomodoroExecutor (PomodoroTimer timer) {
		this.setTimer(timer);
	}
	
	/* Executer functions */
	
	/**
	 * This function initializes all timer fields and booleans, setting the current timer to the Pomodoro work time
	 */
	public void initTimer() {
		long temp;
		/* set normal timer */
		temp = this.getTimer().getMins();
		if (temp != 0) {
			temp *= 60;
		}
		temp += this.getTimer().getSec();
		this.setTimeRemaining(temp);
		/* set break timer */
		temp = this.getTimer().getBreakMin();
		if (temp != 0) {
			temp *= 60;
		}
		temp += this.getTimer().getBreakSec();
		this.setBreakRemaining(temp);
		/* set long break timer */
		temp = this.getTimer().getLongBreakMin();
		if (temp != 0) {
			temp *= 60;
		}
		temp += this.getTimer().getLongBreakSec();
		this.setLongBreakRemaining(temp);
		/* set booleans */
		this.setPomodoro(true);
		this.setBreak(false);
		this.setLongBreak(false);
		this.setFinished(false);
	}
	
	/**
	 * Initialize a countdown timer based on which is the active timer
	 */
	public void setCountdown() {
		if(this.isPomodoro()) {
			this.setCurrentTime(this.getPomodoroTime());
		} else if (this.isBreak()) {
			this.setCurrentTime(this.getBreakTime());
		} else if (this.isLongBreak()) {
			this.setCurrentTime(this.getLongBreakTime());
		} else if (this.isFinished()) {
			/* Hey, you finished */
		} else {
			/* perror(got confused, fail-safe) */
		}
	}
	
	/**
	 * Starts counting down, decrements currentTime every second(with some drift). 
	 * @throws InterruptedException
	 */
	
	public void countdown() throws InterruptedException {
		while (this.getCurrentTime() != 0) {
			this.delay_sec();
			this.setCurrentTime(this.getCurrentTime() - 1);
		}
		if (this.isPomodoro()) {
			this.setPomodoro(false);
			if (this.getCurrentPomodoro() == this.getTotalPomodoro())
			if ((this.getCurrentPomodoro() % this.getTimer().getLongBreakFreq()) == 0) {
				this.setLongBreak(true);
				this.setBreak(false);
			} else {
				this.setBreak(true);
				this.setLongBreak(false);
			}
		}
	}

	public void delay_sec() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	/* mutators */
	
	/* setters */
	
	public void setTimer(PomodoroTimer timer) {
		this.timer = timer;
	}
	
	public void setTimeRemaining(long time) {
		this.timeSet = time;
	}
	
	public void setBreakRemaining(long time) {
		this.breakSet = time;
	}
	
	public void setLongBreakRemaining(long time) {
		this.longSet = time;
	}

	public void setCurrentTime(long time) {
		this.currentTime = time;
	}
	
	public void setPomodoro(boolean bool) {
		this.Pomodoro = bool;
	}
	
	public void setBreak(boolean bool) {
		this.Break = bool;
	}
	
	public void setLongBreak(boolean bool) {
		this.LongBreak = bool;
	}
	
	public void setFinished(boolean bool) {
		this.Finished = bool;
	}
	
	/* getters */
	
	public PomodoroTimer getTimer() {
		return this.timer;
	}
	
	public long getPomodoroTime() {
		return this.timeSet;
	}
	
	public long getBreakTime() {
		return this.breakSet;
	}
	
	public long getLongBreakTime() {
		return this.longSet;
	}
	
	public long getCurrentTime() {
		return this.currentTime;
	}
	
	public int getTotalPomodoro() {
		return this.totalPomodoro;
	}
	
	public int getCurrentPomodoro() { 
		return this.currentPomodoro;
	}
	
	/* booleans */
	
	public boolean isPomodoro() {
		return this.Pomodoro;
	}
	
	public boolean isBreak() {
		return this.Break;
	}
	
	public boolean isLongBreak() {
		return this.LongBreak;
	}
	
	public boolean isFinished() {
		return this.Finished;
	}
	
}
