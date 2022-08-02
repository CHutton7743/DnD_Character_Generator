package com.Crucible.Forge;

import com.Crucible.Exceptions.PdfExportException;
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
public class PDFExportController {
    @RequestMapping("/file")
    public byte[] getPdf() throws Exception, PdfExportException {
        Path pdfPath = Paths.get("Forge/src/main/resources/templates/5E_CharacterSheet.pdf");
        return Files.readAllBytes(pdfPath);
    }

    @RequestMapping("/Barbarian")
    public byte[] fillPDF() throws IOException {
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

        addBasics(fieldsList);
        addPrimaryStats(fieldsList);
        addSkills(fieldsList);
        addPrimaryStatModifications(fieldsList);
        addSavingThrows(fieldsList);
        addRolePlayTraits(fieldsList);
        addMiscFields(fieldsList);
        addMoney(fieldsList);

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
    public void addPrimaryStats(ArrayList<PDField> fieldsList) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("STR")) {
                field.setValue("15");
            } else if (field.getFullyQualifiedName().equals("DEX")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("CON")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("INT")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("WIS")) {
                field.setValue("1");
            } else if (field.getFullyQualifiedName().equals("CHA")) {
                field.setValue("2");
            } else if (field.getFullyQualifiedName().equals("Speed")) {
                field.setValue("8");
            }
        }
    }

    public void addSkills(ArrayList<PDField> fieldsList) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("Acrobatics")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("Animal")) {
                field.setValue("14");
            } else if (field.getFullyQualifiedName().equals("Athletics")) {
                field.setValue("15");
            } else if (field.getFullyQualifiedName().equals("Deception ")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("History ")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Insight")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Intimidation")) {
                field.setValue("15");
            } else if (field.getFullyQualifiedName().equals("Investigation ")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("Arcana")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("Perception ")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Performance")) {
                field.setValue("1");
            } else if (field.getFullyQualifiedName().equals("Nature")) {
                field.setValue("1");
            } else if (field.getFullyQualifiedName().equals("Medicine")) {
                field.setValue("2");
            } else if (field.getFullyQualifiedName().equals("Religion")) {
                field.setValue("8");
            } else if (field.getFullyQualifiedName().equals("Stealth ")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("Persuasion")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("SleightofHand")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("Survival")) {
                field.setValue("1");
            }
        }
    }
    public void addToInventory(ArrayList<PDField> fieldsList) {
        for (PDField field: fieldsList) {

        }
    }
    public void addSavingThrows(ArrayList<PDField> fieldsList) throws IOException {
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
    public void addBasics(ArrayList<PDField> fieldsList) throws IOException {
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
    public void addRolePlayTraits(ArrayList<PDField> fieldsList) throws IOException {
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
    public void addMiscFields(ArrayList<PDField> fieldsList) throws IOException {
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
    public void addMoney(ArrayList<PDField> fieldsList) throws IOException {
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
    public void addPrimaryStatModifications(ArrayList<PDField> fieldsList) throws IOException {
        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("STRmod")) {
                field.setValue("2");
            } else if (field.getFullyQualifiedName().equals("DEXmod")) {
                field.setValue("8");
            } else if (field.getFullyQualifiedName().equals("CONmod")) {
                field.setValue("9");
            } else if (field.getFullyQualifiedName().equals("INTmod")) {
                field.setValue("3");
            } else if (field.getFullyQualifiedName().equals("WISmod")) {
                field.setValue("4");
            } else if (field.getFullyQualifiedName().equals("CHamod")) {
                field.setValue("1");
            }
        }
    }
}
