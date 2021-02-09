package AvivDoron_AlexGinzburg.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import AvivDoron_AlexGinzburg.listeners.ModelEventListener;


public class Model {
	
    private Academy academy;
    private ArrayList<ModelEventListener> allListeners;    

    public Model() {
        academy = new Academy("A&A Academy");
        allListeners = new ArrayList<ModelEventListener>();
        academy.tenureU = new TenureUnion();
        academy.tempU = new TempUnion();
    }
    
	public void registerListener(ModelEventListener listener) {
		allListeners.add(listener);
	}

	public void addTenureLecturer(String name,int salary,int years,String specialty,String Committee,Degree deg) throws Exception {
		boolean result=false;
		TenureLecturer newLecturer=null;
		if(salary != 0) {
			try {
				 newLecturer = new TenureLecturer(name,deg,specialty,salary,years,Committee);				 
			}
			catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null, "Salary must be positive integer!");
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Seniority must be positive integer!");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Unknown error, please try again!");
			}
			
			result = academy.addNewLecturer(newLecturer);		
			if(result) {// success 
				
				academy.tenureU.addLecturerToUnion(newLecturer);
				fireAddLecturerEvent(newLecturer);
				fireUpdateNumOfLecturers(academy.tenureU.getSize(),academy.tempU.getSize());
				
			}				
			else
				JOptionPane.showMessageDialog(null, "Error! couldn't add Tenure Lecturer!");
		}

			
	}
	
	public void addTempLecturer(String name, int salary, int hours, String specialty, Degree deg) throws Exception,InputMismatchException,NumberFormatException {
		boolean result=false;
		TempLecturer newLecturer=null;
		if(salary != 0) {
			try {
				newLecturer = new TempLecturer(name, deg, specialty, salary, hours);
			}
			catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null, "Daily Salary must be positive integer!");
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Daily Hours must be between 1-14!");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Unknown error, please try again!");
			}
			
			result = academy.addNewLecturer(newLecturer);
			if(result) { // success
				fireAddLecturerEvent(newLecturer);
				academy.tempU.addLecturerToUnion(newLecturer);
				fireUpdateNumOfLecturers(academy.tenureU.getSize(),academy.tempU.getSize());
			}
			else
				JOptionPane.showMessageDialog(null, "Error! couldn't add Temporary Lecturer!");
		}

	}
	
	private void fireAddLecturerEvent(Lecturer newLecturer) {
		//System.out.println(newLecturer.toString());
		if(newLecturer instanceof TenureLecturer) {
			TenureLecturer t = (TenureLecturer) newLecturer;
			for(ModelEventListener l: allListeners)
				l.addedTenureLecturerToModelEvent(t.getName(),t.getMonthlySalary(),t.getSeniority(),t.getSpecialty(),t.getCommittee(), t.getDegree(),t.getId());
		}
		else if (newLecturer instanceof TempLecturer)
		{
			TempLecturer t =(TempLecturer) newLecturer;
			for(ModelEventListener l: allListeners)
				l.addedTempLecturerToModelEvent(t.getName(), t.getHourlySalary(), t.getDailyHours(), t.getSpecialty(), t.getDegree(),t.getId());
		}
		
	}
	
	public void findLecturer(int id, int flag) {
			int lecturer = 0;
			
			try{
				if(flag == 0)
				{
					TenureLecturer t = academy.tenureU.findById(id);
					fireDisplayLecturer(t,lecturer);
				}
				else {
					TempLecturer tm = academy.tempU.findById(id);
					fireDisplayLecturer(tm,lecturer);
				}
			}
			catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Couldn't find lecturer, please try again.");
			}		
	}
	
	private void fireDisplayLecturer(Lecturer toDisplay, int flag) {
		
		if(flag == 0) //lecturer
		{
			if(toDisplay instanceof TenureLecturer)
			{
				TenureLecturer t = (TenureLecturer) toDisplay;
				for(ModelEventListener l: allListeners)
					l.displayTenureLecturer(t.getName(),t.getMonthlySalary(),t.getSeniority(),t.getSpecialty(),t.getCommittee(),t.getDegree(),t.getId());			
			}
			
			if(toDisplay instanceof TempLecturer)
			{
				TempLecturer t = (TempLecturer) toDisplay;
				for(ModelEventListener l: allListeners)
					l.displayTempLecturer(t.getName(), t.getHourlySalary(), t.getDailyHours(), t.getSpecialty(), t.getDegree(), t.getId());
			}
		}
		
		if(flag == 1) //chairman
		{
			if(toDisplay instanceof TenureLecturer)
			{
				TenureLecturer t = (TenureLecturer) toDisplay;
				for(ModelEventListener l: allListeners)
					l.displayTenureChairman(t.getName(),t.getMonthlySalary(),t.getSeniority(),t.getSpecialty(),t.getCommittee(),t.getDegree(),t.getId());
			}
			
			if(toDisplay instanceof TempLecturer)
			{
				TempLecturer t = (TempLecturer) toDisplay;
				for(ModelEventListener l: allListeners)
					l.displayTempChairman(t.getName(), t.getHourlySalary(), t.getDailyHours(), t.getSpecialty(), t.getDegree(), t.getId());
			}
		}
	}
	
	public void selectChairman(int flag) {
		int chairman = 1; 
		if((flag ==0 && academy.tenureU.getSize()==0) || (flag == 1  && academy.tenureU.getSize()==0) )
			throw new NullPointerException("There are no lecturers to choose from");
		
		try {
			if(flag == 0 ) {// Tenure
				academy.tenureU.setChairman();
				TenureLecturer t = (TenureLecturer)academy.tenureU.getChairman();
				fireDisplayLecturer(t,chairman);
			}
			
			if(flag == 1) { // Temporary
				academy.tempU.setChairman();
				TempLecturer t = (TempLecturer)academy.tempU.getChairman();
				fireDisplayLecturer(t,chairman);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Couldn't find Chairman, please try again.");
		}			
	}
	
	public void fireUpdateNumOfLecturers(int sizeOfTenure, int sizeOfTemp) {
		for(ModelEventListener l: allListeners)
			l.updateNumber(sizeOfTenure, sizeOfTemp);
	}
	
	public void removeLecturerFromModel(int id) {
		
		Lecturer toRemove = getLecturerById(id);
		
		if(removeLecturer(id)) {			
			fireRemoveLecturer(toRemove);
			fireUpdateNumOfLecturers(academy.tenureU.getSize(), academy.tempU.getSize());			
		}
		
	}
	
	public void fireRemoveLecturer(Lecturer toRemove) {
		
		if(toRemove instanceof TenureLecturer) {
			for(ModelEventListener l: allListeners)
				l.removeTenureLecturer(toRemove.getId());				
		}
		
		if(toRemove instanceof TempLecturer) {
			for(ModelEventListener l: allListeners)
				l.removeTempLecturer(toRemove.getId());							
		}			
	}
	
    public boolean removeLecturer(int id) {
    	
    	Lecturer toRemove = getLecturerById(id);    	
    	boolean tenure,temp,ok = academy.allLecturers.remove(toRemove);
    	
    	if(toRemove instanceof TenureLecturer) {
    		tenure = academy.tenureU.lecturers.remove(toRemove);
    		if(ok && tenure) {
    			academy.tenureU.decrementSize();
    			return true;
    		}    			
    	}
    	
    	if(toRemove instanceof TempLecturer) {
    		temp = academy.tempU.lecturers.remove(toRemove);
    		if(ok && temp) {
    			academy.tempU.decrementSize();
    			return true;
    		}    			
    	}    	
    	return false;
    }
	
	public Lecturer getLecturerById(int id) {
		for (Lecturer l : academy.allLecturers)	{
			if(l.getId() == id)
				return l;
		}	
		return null;
	}
	
	public void sendMessageToAcademy(String message) {
		if(message.equals("") || message == null)
			JOptionPane.showMessageDialog(null, "Message is empty, try again!");
		else {
		
			JOptionPane.showMessageDialog(null, "Message sent to all lecturers: "+message);
		}
	}
	
	public void payLecturers() {
		String str="Payments:\n";
		
		if(academy.tempU.getSize() != 0 && academy.tenureU.getSize() != 0) {
			for (Lecturer l : academy.allLecturers) {
				if(l instanceof TenureLecturer)
					str = str +l.getName()+ " got paid 500$\n";
				
				if(l instanceof TempLecturer)
					str = str +l.getName()+ " got paid 350$\n";
			}		
			JOptionPane.showMessageDialog(null, str);	
		}
		else
			throw new InputMismatchException("There are no lecturers to pay for!");
		
	}
	
	public void saveFile() {
		try {
			academy.saveAllLecturers(academy.allLecturers);
			academy.tenureU.saveTenureUnion(academy.tenureU.lecturers);
			academy.tempU.saveTempUnion(academy.tempU.lecturers);
		}catch (NullPointerException e) {
			return;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loadFile() {
		try {
			academy.readAllLecturers();
			academy.tempU.lecturers = academy.tempU.readTempUnion();
			academy.tenureU.lecturers = academy.tenureU.readTenureUnion();
			academy.allLecturers.addAll(academy.tempU.lecturers);
			academy.allLecturers.addAll(academy.tenureU.lecturers);
			return true;
			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}catch (ClassNotFoundException e) {
			return false;
		}
	}
}
