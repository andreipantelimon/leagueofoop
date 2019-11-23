package com.leagueofoop.main;

import java.util.ArrayList;

import fileio.FileSystem;

final class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    GameInput load() {
        ArrayList<String> groundData = new ArrayList<>();
        ArrayList<String> playerData = new ArrayList<>();
        ArrayList<String> roundData = new ArrayList<>();
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
                playerData.add(fs.nextWord() + fs.nextInt() + fs.nextInt());
            }

            roundNumber = fs.nextInt();

            for (int i = 0; i < roundNumber; i++) {
                roundData.add(fs.nextWord());
            }

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(xDim, yDim, groundData, playerNumber, playerData, roundNumber, roundData);
    }
}
