package libgdx.ui.screens.game.creator;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

import libgdx.resources.dimen.MainDimen;
import libgdx.controls.button.MyButton;
import libgdx.utils.ScreenDimensionsManager;

public class GameControlsCreatorService {

    public Table createAnswerOptionsTable(List<String> allAnswerOptions,
                                          int nrOfAnswersOnRow,
                                          int getNrOfAnswerRows,
                                          List<MyButton> allAnswerButtons) {
        GameControlsCreatorService gameControlsCreatorService = new GameControlsCreatorService();
        if (longAnswerButtons(allAnswerOptions)) {
            return gameControlsCreatorService.createLongAnswerOptionsTable(new ArrayList<>(allAnswerButtons));
        } else {
            return gameControlsCreatorService.createSquareAnswerOptionsTable(nrOfAnswersOnRow, getNrOfAnswerRows, new ArrayList<>(allAnswerButtons));
        }
    }

    public static boolean longAnswerButtons(List<String> allAnswerOptions) {
        boolean containsLongAnswer = false;
        for (String option : allAnswerOptions) {
            if (option.length() > 35) {
                containsLongAnswer = true;
                break;
            }
        }
        return containsLongAnswer || allAnswerOptions.size() > 4;
    }

    public Table createSquareAnswerOptionsTable(int nrOfAnswersOnRow,
                                                int getNrOfAnswerRows,
                                                List<MyButton> allAnswerButtons) {
        int answerIndex = 0;
        Table buttonTable = new Table();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        for (int i = getNrOfAnswerRows; i >= 0; i--) {
            Table buttonRow = new Table();
            for (int j = 0; j < nrOfAnswersOnRow; j++) {
                if (answerIndex < allAnswerButtons.size()) {
                    MyButton button = allAnswerButtons.get(answerIndex);
                    buttonRow.add(button).width(button.getWidth()).height(button.getHeight()).padRight(ScreenDimensionsManager.getScreenWidthValue(0.65f));
                    answerIndex++;
                }
            }
            buttonTable.add(buttonRow).padBottom(verticalGeneralMarginDimen / 2).row();
        }
        return buttonTable;
    }

    public Table createLongAnswerOptionsTable(List<MyButton> allAnswerButtons) {
        Table buttonTable = new Table();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        for (MyButton button : allAnswerButtons) {
            buttonTable.add(button).width(button.getWidth()).height(button.getHeight()).padBottom(verticalGeneralMarginDimen / 2).row();
        }
        return buttonTable;
    }
}
