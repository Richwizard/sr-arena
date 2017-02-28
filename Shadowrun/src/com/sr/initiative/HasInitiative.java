package com.sr.initiative;

public interface HasInitiative {
	public abstract int getIni();
	public abstract int rollIni();
	public abstract String getIniDesc();
	
	public abstract void reduceIni();
}
