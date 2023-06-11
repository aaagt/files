import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static final String SAVE_PATH = "Games/savegames/";

    public static void main(String[] args) {
        openZip(SAVE_PATH + "save.zip", SAVE_PATH).stream()
                .map(Main::openProgress)
                .forEach(System.out::println);
    }

    public static List<String> openZip(String zipPath, String extractToPath) {
        List<String> extractedFiles = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(zipPath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                Path path = Paths.get(extractToPath + entry.getName());
                Files.deleteIfExists(path);
                Files.copy(zis, path);
                extractedFiles.add(extractToPath + entry.getName());
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return extractedFiles;
    }

    public static GameProgress openProgress(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (GameProgress) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
