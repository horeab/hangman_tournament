package libgdx.ui.services.gametypes.types.quizgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import libgdx.ui.model.game.GameQuestionInfo;
import libgdx.ui.model.game.GameUser;
import libgdx.ui.model.game.question.Question;
import libgdx.ui.services.game.gameservice.GameService;

public abstract class QuizGameService extends GameService {

    public abstract List<String> getAnswers();

    public abstract List<String> getAllAnswerOptions();

    public QuizGameService(Question question) {
        super(question);
    }

    @Override
    public boolean isAnswerCorrectInQuestion(String answer) {
        return compareAnswerStrings(answer, getAnswers().get(0));
    }

    @Override
    public List<String> getUnpressedCorrectAnswers(Set<String> answersIds) {
        List<String> answers = new ArrayList<>(getAnswers());
        answers.removeAll(answersIds);
        return answers;
    }

    @Override
    public int getImageToBeDisplayedPositionInString() {
        return 4;
    }

    @Override
    protected int getQuestionToBeDisplayedPositionInString() {
        return 1;
    }

    @Override
    public int getNrOfWrongAnswersPressed(Set<String> answersIds) {
        return 0;
    }

    @Override
    protected int getSimulatePressedLetterCorrectAnswerFactor() {
        //[0,100) IF number is small, more correct answers will be found
        return 40;
    }

    @Override
    protected List<Long> getFastIntervalsToPressAnswer() {
        return Arrays.asList(4500L, 5000L, 5500L, 6000L);
    }

    @Override
    protected List<Long> getSlowIntervalsToPressAnswer() {
        return getFastIntervalsToPressAnswer();
    }

    @Override
    public float getPercentOfCorrectAnswersPressed(Set<String> answersIds) {
        return 1f;
    }

    @Override
    public boolean isGameFinishedSuccessful(Set<String> answersIds) {
        return !answersIds.isEmpty() && isAnswerCorrectInQuestion(answersIds.iterator().next());
    }

    @Override
    public boolean isGameFinishedFailed(Set<String> answersIds) {
        return !answersIds.isEmpty() && !isAnswerCorrectInQuestion(answersIds.iterator().next());
    }
}
