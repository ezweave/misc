package malmal;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Generate a file of randomly generated "preferred colors" for a given user,
 * ordered by color.
 *
 */
public class Mal {

  static final Logger log = Logger.getLogger(Mal.class.getName());

  public enum Color {

    RED,
    GREEN,
    BLUE,
    AQUA,
    PINK,
    PURPLE,
    TAUPE,
    MAUVE,
    MAGENTA,
    CYAN,
    BLACK,
    WHITE,
    GOLD,
    SILVER,
    SAND,
    EGG_SHELL,
    OFF_WHITE,
    MIST
  }

  public static Color[] getRandomFavoriteColors() {

    Color[] colors = new Color[Color.values().length];

    for (int i = 0; i < colors.length; i++) {

      boolean matchFound = true;

      while (matchFound) {
        Random dum = new Random();
        int order = dum.nextInt(Color.values().length);

        Color aColor = Color.values()[i];

        matchFound = false;
        
        for (int j = 0; j < colors.length; j++) {
          Color anotherColor = colors[j];

          if (anotherColor == aColor) {
            matchFound = true;
            break;
          } 
        }

        if(!matchFound) {
          colors[order] = aColor;
        }
      }
    }


    MalDatum myDatum = new MalDatum();

    for(int i = 0; i < colors.length; i++) {
      myDatum.addColor(colors[i]);
    }

    myDatum.setFileName("my_colors.txt");

    return colors;
  }

  public static class MalDatum {

    public String fileName;
    public File file;
    public FileWriter writer;

    public void addColor(Color color) {
      try {
        writer.write("I like: " + color);
      } catch (Throwable t) {
      }
    }

    public void setFileName(String fileName) {
      if (file == null) {
        file = new File(fileName);
        try {
          writer = new FileWriter(file);
        } catch (Throwable t) {
        } finally {
          log.info("Wrote the file!");
        }
      }
    }

    @Override
    public void finalize() {
      try {
        super.finalize();
        writer.close();
      } catch (Throwable t) {
      }
    }
  }
}
