package AvivDoron_AlexGinzburg.model;

import java.util.InputMismatchException;

public class TempLecturer extends Lecturer {

    private int hourlySalary;
    private int dailyHours;
    
    public TempLecturer(String name, Degree deg, String specialty, int salary, int hours) throws Exception {
        super(name, deg, specialty);
        setHourlySalary(salary);
        setDailyHours(hours);
    }
    
    public TempLecturer(TempLecturer tempLecturer) {
        super(tempLecturer.getName(),tempLecturer.getDegree(),tempLecturer.getSpecialty());
        this.hourlySalary = tempLecturer.getHourlySalary();
        this.dailyHours = tempLecturer.getDailyHours();
    }
       
    public void setHourlySalary(int hourlySalary) throws InputMismatchException {
    	if(hourlySalary <= 0)
    		throw new InputMismatchException();
    	else
    		this.hourlySalary = hourlySalary;
	}

	public void setDailyHours(int dailyHours) throws NumberFormatException {
    	if(dailyHours <= 0 || dailyHours > 14)
    		throw new NumberFormatException();
    	else
    		this.dailyHours = dailyHours;
		
	}

	public int getHourlySalary() {
        return hourlySalary;
    }

    public int getDailyHours() {
        return dailyHours;
    }

	@Override
	public String toString() {
		return super.toString()+" hourlySalary=" + hourlySalary + ", dailyHours=" + dailyHours + "] is TempLecturer";
	}
    
    





}
