package org.example.input;

import java.io.FileWriter;
import java.io.IOException;

import org.example.models.Board;

/**
 * Intereact with file.
 */

public class File {
    /**
     * Write map out to a file.
     */

    public void filewrite(Board b, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(String.valueOf(b.getH()) + " " + String.valueOf(b.getW()) + "\n");
            for (int i = 0; i < b.getH(); i++) {
                for (int j = 0; j < b.getW(); j++) {
                    myWriter.write(String.valueOf(b.geti(i, j)) + " ");
                }
                myWriter.write("\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");

        }

    }


}