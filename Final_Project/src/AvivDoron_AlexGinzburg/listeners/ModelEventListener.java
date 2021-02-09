package AvivDoron_AlexGinzburg.listeners;
import AvivDoron_AlexGinzburg.model.Degree;

public interface ModelEventListener {
	
    void addedTenureLecturerToModelEvent(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
    void addedTempLecturerToModelEvent(String name, int salary, int hours, String specialty, Degree deg,int id);
    
    void displayTenureLecturer(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
    void displayTempLecturer(String name, int salary, int hours, String specialty, Degree deg,int id);
    
    void displayTenureChairman(String name,int salary,int years,String specialty,String committee,Degree deg,int id);
    void displayTempChairman(String name, int salary, int hours, String specialty, Degree deg,int id);
    
    void removeTenureLecturer(int id);
    void removeTempLecturer(int id);
    
    void updateNumber(int sizeOfTenure, int sizeOfTemp);
    
}
