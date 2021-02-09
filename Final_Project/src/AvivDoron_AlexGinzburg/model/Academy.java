package AvivDoron_AlexGinzburg.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Academy implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String name;
    protected final String allLecturersFile = "allLecturers.txt";
    protected ArrayList<Lecturer> allLecturers;
    protected TenureUnion tenureU;
    protected TempUnion tempU;
    
    
    public Academy(String name) {
        this.name = name;
        allLecturers = new ArrayList<>();
        tenureU = new TenureUnion();
        tempU = new TempUnion();
    }

    public String getName() {
        return name;
    }
    
    public int getSize() {
    	return allLecturers.size();
    }
    
    public boolean addNewLecturer(Lecturer toAdd) { // add to array list of lecturers
    	if(allLecturers.add(toAdd) && toAdd != null)
    		return true;
    	return false;
    }
    
    
    /**
     * save file section
     **/
    public void saveAllLecturers(ArrayList<Lecturer> lecturers) throws FileNotFoundException, IOException {
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(allLecturersFile))) {
            object.writeInt(lecturers.size());
            for (Lecturer i : allLecturers) {
                object.writeInt(i.getId());
                object.writeUTF(String.valueOf(i.getName()));
                object.writeObject((i.getDegree()));
                object.writeUTF(String.valueOf(i.getSpecialty()));


            }
        }
    }

    public ArrayList<Lecturer> readAllLecturers() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Lecturer> list = new ArrayList<>();
        InputStream stream = new ByteArrayInputStream(allLecturersFile.getBytes(StandardCharsets.UTF_8));
        try (ObjectInputStream object = new ObjectInputStream(new ObjectInputStream(stream))) {
            int i = object.readInt();
            Lecturer lecturerTmp = null;
            while (stream.available() > 0) {
                int id = object.readInt();
                String name = object.readUTF();
                String degree = object.readUTF();
                String specialty = object.readUTF();
                try {
                    lecturerTmp = new Lecturer(name, Degree.valueOf(degree), specialty) {
                    };
                } catch (Exception e) {
                    e.printStackTrace();
                }

                list.add(lecturerTmp);
                System.out.println(list);

            }
        }
        return list;
    }



    
    


}
