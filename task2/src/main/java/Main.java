import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static final String SAVE_PATH = "Games/savegames/";

    public static void main(String[] args) {
        // Создать три экземпляра класса GameProgress
        var gamedata = List.of(
                new GameProgress(100, 50, 1, 50.5),
                new GameProgress(100, 50, 2, 50.5),
                new GameProgress(100, 50, 3, 50.5)
        );

        // Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        List<String> filenames = new ArrayList<>();
        for (int i = 0; i < gamedata.size(); i++) {
            String name = SAVE_PATH + "game" + (i + 1) + ".dat";

            try (FileOutputStream baos = new FileOutputStream(name);
                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {

                oos.writeObject(gamedata.get(i));

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            filenames.add(name);
        }

        // Созданные файлы сохранений из папки savegames запаковать в архив zip.
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(SAVE_PATH + "save.zip"))) {
            for (int i = 0; i < filenames.size(); i++) {
                String name = filenames.get(i);
                try (FileInputStream fis = new FileInputStream(name)) {

                    ZipEntry entry = new ZipEntry(name);
                    zout.putNextEntry(entry);

                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    // добавляем содержимое к архиву
                    zout.write(buffer);

                    // закрываем текущую запись для новой записи
                    zout.closeEntry();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Удалить файлы сохранений, лежащие вне архива.
        for (int i = 0; i < filenames.size(); i++) {
            String name = filenames.get(i);
            File file = new File(name);
            if (file.delete())
                System.out.println("Файл удален");
        }
    }
}