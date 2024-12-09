import java.io.*;
import java.nio.file.*;

public class orarendD21YGM {
    public static void main(String[] args) {
        String inputFilePath = "orarend_d21ygm.xml";
        String newExtension = "orarend_d21ygm.out.xml";
        try {
            changeFileExtension(inputFilePath, newExtension);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void changeFileExtension(String inputFilePath, String newFilePath) throws IOException {
        Path inputFile = Paths.get(inputFilePath);
        if (!Files.exists(inputFile)) {
            throw new FileNotFoundException(inputFilePath);
        }

        Path newFile = inputFile.resolveSibling(newFilePath);
        byte[] fileContent = Files.readAllBytes(inputFile);
        Files.write(newFile, fileContent);
    }
}
