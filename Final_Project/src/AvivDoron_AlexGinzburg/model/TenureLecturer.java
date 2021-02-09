package AvivDoron_AlexGinzburg.model;

import java.util.InputMismatchException;

public class TenureLecturer extends Lecturer {

    private int monthlySalary;
    private int seniority; 
    private String committee;

    public TenureLecturer(String name, Degree deg, String specialty, int salary, int years, String committee) throws Exception,InputMismatchException {
    	super(name, deg, specialty);    		        
        setMonthlySalary(salary);
        setSeniority(years);
        this.committee = committee;
    }
    
    public TenureLecturer(TenureLecturer tenureLecturer) {
        super(tenureLecturer.getName(), tenureLecturer.getDegree(), tenureLecturer.getSpecialty());
        this.monthlySalary = tenureLecturer.getMonthlySalary();
        this.seniority = tenureLecturer.getSeniority();
        this.committee = tenureLecturer.getCommittee();
    }
        
    public void setMonthlySalary(int monthlySalary) throws InputMismatchException,NumberFormatException {
		if(monthlySalary <= 0)
			throw new InputMismatchException();
		else
			this.monthlySalary = monthlySalary;
	}
    
	public void setSeniority(int seniority) throws Exception {
		if(seniority <= 0) 
			throw new NumberFormatException();
		else
			this.seniority = seniority;
	}
        
    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getSeniority() {
        return seniority;
    }

    public String getCommittee() {
        return committee;
    }
	
    @Override
	public String toString() {
		return super.toString()+" monthlySalary=" + monthlySalary + ", seniority=" + seniority + ", committee="
				+ committee + "] is TenureLecturer";
	}

    


}
