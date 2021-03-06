package libgdx.ui.services.gametypes.types.quizgame;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.resources.FontManager;
import libgdx.resources.dimen.MainDimen;
import libgdx.ui.controls.button.ButtonSize;
import libgdx.ui.controls.button.ButtonSkin;
import libgdx.ui.model.game.GameUser;
import libgdx.ui.screens.game.creator.GameControlsCreatorService;
import libgdx.ui.screens.game.creator.QuestionContainerCreatorService;
import libgdx.ui.screens.game.screens.AbstractGameScreen;

public abstract class QuizQuestionContainerCreatorService extends QuestionContainerCreatorService<QuizGameService> {

    public QuizQuestionContainerCreatorService(GameUser gameUser, AbstractGameScreen abstractGameScreen) {
        super(gameUser, abstractGameScreen);
    }

    @Override
    public Table createQuestionTable() {
        Table questionTable = super.createQuestionTable();
        Image questionImage = gameService.getQuestionImage();
        String questionToBeDisplayed = gameService.getQuestionToBeDisplayed();
        MyWrappedLabelConfigBuilder myWrappedLabelConfigBuilder = new MyWrappedLabelConfigBuilder().setText(questionToBeDisplayed);
        if (questionImage == null) {
            myWrappedLabelConfigBuilder.setFontScale(FontManager.calculateMultiplierStandardFontSize(1.2f));
        }
        myWrappedLabelConfigBuilder.setFontScale(getQuestionFontScale(questionToBeDisplayed,
                myWrappedLabelConfigBuilder.getFontScale(), GameControlsCreatorService.longAnswerButtons(gameService.getAllAnswerOptions())));
        MyWrappedLabel questionLabel = new MyWrappedLabel(myWrappedLabelConfigBuilder.setStyleDependingOnContrast().build());
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        questionContainer.add(questionLabel).padBottom(verticalGeneralMarginDimen).row();
        if (questionImage != null) {
            addQuestionImage(questionImage);
        }
        return questionTable;
    }

    private float getQuestionFontScale(String questionToBeDisplayed, float fontScale, boolean longAnswerButtons) {
        float factor = 1f;
        //if there are long answer buttons, the question fontScale should be smaller
        float increaseFactor = longAnswerButtons ? 0.035f : 0.018f;
        int increaseWordCount = 5;
        for (int standardQuestionLength = 160; standardQuestionLength < 600; standardQuestionLength = standardQuestionLength + increaseWordCount)
            if (questionToBeDisplayed.length() > standardQuestionLength) {
                factor = factor + increaseFactor;
            } else {
                break;
            }
        return fontScale / factor;
    }

    @Override
    public Table createAnswerOptionsTable() {
        return new GameControlsCreatorService().createAnswerOptionsTable(gameService.getAllAnswerOptions(),
                getNrOfAnswersOnRow(),
                getNrOfAnswerRows(),
                new ArrayList<>(getAllAnswerButtons().values()));
    }

    @Override
    public int getNrOfAnswerRows() {
        return 2;
    }

    @Override
    public int getNrOfAnswersOnRow() {
        return 2;
    }

    @Override
    public ButtonSkin correctAnswerSkin() {
        return getCorrectButtonSkin(gameService.getAllAnswerOptions());
    }

    public static ButtonSkin getCorrectButtonSkin(List<String> allAnswerOptions) {
        return GameControlsCreatorService.longAnswerButtons(allAnswerOptions) ? ButtonSkin.LONG_ANSWER_OPTION_CORRECT : ButtonSkin.SQUARE_ANSWER_OPTION_CORRECT;
    }

    @Override
    public ButtonSkin wrongAnswerSkin() {
        return GameControlsCreatorService.longAnswerButtons(gameService.getAllAnswerOptions()) ? ButtonSkin.LONG_ANSWER_OPTION_WRONG : ButtonSkin.SQUARE_ANSWER_OPTION_WRONG;
    }

    @Override
    protected MyButton createAnswerButton(final String answer) {
        ButtonSize buttonSize = ButtonSize.SQUARE_QUIZ_OPTION_ANSWER;
        ButtonSkin buttonSkin = ButtonSkin.SQUARE_ANSWER_OPTION;
        if (GameControlsCreatorService.longAnswerButtons(gameService.getAllAnswerOptions())) {
            buttonSize = ButtonSize.LONG_QUIZ_OPTION_ANSWER;
            buttonSkin = ButtonSkin.LONG_ANSWER_OPTION;
        }
        return new ButtonBuilder().setWrappedText(answer, buttonSize.getWidth() / 1.1f).setFixedButtonSize(buttonSize).setButtonSkin(buttonSkin).build();
    }
}
