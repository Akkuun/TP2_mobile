package com.example.tp2_mobile;

public class Country {
    private String name;
    private String flag;
    private String capital;
    private int population;

    public Country(String name, String flag, String capital, int population) {
        this.name = name;
        this.flag = flag;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
