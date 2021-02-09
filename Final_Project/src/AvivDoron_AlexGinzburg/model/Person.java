package AvivDoron_AlexGinzburg.model;

public  class Person {
    private static int idGenerator = 1000;
    private String name;
    private int id;

    public Person(String name) {
        this.name = name;
        this.id = idGenerator++;

    }

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
}
