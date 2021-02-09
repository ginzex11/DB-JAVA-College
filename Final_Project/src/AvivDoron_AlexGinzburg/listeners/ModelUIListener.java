package AvivDoron_AlexGinzburg.listeners;

import java.util.InputMismatchException;

import AvivDoron_AlexGinzburg.model.Degree;

public interface ModelUIListener {
	
	public void addTenureLecturerToUI(String name, int salary,int years,String specialty,String committee,Degree deg) throws Exception;
	public void addTempLecturerToUI(String name, int salary, int hours, String specialty, Degree deg) throws InputMismatchException, NumberFormatException, Exception;
	
	public void findLecturer(int id, int flag); //flag will determine tenure or temporary lecturer
	public void selectChairman(int flag);
	
	public void removeLecturer(int id);

	public void sendMessage(String message);
	public void paycheck();
	public void closeProgram();
}
