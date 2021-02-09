package AvivDoron_AlexGinzburg.controller;

import java.util.InputMismatchException;

import AvivDoron_AlexGinzburg.listeners.ModelEventListener;
import AvivDoron_AlexGinzburg.listeners.ModelUIListener;
import AvivDoron_AlexGinzburg.model.*;
import AvivDoron_AlexGinzburg.view.AbstractAcademyView;
import AvivDoron_AlexGinzburg.view.View;


public class AcademyController implements ModelEventListener , ModelUIListener{

    private  Model theModel;
    private AbstractAcademyView theView;

    public AcademyController(Model model, AbstractAcademyView view) {
        this.theModel = model;
        this.theView = view;
        
        theModel.registerListener(this);
        theView.registerListener(this);

    }
    
	@Override
	public void addedTempLecturerToModelEvent(String name, int salary, int hours, String specialty, Degree deg,int id) {
		theView.addTempLecturerToUI(name, salary, hours, specialty, deg, id);
		
	}

	@Override
	public void addedTenureLecturerToModelEvent(String name,int salary,int years,String specialty,String committee,Degree deg, int id) {
		theView.addTenureLecturerToUI(name, salary, years, specialty, committee, deg, id);
		
	}
	
	@Override
	public void addTenureLecturerToUI(String name,int salary,int years,String specialty,String committee,Degree deg) throws Exception {
		theModel.addTenureLecturer(name, salary, years, specialty, committee, deg);
		
	}
	
	@Override
	public void addTempLecturerToUI(String name, int salary, int hours, String specialty, Degree deg) throws InputMismatchException, NumberFormatException, Exception {
		theModel.addTempLecturer(name, salary, hours, specialty, deg);
	}

	@Override
	public void findLecturer(int id,int flag) {
		theModel.findLecturer(id, flag);		
	}

	@Override
	public void displayTenureLecturer(String name,int salary,int years,String specialty,String committee,Degree deg,int id) {
		theView.displayTenureLecturer(name, salary, years, specialty, committee, deg, id);
		
	}

	@Override
	public void displayTempLecturer(String name, int salary, int hours, String specialty, Degree deg, int id) {
		theView.displayTempLecturer(name, salary, hours, specialty, deg, id);
		
	}

	@Override
	public void selectChairman(int flag) {
		theModel.selectChairman(flag);
	}

	@Override
	public void displayTenureChairman(String name, int salary, int years, String specialty, String committee,Degree deg, int id) {
		theView.displayTenureChairman(name, salary, years, specialty, committee, deg, id);
		
	}

	@Override
	public void displayTempChairman(String name, int salary, int hours, String specialty, Degree deg,int id) {
		theView.displayTempChairman(name, salary, hours, specialty, deg, id);
		
	}

	@Override
	public void updateNumber(int sizeOfTenure, int sizeOfTemp) {
		theView.updateNumOfLecturers(sizeOfTenure, sizeOfTemp);
		
	}

	@Override
	public void sendMessage(String message) {
		theModel.sendMessageToAcademy(message);		
	}

	@Override
	public void paycheck() {
		theModel.payLecturers();
		
	}

	@Override
	public void removeLecturer(int id) {
		theModel.removeLecturerFromModel(id);
		
	}

	@Override
	public void removeTenureLecturer(int id) {
		theView.removeTenureFromUI(id);
		
	}

	@Override
	public void removeTempLecturer(int id) {
		theView.removeTempFromUI(id);
		
	}

	@Override
	public void closeProgram() {
		theModel.saveFile();
		
	}


	

}
