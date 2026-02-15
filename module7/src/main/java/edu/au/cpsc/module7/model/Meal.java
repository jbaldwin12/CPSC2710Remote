package edu.au.cpsc.module7.model;

import java.time.LocalDate;

public class Meal {
    private LocalDate date;
    private String name;
    private int calories;
    private int protein;
    private String notes;

    public Meal(LocalDate date, String name, int calories, int protein) {
        this.date = date;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.notes = "";
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
