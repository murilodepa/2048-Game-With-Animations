/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameControl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Murilo Araujo
 */
public class WriteFile {

    public static int record;

    public WriteFile() throws IOException {
        try {
            FileOutputStream file = new FileOutputStream("SaveFile\\Record.txt");
            PrintWriter printOut = new PrintWriter(file);
            printOut.print("" + record);

            printOut.close();
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error initializing file");
        }
    }

    public static int getRecord() {
        return record;
    }

    public static void setRecord(int record) {
        WriteFile.record = record;
    }
}
