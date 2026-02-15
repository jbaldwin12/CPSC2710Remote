package edu.au.cpsc.module7.model;

public class NutritionGoals {

    private int calGoal;
    private int proteinGoal;

    //default goals
    public NutritionGoals() {
        this.calGoal = 2000;
        this.proteinGoal = 125;
    }

    public NutritionGoals(int calGoal, int proteinGoal) {
        this.calGoal = calGoal;
        this.proteinGoal = proteinGoal;
    }

    public int getCalGoal() {
        return calGoal;
    }

    public void setCalGoal(int calGoal) {
        this.calGoal = calGoal;
    }

    public int getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(int proteinGoal) {
        this.proteinGoal = proteinGoal;
    }
}
