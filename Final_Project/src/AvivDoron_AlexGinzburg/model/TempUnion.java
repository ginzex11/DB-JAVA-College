package AvivDoron_AlexGinzburg.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.lang.Math;

public class TempUnion extends Union implements Serializable  {

	private static final long serialVersionUID = 1L;
	protected final String temporaryUnionFile = "temporaryUnion.txt";
	protected TempLecturer chairman;
	protected ArrayList<TempLecturer> lecturers;
	int size;
	
	public TempUnion() {
		this.size = 0;
		lecturers = new ArrayList<TempLecturer>();
	}
	
    public int getSize() {
		return size;
	}

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
		TempLecturer t = (TempLecturer) newLecturer;
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
		
		for(TempLecturer t: lecturers) 
		{
			paycheck = t.getDailyHours()*t.getHourlySalary();
			System.out.println(t.getName()+"("+t.getId()+") is "+ t.getClass().getSimpleName()+", Paid: " +paycheck+"$.");
		}
		
	}

	protected TempLecturer findById(int id) {
		
		for(TempLecturer t: lecturers) 
		{
			if(t.getId() == id)
				return t;
		}
		
		return null;
	}
	
    /**
     * save file section
     **/
	public void saveTempUnion(ArrayList<TempLecturer> lecturers) throws FileNotFoundException, IOException {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(temporaryUnionFile))) {
            object.writeInt(lecturers.size());
            for (TempLecturer i : lecturers) {
                object.writeInt(i.getId());
                object.writeUTF(String.valueOf(i.getName()));
                object.writeObject((i.getDegree()));
                object.writeUTF(String.valueOf(i.getSpecialty()));
                object.writeInt(i.getDailyHours());
                object.writeInt(i.getHourlySalary());
            }
        }
    }
public ArrayList<TempLecturer> readTempUnion() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<TempLecturer> list = new ArrayList<>();
        InputStream stream = new ByteArrayInputStream(temporaryUnionFile.getBytes(StandardCharsets.UTF_8));
        try (ObjectInputStream object = new ObjectInputStream(new ObjectInputStream(stream))) {
            int i = object.readInt();
            while (stream.available() > 0) {
                int id = object.readInt();
                String name = object.readUTF();
                String degree = object.readUTF();
                String specialty = object.readUTF();
                int salary = object.readInt();
                int hours = object.readInt();
                TempLecturer temporaryLecturerTmp = null;
                try {
                    temporaryLecturerTmp = new TempLecturer(name, Degree.valueOf(degree), specialty, salary, hours);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.add(temporaryLecturerTmp);
            }
        }
        return list;
    }

    /**
     * save file section
     **/


	
	
	
	
	
	

}
