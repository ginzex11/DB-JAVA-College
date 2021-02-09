package AvivDoron_AlexGinzburg.model;


public abstract class Union {

  
	protected abstract void addLecturerToUnion(Lecturer newLecturer);
	protected abstract void payment();
	protected abstract void setChairman();
	protected abstract void incrementSize();
	protected abstract void decrementSize();
	public abstract Lecturer getChairman();
	
	//protected abstract Lecturer findById(int id);
	

}
