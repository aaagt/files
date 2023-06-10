import java.io.File;

public class Main {

    public static final String GAMES_PATH = "Games";

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.info(new File("").getAbsolutePath());
        FilesMaker.make(logger, maker -> maker
                .setGamesDirectory(GAMES_PATH)
                .makeDirectoryToGames("/src")
                .makeDirectoryToGames("/src/main")
                .createFileToGames("/src/main/Main.java")
                .createFileToGames("/src/main/Utils.java")
                .makeDirectoryToGames("/src/test")
                .makeDirectoryToGames("/res")
                .makeDirectoryToGames("/res/drawables")
                .makeDirectoryToGames("/res/vectors")
                .makeDirectoryToGames("/res/icons")
                .makeDirectoryToGames("/savegames")
                .makeDirectoryToGames("/temp")
                .createFileToGames("/temp/temp.txt")
                .saveLogToFile("/temp/temp.txt")
        );
    }
}