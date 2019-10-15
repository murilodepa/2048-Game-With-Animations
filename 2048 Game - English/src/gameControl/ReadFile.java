/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameControl;

import static gameControl.WriteFile.setRecord;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Murilo Araujo
 */
public class ReadFile {

    String score;
    int readScore;

    public ReadFile() {
        try {
            FileInputStream file = new FileInputStream("SaveFile\\Record.txt");
            InputStreamReader input = new InputStreamReader(file);
            BufferedReader buffer = new BufferedReader(input);

            score = buffer.readLine();
            readScore = Integer.parseInt(score);

            setRecord(readScore);

        } catch (Exception e) {
            System.out.println("Error initializing file");
        }
    }
}
