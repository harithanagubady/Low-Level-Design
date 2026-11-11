package com.weatherstation.v3;

public interface Subject {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
