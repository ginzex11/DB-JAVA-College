package AvivDoron_AlexGinzburg.view;

import AvivDoron_AlexGinzburg.listeners.ModelUIListener;
import AvivDoron_AlexGinzburg.model.Degree;

public interface AbstractAcademyView {
	
	void registerListener(ModelUIListener listener);
	void addTenureLecturerToUI(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
	void addTempLecturerToUI(String name, int salary, int hours, String specialty, Degree deg,int id);
	
	public void displayTenureLecturer(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
	public void displayTempLecturer(String name, int salary, int hours, String specialty, Degree deg, int id);
	
	public void displayTenureChairman(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
	public void displayTempChairman(String name, int salary, int hours, String specialty, Degree deg, int id);
	
	public void removeTenureFromUI(int id);
	public void removeTempFromUI(int id);
	public void updateNumOfLecturers(int sizeOfTenure,int sizeOfTemp);
}
