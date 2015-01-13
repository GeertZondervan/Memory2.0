import java.util.Collections;
import java.util.LinkedList;

public class Player {
    private String name, difficulty;
    private Score scoreLastGame;
    private LinkedList<Score> highScore = new LinkedList<>();

    public Player(String name) {
        this.name = name;
    }

    public void setScore(Score score) {
        scoreLastGame = score;
        highScore.add(score);
        Collections.sort(highScore);
        if (highScore.size() > 10)
            highScore.removeLast();
    }

    public Score getScoreLastGame() {
        return scoreLastGame;
    }

    public LinkedList<Score> getHighScore() {
        return highScore;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    // om in de combobox weer te geven
    @Override
    public String toString() {
        return name;
    }
}