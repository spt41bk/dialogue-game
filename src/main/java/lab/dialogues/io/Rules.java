/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Rules {

    public static String randomRule(List<String> rulesList) {

        Random ran = new Random();
        int x = ran.nextInt(rulesList.size());
        return rulesList.get(x);

    }

    public static List<String> readRules(String rulesFile, String startkey, String endkey) {
        List<String> rulesList = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(rulesFile)), "utf-8"));
            String line;
            boolean ok = false;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                } else {

                    if (line.startsWith(endkey)) {
                        ok = false;
                    }

                    if (ok) {
                        rulesList.add(line.trim());
                    }

                    if (line.startsWith(startkey)) {
                        ok = true;
                    }

                }
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Rules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rulesList;

    }
}
