package com.medievaltower.levels;

public class NiveauPersonnage {
    private final int[] expRequired = {0, 175, 400, 850, 1300, 2050};
    private final int MAX_LEVEL = expRequired.length - 1;
    private int currentLevel;
    private int hearts;
    private int exp;


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

    public void setHearts(int i) {
        this.hearts = i;
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
        if (exp >= expRequired[currentLevel] && currentLevel < MAX_LEVEL) {
            currentLevel++;
            exp = 0; // Réinitialiser l'expérience pour le nouveau niveau
            hearts += 1; // Augmenter les cœurs si nécessaire
            return true; // Retourner true pour indiquer qu'un niveau a été franchi
        }
        return false; // Retourner false si aucun niveau n'a été franchi
    }

    public void gainExp(int amount) {

        if (currentLevel >= MAX_LEVEL) {
            return;
        }

        // Ajout de l'expérience et vérification d'un éventuel niveau supérieur
        this.exp += amount;
        if (this.exp >= expRequired[currentLevel]) {
            // Monter de niveau si possible
            levelUp();
        }
    }

    public void gainHeart(int i) {
        this.hearts += i;
    }

    public int getExpByCurrentLevel(int level) {
        return expRequired[level];
    }

    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
