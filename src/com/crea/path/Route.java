package com.crea.path;

public class Route {
	
	String startPosition;
	String destination;
	int distance;
	boolean flag;
	
	public Route(String startPosition, String destination, int distance, boolean flag) {
		super();
		this.startPosition = startPosition;
		this.destination  = destination;
		this.distance = distance;
		this.flag = flag;
	}
	
	
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

