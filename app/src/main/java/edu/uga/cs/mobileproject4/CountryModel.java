package edu.uga.cs.mobileproject4;

public class CountryModel {

    //variables
    private int id;
    private String name;
    private String continent;

    //constructors
    public CountryModel(int id, String name, String continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    public CountryModel(){}

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    //tostring
    @Override
    public String toString() {
        return "CountryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}
