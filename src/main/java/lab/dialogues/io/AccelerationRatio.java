/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.Turn;

/**
 *
 */
public class AccelerationRatio {

    public static void accelerateRatio(String jsonFile) throws IOException {

        List<Dial> dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
        });

        int totalTurns = 0;
        int numGoals = 0;
        int numShoots = 0;
        int systemInformShoots = 0;
        int totalGeneratedInform = 0;

        int numDialogues = dials.size();

        for (Dial dial : dials) {
            List<Turn> turns = dial.getTurns();

            totalTurns += turns.size();

            int localshoots = 0;
            int localgoals = 0;
            int localinform = 0;

            for (int turnid = 0; turnid < turns.size(); turnid++) {
                Turn turn = turns.get(turnid);
                String userDact = turn.getUser().getDact();
                String systemDact = turn.getSystem().getDact();

                if (userDact.contains("inform(") || userDact.contains("request(")) {
                    numGoals++;
                    localgoals++;
                }
                if (!systemDact.isEmpty()) {

                    numShoots++;
                    localshoots++;
                }

                if (systemDact.contains("inform(")) {
                    systemInformShoots++;
                    List<String[]> systemActList = lab.dialogues.io.KeyWords.getAct(systemDact);
                    localinform += systemActList.size();

                }

            }

            totalGeneratedInform += localinform * 2;

        }

        int totalShoots = numGoals + numShoots;

        double accelerate = accelerateRatio(numGoals, totalShoots);

        System.out.println("#. total turns: " + totalTurns);
        System.out.println("#. total system shoots: " + numShoots);
        System.out.println("#. total goals: " + numGoals);
        System.out.println("total shoots: " + totalShoots);

        System.out.println("------------------");

        double average_shoots = (double) totalShoots / numDialogues;
        double average_goals = (double) numGoals / numDialogues;

        double average_accelerate = accelerateRatio(average_goals, average_shoots);
        System.out.println("average: shoots, goals/dialogue");
        System.out.println("shoots: " + average_shoots);
        System.out.println("goals: " + average_goals);
        System.out.println("accelerate: " + average_accelerate);
        System.out.println("");

        System.out.println("-------------------------------------");
        System.out.println("generated");

        int generatedShoots = 18_800;
        numShoots = totalGeneratedInform;
        totalShoots = numGoals + numShoots;
        System.out.println("generated from inform: " + totalGeneratedInform);
        average_shoots = (double) totalShoots / numDialogues;
        average_goals = (double) numGoals / numDialogues;

        average_accelerate = accelerateRatio(average_goals, average_shoots);
        System.out.println("average: shoots, goals/dialogue");
        System.out.println("shoots: " + average_shoots);
        System.out.println("goals: " + average_goals);
        System.out.println("accelerate: " + average_accelerate);

    }

    public static double accelerateRatio(int goals, int shoots) {

        double accelerate = 1;

        if (shoots != 0) {

            return Math.sqrt(goals) / shoots;

        }

        return accelerate;

    }

    public static double accelerateRatio(double goals, double shoots) {

        double accelerate = 1;

        if (shoots != 0) {

            return Math.sqrt(goals) / shoots;

        }

        return accelerate;

    }

}
