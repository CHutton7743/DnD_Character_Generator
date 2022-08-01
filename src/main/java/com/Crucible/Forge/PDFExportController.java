package com.Crucible.Forge;


import com.Crucible.Character_Resources.ForgeCharacter;
import com.Crucible.Exceptions.PdfExportException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        for (PDField field: fieldsList) {
            if (field.getFullyQualifiedName().equals("PlayerName")) {
                field.setValue("Codey");
            } else if (field.getFullyQualifiedName().equals("ClassLevel")) {
                field.setValue("Monk 3");
            } else if (field.getFullyQualifiedName().equals("Background")) {
                field.setValue("Drunk");
            } else if (field.getFullyQualifiedName().equals("CharacterName")) {
                field.setValue("John the drunk");
            } else if (field.getFullyQualifiedName().equals("Alignment")) {
                field.setValue("good");
            } else if (field.getFullyQualifiedName().equals("Race")) {
                field.setValue("Dwarf");
            } else if (field.getFullyQualifiedName().equals("XP")) {
                field.setValue("5400");
            } else if (field.getFullyQualifiedName().equals("STR")) {
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
        newCharacter.save("src/main/resources/templates/test.pdf");
        Path pdfPath = Paths.get("src/main/resources/templates/test.pdf");
        return Files.readAllBytes(pdfPath);
    }
    @GetMapping("/ClassTemplate")
    public void getBasicInformation(@RequestParam("characterName") String characterName,
                                    @RequestParam("characterRace") String charactersRace,
                                    @RequestParam("level") int charactersLevel) {
        System.out.println("name: " + characterName + "\n" +
                           "Race: " +charactersRace + "\n" +
                           "Level: " +charactersLevel);

    }
    @PostMapping("/Basics")
    public void postBasicInformation() {

    }
}
