package com.example.demo.domain;

public class MiniSeriesDTO {
    public int losses;        // Total number of losses
    public String progress;   // Progress description (e.g., "In Progress", "Complete")
    public int target;        // Target value (e.g., target number of wins or points)
    public int wins;          // Total number of wins
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
    
}
