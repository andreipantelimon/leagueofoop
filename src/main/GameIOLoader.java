package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fileio.FileSystem;

public final class GameIOLoader {
    private final String mInputPath;
    private static String mOutputPath;


    GameIOLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    GameInput load() {
        ArrayList<String> groundData = new ArrayList<>();
        ArrayList<Triplet<String, Integer, Integer>> playerData = new ArrayList<>();
        ArrayList<String> roundData = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> angelsMap = new HashMap<>();
        int xDim = -1;
        int yDim = -1;
        int playerNumber = -1;
        int roundNumber = -1;

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            xDim = fs.nextInt();
            yDim = fs.nextInt();

            for (int i = 0; i < xDim; i++) {
                groundData.add(fs.nextWord());
            }

            playerNumber = fs.nextInt();

            for (int i = 0; i < playerNumber; i++) {
                // Player data is stored in an external Triplet class.
                playerData.add(new Triplet<>(fs.nextWord(), fs.nextInt(), fs.nextInt()));
            }

            roundNumber = fs.nextInt();

            for (int i = 0; i < roundNumber; i++) {
                roundData.add(fs.nextWord());
            }

            for (int i = 0; i < roundNumber; i++) {
                int tempCount = fs.nextInt();
                ArrayList<String> tempArray = new ArrayList<>();
                for (int j = 0; j < tempCount; j++) {
                    tempArray.add(fs.nextWord());
                }
                angelsMap.put(i, tempArray);
            }

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(xDim, yDim, groundData,
                playerNumber, playerData, roundNumber, roundData, angelsMap);
    }

    public static void write(final String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(mOutputPath, true));
        writer.write(string);
        writer.newLine();
        writer.close();
    }
}
