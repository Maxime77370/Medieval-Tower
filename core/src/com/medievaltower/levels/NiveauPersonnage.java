package com.medievaltower.levels;

public class NiveauPersonnage {
    private int currentLevel;
    private int hearts;
    private int exp;
    private final int[] expRequired = {0, 100, 200, 300, 400, 500}; // Adjust as needed

    public NiveauPersonnage() {
        this.currentLevel = 1;
        this.hearts = 3;
        this.exp = 0;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getHearts() {
        return hearts;
    }

    public void loseHeart(int damage) {
        this.hearts -= damage;
    }

    public int getExp() {
        return exp;
    }

    public int getExpRequiredForNextLevel() {
        return expRequired[currentLevel] - exp;
    }

    public boolean levelUp() {
        if (exp >= getExpRequiredForNextLevel() && currentLevel < expRequired.length - 1) {
            currentLevel++;
            hearts += 1; // Adjust as needed
        }
        return false;
    }

    public void gainExp(int amount) {
        if (this.exp + amount >= expRequired[currentLevel]) {
            this.exp = expRequired[currentLevel];
        } else {
            this.exp += amount;
        }
    }

    public void gainHeart(int i) {
        this.hearts += i;
    }

    public int getExpByCurrentLevel(int level) {
        return expRequired[level];
    }

    public void setHearts(int i) {
        this.hearts = i;
    }
}
