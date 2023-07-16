package project.anurag.PictoBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
//        ProcessBuilder processBuilder = new ProcessBuilder("ls");
//        Process process = processBuilder.start();


        ProcessBuilder processBuilder = new ProcessBuilder("python3", "main.py", "\"cats wearing glasses images\"");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        }
        System.out.println(process.getInputStream());


    }
}
