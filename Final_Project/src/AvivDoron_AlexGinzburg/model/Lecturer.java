package AvivDoron_AlexGinzburg.model;

import java.io.Serializable;

public abstract  class Lecturer extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private Degree degree;
    private String specialty;

    public Degree getDegree() {
        return degree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Lecturer(String name, Degree deg, String specialty) {
        super(name);
        this.degree = deg;
        this.specialty = specialty;
    }

	@Override
	public String toString() {
		return "Lecturer: [degree=" + degree + ", specialty=" + specialty;
	}

    

}
