package com.Crucible.Character_Resources;

import java.util.ArrayList;
import java.util.Arrays;

public class ForgeCharacter extends SubStats{
        private int speed;
        private Size size;
        private Gender gender;
        private ArrayList<Integer> statList = new ArrayList<>();
        private Race race;
        private String name;
        private Class characterClass;
        private int characterLevel;
        private int experience;
        private Alignment alignment;
        private SubRace subrace;
        ArrayList<Integer> statsList;

    public ForgeCharacter(Race race, Class characterClass, int characterLevel) {
        this.race = race;
        this.characterClass = characterClass;
        this.characterLevel = characterLevel;
    }

    public void forgeLogic() {
        formatStats();
        calculateSize(race);
        calculateSpeed(race, subrace);
        calculateRacialStats();
        calculateStrengthSubstats();
        calculateDexteritySubstats();
        calculateIntellectSubstats();
        calculateWisdomSubstats();
        calculateCharismaSubstats();
    }
    private ArrayList<Integer> rollStats4d6() {
        Dice dice = new Dice();
        ArrayList<Integer> statList = new ArrayList<>();
        int[] rolls = new int[4];
        int test = 0;
        for(int i = 0; i < 6; i ++) {
            for(int k = 0; k < 4; k++) {
                int stat = dice.diceRoll(6);
                rolls[k] = stat;
            }
            Arrays.sort(rolls);
            rolls[0] = 0;
            for (int l = 0; l < rolls.length; l++) {
                test += rolls[l];
                rolls[l] = 0;
            }
            statList.add(test);
            test = 0;
        }
        int total = 0;
        for (Integer integer : statList) {
            total += integer;
        }
        if (total < 72) {
            rollStats4d6();
        }
        return statList;
    }
    private ArrayList<Integer> standardStatList() {
        ArrayList<Integer> statList = new ArrayList<>();
        statList.add(15);
        statList.add(14);
        statList.add(13);
        statList.add(12);
        statList.add(10);
        statList.add(8);
        return statList;
    }
    private void formatStats() {
        statsList.add(strength);
        statsList.add(dexterity);
        statsList.add(constitution);
        statsList.add(intelligence);
        statsList.add(wisdom);
        statsList.add(charisma);
    }

    private void calculateRacialStats(String halfElfChoice) {
        switch(race) {
            case Orc, Halforc -> {
                this.strength += 2;
                this.constitution += 1;
            }
            case Gnome -> {
                if (subrace == SubRace.DeepGnome) {
                    this.intelligence += 2;
                    this.dexterity += 1;
                } else if (subrace == SubRace.RockGnome) {
                    this.constitution += 1;
                    this.intelligence += 2;
                } else if (subrace == SubRace.ForestGnome) {
                    this.intelligence += 2;
                    this.constitution += 1;
                }
            }
            case Halfling -> {
                if (subrace == SubRace.StoutHalfling) {
                    this.dexterity += 2;
                    this.constitution += 1;
                } else if (subrace == SubRace.LightFootHalfling) {
                    this.dexterity += 2;
                    this.charisma += 1;
                }
            }
            case Dwarf -> {
                if (subrace == SubRace.GrayDwarf) {
                    this.constitution += 2;
                    this.strength += 1;
                } else if (subrace == SubRace.HillDwarf) {
                    this.constitution += 2;
                    this.wisdom += 1;
                } else if (subrace == SubRace.MountainDwarf) {
                    this.strength += 2;
                    this.constitution += 2;
                }
            }
            case Dragonborn -> {
                this.strength += 2;
                this.charisma += 1;
            }
            case Tiefling -> {
                this.charisma += 2;
                this.intelligence += 1;
            }
            case Halfelf -> {
                this.charisma +=2;
                String choice = halfElfChoice.toLowerCase();
                switch (choice) {
                    case "intellect" -> this.intelligence += 1;
                    case "strength" -> this.strength += 1;
                    case "dexterity" -> this.dexterity += 1;
                    case "wisdom" -> this.wisdom += 1;
                    case "constitution" -> this.constitution += 1;
                    case "charisma" -> this.charisma += 1;
                }
            }
            case Human -> {
                this.charisma += 1;
                this.strength += 1;
                this.wisdom += 1;
                this.dexterity += 1;
                this.constitution += 1;
                this.intelligence += 1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + race);
        }
    }
    private void calculateRacialStats() {
        switch(race) {
            case Elf -> {
                if (subrace == SubRace.HighElf) {
                    this.intelligence += 1;
                    this.dexterity += 2;
                } else if (subrace == SubRace.WoodElf) {
                    this.constitution += 2;
                    this.intelligence += 1;
                } else if (subrace == SubRace.Drow) {
                    this.dexterity += 2;
                    this.charisma += 1;
                }
            }
            case Orc, Halforc -> {
                this.strength += 2;
                this.constitution += 1;
            }
            case Gnome -> {
                if (subrace == SubRace.DeepGnome) {
                    this.intelligence += 2;
                    this.dexterity += 1;
                } else if (subrace == SubRace.RockGnome) {
                    this.constitution += 1;
                    this.intelligence += 2;
                } else if (subrace == SubRace.ForestGnome) {
                    this.intelligence += 2;
                    this.constitution += 1;
                }
            }
            case Halfling -> {
                if (subrace == SubRace.StoutHalfling) {
                    this.dexterity += 2;
                    this.constitution += 1;
                } else if (subrace == SubRace.LightFootHalfling) {
                    this.dexterity += 2;
                    this.charisma += 1;
                }
            }
            case Dwarf -> {
                if (subrace == SubRace.GrayDwarf) {
                    this.constitution += 2;
                    this.strength += 1;
                } else if (subrace == SubRace.HillDwarf) {
                    this.constitution += 2;
                    this.wisdom += 1;
                } else if (subrace == SubRace.MountainDwarf) {
                    this.strength += 2;
                    this.constitution += 2;
                }
            }
            case Dragonborn -> {
                this.strength += 2;
                this.charisma += 1;
            }
            case Tiefling -> {
                this.charisma += 2;
                this.intelligence += 1;
            }
            case Human -> {
                this.charisma += 1;
                this.strength += 1;
                this.wisdom += 1;
                this.dexterity += 1;
                this.constitution += 1;
                this.intelligence += 1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + race);
        }
    }

    private void calculateSize(Race race) {
        switch(race) {
            case Elf, Tiefling, Orc, Dragonborn, Human, Halfelf, Halforc -> this.size = Size.Medium;
            case Gnome, Halfling, Dwarf -> this.size = Size.Small;
            default -> throw new IllegalStateException("Unexpected value: " + race);
        }
    }

    private void calculateStrengthSubstats() {
        int test = statsList.get(0);
        switch (test) {
            case 1 -> setAthletics(getAthletics()-5);
            case 2, 3 -> setAthletics(getAthletics()-4);
            case 4, 5 -> setAthletics(getAthletics()-3);
            case 6, 7 -> setAthletics(getAthletics()-2);
            case 8, 9 -> setAthletics(getAthletics()-1);
            case 10, 11 -> setAthletics(getAthletics());
            case 12, 13 -> setAthletics(getAthletics()+1);
            case 14, 15 -> setAthletics(getAthletics()+2);
            case 16, 17 -> setAthletics(getAthletics()+3);
            case 18 -> setAthletics(getAthletics()+4);
            default -> throw new IllegalStateException("Unexpected value: " + test);
        }
    }
    private void calculateDexteritySubstats() {
        int test = statsList.get(1);
        switch (test) {
            case 1 -> {
                setAcrobatics(getAcrobatics()-5);
                setStealth(getStealth()-5);
                setSleightOfHand(getSleightOfHand()-5);
            }
            case 2, 3 -> {
                setAcrobatics(getAcrobatics()-4);
                setStealth(getStealth()-4);
                setSleightOfHand(getSleightOfHand()-4);
            }
            case 4, 5 -> {
                setAcrobatics(getAcrobatics()-3);
                setStealth(getStealth()-3);
                setSleightOfHand(getSleightOfHand()-3);
            }
            case 6, 7 -> {
                setAcrobatics(getAcrobatics()-2);
                setStealth(getStealth()-2);
                setSleightOfHand(getSleightOfHand()-2);
            }
            case 8, 9 -> {
                setAcrobatics(getAcrobatics()-1);
                setStealth(getStealth()-1);
                setSleightOfHand(getSleightOfHand()-1);
            }
            case 10, 11 -> {
                setAcrobatics(getAcrobatics());
                setStealth(getStealth());
                setSleightOfHand(getSleightOfHand());
            }
            case 12, 13 -> {
                setAcrobatics(getAcrobatics()+1);
                setStealth(getStealth()+1);
                setSleightOfHand(getSleightOfHand()+1);
            }
            case 14, 15 -> {
                setAcrobatics(getAcrobatics()+2);
                setStealth(getStealth()+2);
                setSleightOfHand(getSleightOfHand()+2);
            }
            case 16, 17 -> {
                setAcrobatics(getAcrobatics()+3);
                setStealth(getStealth()+3);
                setSleightOfHand(getSleightOfHand()+3);
            }
            case 18 -> {
                setAcrobatics(getAcrobatics()+4);
                setStealth(getStealth()+4);
                setSleightOfHand(getSleightOfHand()+4);
            }
            default -> throw new IllegalStateException("Unexpected value: " + test);
        }
    }

    private void calculateIntellectSubstats() {
        int test = statsList.get(3);
        switch (test) {
            case 1 -> {
                setArcana(getArcana()-5);
                setHistory(getHistory()-5);
                setInvestigation(getInvestigation()-5);
                setNature(getNature()-5);
                setReligion(getReligion()-5);
            }
            case 2, 3 -> {
                setArcana(getArcana()-4);
                setHistory(getHistory()-4);
                setInvestigation(getInvestigation()-4);
                setNature(getNature()-4);
                setReligion(getReligion()-4);
            }
            case 4, 5 -> {
                setArcana(getArcana()-3);
                setHistory(getHistory()-3);
                setInvestigation(getInvestigation()-3);
                setNature(getNature()-3);
                setReligion(getReligion()-3);
            }
            case 6, 7 -> {
                setArcana(getArcana()-2);
                setHistory(getHistory()-2);
                setInvestigation(getInvestigation()-2);
                setNature(getNature()-2);
                setReligion(getReligion()-2);
            }
            case 8, 9 -> {
                setArcana(getArcana()-1);
                setHistory(getHistory()-1);
                setInvestigation(getInvestigation()-1);
                setNature(getNature()-1);
                setReligion(getReligion()-1);
            }
            case 10, 11 -> {
                setArcana(getArcana());
                setHistory(getHistory());
                setInvestigation(getInvestigation());
                setNature(getNature());
                setReligion(getReligion());
            }
            case 12, 13 -> {
                setArcana(getArcana()+1);
                setHistory(getHistory()+1);
                setInvestigation(getInvestigation()+1);
                setNature(getNature()+1);
                setReligion(getReligion()+1);
            }
            case 14, 15 -> {
                setArcana(getArcana()+2);
                setHistory(getHistory()+2);
                setInvestigation(getInvestigation()+2);
                setNature(getNature()+2);
                setReligion(getReligion()+2);
            }
            case 16, 17 -> {
                setArcana(getArcana()+3);
                setHistory(getHistory()+3);
                setInvestigation(getInvestigation()+3);
                setNature(getNature()+3);
                setReligion(getReligion()+3);
            }
            case 18 -> {
                setArcana(getArcana()+4);
                setHistory(getHistory()+4);
                setInvestigation(getInvestigation()+4);
                setNature(getNature()+4);
                setReligion(getReligion()+4);
            }
            default -> throw new IllegalStateException("Unexpected value: " + test);
        }
    }
    private void calculateWisdomSubstats() {
        int test = statsList.get(4);

        switch (test) {
            case 1 -> {
                setAnimalHandling(getAnimalHandling()-5);
                setInsight(getInsight()-5);
                setMedicine(getMedicine()-5);
                setPerception(getPerception()-5);
                setSurvival(getSurvival()-5);
            }
            case 2, 3 -> {
                setAnimalHandling(getAnimalHandling()-4);
                setInsight(getInsight()-4);
                setMedicine(getMedicine()-4);
                setPerception(getPerception()-4);
                setSurvival(getSurvival()-4);
            }
            case 4, 5 -> {
                setAnimalHandling(getAnimalHandling()-3);
                setInsight(getInsight()-3);
                setMedicine(getMedicine()-3);
                setPerception(getPerception()-3);
                setSurvival(getSurvival()-3);
            }
            case 6, 7 -> {
                setAnimalHandling(getAnimalHandling()-2);
                setInsight(getInsight()-2);
                setMedicine(getMedicine()-2);
                setPerception(getPerception()-2);
                setSurvival(getSurvival()-2);
            }
            case 8, 9 -> {
                setAnimalHandling(getAnimalHandling()-1);
                setInsight(getInsight()-1);
                setMedicine(getMedicine()-1);
                setPerception(getPerception()-1);
                setSurvival(getSurvival()-1);
            }
            case 10, 11 -> {
                setAnimalHandling(getAnimalHandling());
                setInsight(getInsight());
                setMedicine(getMedicine());
                setPerception(getPerception());
                setSurvival(getSurvival());
            }
            case 12, 13 -> {
                setAnimalHandling(getAnimalHandling()+1);
                setInsight(getInsight()+1);
                setMedicine(getMedicine()+1);
                setPerception(getPerception()+1);
                setSurvival(getSurvival()+1);
            }
            case 14, 15 -> {
                setAnimalHandling(getAnimalHandling()+2);
                setInsight(getInsight()+2);
                setMedicine(getMedicine()+2);
                setPerception(getPerception()+2);
                setSurvival(getSurvival()+2);
            }
            case 16, 17 -> {
                setAnimalHandling(getAnimalHandling()+3);
                setInsight(getInsight()+3);
                setMedicine(getMedicine()+3);
                setPerception(getPerception()+3);
                setSurvival(getSurvival()+3);
            }
            case 18 -> {
                setAnimalHandling(getAnimalHandling()+4);
                setInsight(getInsight()+4);
                setMedicine(getMedicine()+4);
                setPerception(getPerception()+4);
                setSurvival(getSurvival()+4);
            }
            default -> throw new IllegalStateException("Unexpected value: " + test);
        }
    }
    private void calculateCharismaSubstats() {
        int test = statsList.get(5);
        switch (test) {
            case 1 -> {
                setDeception(getDeception()-5);
                setIntimidation(getIntimidation()-5);
                setPerformance(getPerformance()-5);
                setPersuasion(getPersuasion()-5);
            }
            case 2, 3 -> {
                setDeception(getDeception()-4);
                setIntimidation(getIntimidation()-4);
                setPerformance(getPerformance()-4);
                setPersuasion(getPersuasion()-4);
            }
            case 4, 5 -> {
                setDeception(getDeception()-3);
                setIntimidation(getIntimidation()-3);
                setPerformance(getPerformance()-3);
                setPersuasion(getPersuasion()-3);
            }
            case 6, 7 -> {
                setDeception(getDeception()-2);
                setIntimidation(getIntimidation()-2);
                setPerformance(getPerformance()-2);
                setPersuasion(getPersuasion()-2);
            }
            case 8, 9 -> {
                setDeception(getDeception()-1);
                setIntimidation(getIntimidation()-1);
                setPerformance(getPerformance()-1);
                setPersuasion(getPersuasion()-1);
            }
            case 10, 11 -> {
                setDeception(getDeception());
                setIntimidation(getIntimidation());
                setPerformance(getPerformance());
                setPersuasion(getPersuasion());
            }
            case 12, 13 -> {
                setDeception(getDeception()+1);
                setIntimidation(getIntimidation()+1);
                setPerformance(getPerformance()+1);
                setPersuasion(getPersuasion()+1);
            }
            case 14, 15 -> {
                setDeception(getDeception()+2);
                setIntimidation(getIntimidation()+2);
                setPerformance(getPerformance()+2);
                setPersuasion(getPersuasion()+2);
            }
            case 16, 17 -> {
                setDeception(getDeception()+3);
                setIntimidation(getIntimidation()+3);
                setPerformance(getPerformance()+3);
                setPersuasion(getPersuasion()+3);
            }
            case 18 -> {
                setDeception(getDeception()+4);
                setIntimidation(getIntimidation()+4);
                setPerformance(getPerformance()+4);
                setPersuasion(getPersuasion()+4);
            }
            default -> throw new IllegalStateException("Unexpected value: " + test);
        }
    }

    private boolean determineIfValidRace(String race) {
        for (Race index: Race.values()) {
            if (index.toString().equals(race)) {
                return true;
            }
        }
        for (SubRace index: SubRace.values()) {
            if (index.toString().equals(race)) {
                return true;
            }
        }
        return false;
    }

    private void calculateSpeed(Race race, SubRace subRace) {
        switch(race) {
            case Elf, Tiefling, Orc, Dragonborn, Human, Halfelf, Halforc -> this.speed = 30;
            case Gnome, Halfling, Dwarf -> this.speed = 25;
            default -> throw new IllegalStateException("Unexpected value: " + race);
        }
        switch(subRace) {
            case EladrinElf, HighElf, WoodElf -> this.speed = 30;
            case DeepGnome, ForestGnome, RockGnome, GrayDwarf, 
                    HillDwarf, MountainDwarf, LightFootHalfling, StoutHalfling -> this.speed = 25;
            default -> throw new IllegalStateException("Unexpected value: " + race);
        }
    }

    private String retrieveSpell(String spellName) {
        return null;
    }
    private String retrieveFeat(String featName) {
        return null;
    }
}
