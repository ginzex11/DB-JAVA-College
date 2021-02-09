package AvivDoron_AlexGinzburg.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.lang.Math;

public class TenureUnion extends Union implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected final String tenureUnionFile = "tenureUnion.txt";
	protected TenureLecturer chairman;
	protected ArrayList<TenureLecturer> lecturers;
	protected int size;
	
	public TenureUnion() {
		this.size = 0;
		lecturers = new ArrayList<TenureLecturer>();
	}
	
    public int getSize() {
		return this.size;
	}
    
    @Override
	protected void incrementSize() {
    	this.size = this.size + 1;
    }
	
	@Override
	protected void decrementSize() {
		this.size = this.size - 1;
	}
	
    @Override
    public Lecturer getChairman() {
		return this.chairman;
	}


	@Override
	public void addLecturerToUnion(Lecturer newLecturer) {
		TenureLecturer t = (TenureLecturer) newLecturer;
		lecturers.add(t);
		incrementSize();			
	}
	
	@Override
	protected void setChairman() {
		int num,size = this.getSize();
		if(size > 0)
		{
			num  = (int)Math.floor(Math.random()*size);
			chairman = lecturers.get(num);
		}
	}
	
	@Override
	protected void payment() {
		double paycheck;
		
		for(TenureLecturer t: lecturers) 
		{
			paycheck = t.getMonthlySalary() + (t.getSeniority())*0.2;
			System.out.println(t.getName()+"("+t.getId()+") is "+ t.getClass().getSimpleName()+", Paid: " +paycheck+"$.");
		}		
	}

	protected TenureLecturer findById(int id) {
		
		for(TenureLecturer t: this.lecturers) 
		{		
			if(t.getId() == id)
				return t;
		}		
		return null;
	}
	
    /**
     * save file section
     **/
	public void saveTenureUnion(ArrayList<TenureLecturer> lecturers) throws FileNotFoundException, IOException {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(tenureUnionFile))) {
            object.writeInt(lecturers.size());
            for (TenureLecturer i : lecturers) {
                object.writeInt(i.getId());
                object.writeUTF(String.valueOf(i.getCommittee()));
                object.writeUTF(String.valueOf(i.getSeniority()));
                object.writeUTF(String.valueOf(i.getName()));
                object.writeUTF(String.valueOf(i.getSeniority()));
                object.writeObject(i.getDegree());
                object.writeInt(i.getMonthlySalary());
                object.writeUTF(String.valueOf(i.getSpecialty()));
            }
        }
    }

	public ArrayList<TenureLecturer> readTenureUnion() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<TenureLecturer> list = new ArrayList<>();
        InputStream stream = new ByteArrayInputStream(tenureUnionFile.getBytes(StandardCharsets.UTF_8));
        try (ObjectInputStream object = new ObjectInputStream(new ObjectInputStream(stream))) {
            int i = object.readInt();
           while (stream.available()>0) {
                int id = object.readInt();
                int salary = object.readInt();
                String name = object.readUTF();
                String committee = object.readUTF();
                int years = object.readInt();
                String degree = object.readUTF();
                String specialty = object.readUTF();
                TenureLecturer tenureLecturerTmp = null;

                try {
                    tenureLecturerTmp = new TenureLecturer(name, Degree.valueOf(degree), specialty, salary, years, committee);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                list.add(tenureLecturerTmp);
            }
        }
        return list;
    }

	
}
