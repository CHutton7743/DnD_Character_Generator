package com.Crucible.Forge.Controllers;

import com.Crucible.Forge.Character_Resources.BaseStats;
import com.Crucible.Forge.Entities_and_Repositories.Character;
import com.Crucible.Forge.Exceptions.PdfExportException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@RestController
@RequestMapping("file")
public class PDFExportController {
    @RequestMapping("/export")
    public byte[] getPdf() throws Exception, PdfExportException {
        Path pdfPath = Paths.get("Forge/src/main/resources/templates/5E_CharacterSheet.pdf");
        return Files.readAllBytes(pdfPath);
    }

    @RequestMapping("/Forge")
    public byte[] fillPDF(Character character) throws IOException {
        File file = new File("src/main/resources/templates/5E_CharacterSheet.pdf");
        PDDocument newCharacter = PDDocument.load(file);

        PDDocumentCatalog docCatalog = newCharacter.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();

        Iterator<PDField> iterator = acroForm.getFieldIterator();
        ArrayList<PDField> fieldsList = new ArrayList<>();

        while(iterator.hasNext()) {
           fieldsList.add(iterator.next());
        }
        for(PDField field : fieldsList) {
            System.out.println(field);
        }

        addBasics(fieldsList, character);
        addPrimaryStats(fieldsList, character);
        addSkills(fieldsList, character);
        addPrimaryStatModifications(fieldsList, character);
        addSavingThrows(fieldsList, character);
        addRolePlayTraits(fieldsList, character);
        addMiscFields(fieldsList, character);
        addMoney(fieldsList, character);

        newCharacter.save("src/main/resources/templates/test.pdf");
        Path pdfPath = Paths.get("src/main/resources/templates/test.pdf");
        return Files.readAllBytes(pdfPath);
    }
    @GetMapping("/ClassTemplate")
    public void getBasicInformation(HttpServletRequest characterInformation) {
        HashMap<String, String> basics = new HashMap<>();

//        System.out.println("name: "  + characterInformation.characterName + "\n" +
//                           "Race: "  +charactersRace + "\n" +
//                           "Level: " +charactersLevel);

    }
    @PostMapping("/Basics")
    public void postBasicInformation() {

    }
    public void addWeapon(ArrayList<PDField> fieldsList) {
        for (PDField field: fieldsList) {

        }
    }
    public void addPrimaryStats(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("STR")) {
                field.setValue(String.valueOf(character.getStrength()));
            } else if (field.getFullyQualifiedName().equals("DEX")) {
                field.setValue(String.valueOf(character.getDexterity()));
            } else if (field.getFullyQualifiedName().equals("CON")) {
                field.setValue(String.valueOf(character.getConstitution()));
            } else if (field.getFullyQualifiedName().equals("INT")) {
                field.setValue(String.valueOf(character.getIntelligence()));
            } else if (field.getFullyQualifiedName().equals("WIS")) {
                field.setValue(String.valueOf(character.getWisdom()));
            } else if (field.getFullyQualifiedName().equals("CHA")) {
                field.setValue(String.valueOf(character.getCharisma()));
            } else if (field.getFullyQualifiedName().equals("Speed")) {
                field.setValue(String.valueOf(character.getSpeed()));
            }
        }
    }

    public void addSkills(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("Acrobatics")) {
                field.setValue(String.valueOf(character.getAcrobatics()));
            } else if (field.getFullyQualifiedName().equals("Animal")) {
                field.setValue(String.valueOf(character.getAnimalHandling()));
            } else if (field.getFullyQualifiedName().equals("Athletics")) {
                field.setValue(String.valueOf(character.getAthletics()));
            } else if (field.getFullyQualifiedName().equals("Deception ")) {
                field.setValue(String.valueOf(character.getDeception()));
            } else if (field.getFullyQualifiedName().equals("History ")) {
                field.setValue(String.valueOf(character.getHistory()));
            } else if (field.getFullyQualifiedName().equals("Insight")) {
                field.setValue(String.valueOf(character.getInsight()));
            } else if (field.getFullyQualifiedName().equals("Intimidation")) {
                field.setValue(String.valueOf(character.getIntimidation()));
            } else if (field.getFullyQualifiedName().equals("Investigation ")) {
                field.setValue(String.valueOf(character.getInvestigation()));
            } else if (field.getFullyQualifiedName().equals("Arcana")) {
                field.setValue(String.valueOf(character.getArcana()));
            } else if (field.getFullyQualifiedName().equals("Perception ")) {
                field.setValue(String.valueOf(character.getPerception()));
            } else if (field.getFullyQualifiedName().equals("Performance")) {
                field.setValue(String.valueOf(character.getPerformance()));
            } else if (field.getFullyQualifiedName().equals("Nature")) {
                field.setValue(String.valueOf(character.getNature()));
            } else if (field.getFullyQualifiedName().equals("Medicine")) {
                field.setValue(String.valueOf(character.getMedicine()));
            } else if (field.getFullyQualifiedName().equals("Religion")) {
                field.setValue(String.valueOf(character.getReligion()));
            } else if (field.getFullyQualifiedName().equals("Stealth ")) {
                field.setValue(String.valueOf(character.getStealth()));
            } else if (field.getFullyQualifiedName().equals("Persuasion")) {
                field.setValue(String.valueOf(character.getPersuasion()));
            } else if (field.getFullyQualifiedName().equals("SleightofHand")) {
                field.setValue(String.valueOf(character.getSleightOfHand()));
            } else if (field.getFullyQualifiedName().equals("Survival")) {
                field.setValue(String.valueOf(character.getSurvival()));
            }
        }
    }
    public void addToInventory(ArrayList<PDField> fieldsList) {
        for (PDField field: fieldsList) {

        }
    }
    public void addSavingThrows(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("ST Strength")) {
                field.setValue("2");
            } else if (field.getFullyQualifiedName().equals("ST Dexterity")) {
                field.setValue("8");
            } else if (field.getFullyQualifiedName().equals("ST Constitution")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("ST Intelligence")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("ST Wisdom")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("ST Charisma")) {
                field.setValue("1");
            }
        }
    }
    public void addBasics(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("PlayerName")) {
                field.setValue("Codey");
            } else if (field.getFullyQualifiedName().equals("ClassLevel")) {
                field.setValue("Monk 3");
            } else if (field.getFullyQualifiedName().equals("Background")) {
                field.setValue("Drunk");
            } else if (field.getFullyQualifiedName().equals("CharacterName")) {
                field.setValue("John the drunk");
            } else if (field.getFullyQualifiedName().equals("CharacterName 2")) {
                field.setValue("John the drunk");
            } else if (field.getFullyQualifiedName().equals("Alignment")) {
                field.setValue("good");
            } else if (field.getFullyQualifiedName().equals("Race ")) {
                field.setValue("Dwarf");
            } else if (field.getFullyQualifiedName().equals("XP")) {
                field.setValue("0");
            }
        }
    }
    public void addRolePlayTraits(ArrayList<PDField> fieldsList, Character character) throws IOException {
        // role play traits
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("Age")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("Height")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("Weight")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Eyes")) {
                field.setValue("1");
            } else if (field.getFullyQualifiedName().equals("Skin")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("Hair")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("Allies")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("FactionName")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("Backstory")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Treasure")) {
                field.setValue("1");
            }
        }
    }
    public void addMiscFields(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("AC")) {
                field.setValue("2");
            } else if (field.getFullyQualifiedName().equals("HPMax")) {
                field.setValue("8");
            }
            //bonds, flaws, ideals, personality traits,
            else if (field.getFullyQualifiedName().equals("Bonds")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("Flaws")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("PersonalityTraits ")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Ideals")) {
                field.setValue("1");
            }
            // languages
            else if (field.getFullyQualifiedName().equals("ProficienciesLang")) {
                field.setValue("1");
            }
            else if (field.getFullyQualifiedName().equals("Features and Traits")) {
                field.setValue("This is the way, 42 is the answer to life love the universe and everything.");
            }
        }
    }
    public void addMoney(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("SP")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("EP")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("GP")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("PP")) {
                field.setValue("1");
            }
        }
    }
    public void addPrimaryStatModifications(ArrayList<PDField> fieldsList, Character character) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("STRmod")) {
                    field.setValue(String.valueOf(calculateBaseStatsModifier(character.getStrength())));
            } else if (field.getFullyQualifiedName().equals("DEXmod")) {
                field.setValue(String.valueOf(calculateBaseStatsModifier(character.getDexterity())));
            } else if (field.getFullyQualifiedName().equals("CONmod")) {
                field.setValue(String.valueOf(calculateBaseStatsModifier(character.getConstitution())));
            } else if (field.getFullyQualifiedName().equals("INTmod")) {
                field.setValue(String.valueOf(calculateBaseStatsModifier(character.getIntelligence())));
            } else if (field.getFullyQualifiedName().equals("WISmod")) {
                field.setValue(String.valueOf(calculateBaseStatsModifier(character.getWisdom())));
            } else if (field.getFullyQualifiedName().equals("CHamod")) {
                field.setValue(String.valueOf(calculateBaseStatsModifier(character.getCharisma())));
            }
        }
    }
    public int calculateBaseStatsModifier(int stat) {
        switch (stat) {
            case 0, 1 -> {return -5;}
            case 2, 3 -> {return -4;}
            case 4, 5 -> {return -3;}
            case 6, 7 -> {return -2;}
            case 8, 9 -> {return -1;}
            case 10, 11 -> {return 0;}
            case 12, 13 -> {return 1;}
            case 14, 15 -> {return 2;}
            case 16, 17 -> {return 3;}
            case 18 -> {return 4;}
            case 20 -> {return 5;}
            default -> throw new IllegalStateException("Unexpected value: " + stat);
        }
    }
}
