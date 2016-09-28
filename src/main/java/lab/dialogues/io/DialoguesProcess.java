/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.Dialogues;

/**
 *
 */
public class DialoguesProcess {

    public static void statistics(String jsonFile, String statisticsFile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(statisticsFile)), "utf-8"));

        List<Dial> dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
        });

        writer.write("Dialogue System: Statistics\n\n");
        writer.write(String.format("File: %s\n", jsonFile));
        writer.write(String.format("# dialogues: %d\n\n", dials.size()));

        Map<Integer, Integer> countTurnMap = new HashMap<>();

        int totalturns = 0;

        for (Dial dial : dials) {

            int turnsize = dial.getTurns().size();

            totalturns += turnsize;

            int count = 1;
            if (countTurnMap.containsKey(turnsize)) {
                count += countTurnMap.get(turnsize);
            }
            countTurnMap.put(turnsize, count);

        }

        writer.write("Turns:\n");
        for (int turnsize : countTurnMap.keySet()) {
            writer.write(String.format("\tturn-size: %d\tfrequency: %d\n", turnsize, countTurnMap.get(turnsize)));
        }
        writer.write("\n");
        writer.write(String.format("\t# total turns: %d\n", totalturns));
        writer.write(String.format("\t# average turns/dialogue: %f\n", (double) totalturns / dials.size()));

        writer.close();

    }

    public static void readInputFile(String inFile, String xmlFile) throws IOException {

        Dialogues dialogues = new Dialogues();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inFile)), "utf-8"));
        String line;

        List<Dial> dialList = new ArrayList<>();

        Dial dial = new Dial();
        int count1 = 0;
        int count2 = 0;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("\"dial\": [")) {
                dial = new Dial();
                dialList.add(dial);
                count1++;

            } else if (line.startsWith("\"turn\":")) {

                String turn_id = line.substring(8, line.length() - 1);
            } else if (line.startsWith("\"id\":")) {
                String id = line.substring(7, line.length() - 1);
                dial.setId(id);

                count2++;
            }

        }

        reader.close();

        dialogues.setDials(dialList);

        System.out.println("");
        System.out.println(count1);
        System.out.println(count2);

    }

}
