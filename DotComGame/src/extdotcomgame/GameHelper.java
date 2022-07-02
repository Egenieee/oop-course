package extdotcomgame;

import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {

        }
        return inputLine;
    }

    private Random generator = new Random(10);

    public String getUserInputRandom(String prompt) {
        String inputLine = null;
        int row = (int) (generator.nextDouble() * gridLength);
        int col = (int) (generator.nextDouble() * gridLength);

        String temp = String.valueOf(alphabet.charAt(row));
        temp = temp.concat(Integer.toString(col));
        return temp;
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        comCount++;

        int incr = 1;
        if ((comCount % 2) == 1) {
            incr = gridLength;
        }
        while (!success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            int x = 0;
            success = true;

            while (success && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;

                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else {
                    success = false;
                }

            }
        }

        int x = 0;
        while (x < comSize) {
            grid[coords[x]] = 1;
            int row = (int) (coords[x] / gridLength);
            int column = coords[x] % gridLength;
            String temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }

        return alphaCells;
    }

}
