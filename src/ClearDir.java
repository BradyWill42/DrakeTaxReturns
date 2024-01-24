import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class ClearDir {

    public ClearDir(){
    }

    //deletes all files in a directory using recursion
    /**    
     * Deletes all files in a directory given a path using recursion.
     * @return void
     * @throws IOException
     */
    public void deleteDirectoryRecursion(Path path) throws IOException{
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
          try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
            for (Path entry : entries) {
              deleteDirectoryRecursion(entry);
            }
          }
        }
        System.out.println("Deleting file path " + path.toString() + "...");
        try {
          Files.delete(path);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

}
