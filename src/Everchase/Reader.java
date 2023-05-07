package Everchase;

import java.io.*;

public class Reader {
    private final String file;

    public Reader(String path) {
        this.file = path;
    }

    public void writeScore(int score) throws IOException {
        boolean append = true;
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.write(score + ", ");
        }
    }

    public int readScores() throws IOException {

        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            int max = 0;

            while (line != null) {
                String[] tokens = line.split(", ");
                int score;

                for (String token : tokens) {
                    score = Integer.parseInt(token);

                    if (score > max) {
                        max = score;
                    }
                }
                line = reader.readLine();
            }
            return max;
        }
    }
}
