import java.io.*;
import java.nio.file.*;

public class hallgatoD21YGM {
    public static void main(String[] args) {
        String inputFilePath = "hallgatoD21YGM.xml";
        String newExtension = "hallgatoD21YGM.out.xml";
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
