public class Score implements Comparable<Score> {
    private Player player;
    private int score;
    private String game;

    public Score(Player player, int score, String game) {
        this.player = player;
        this.score = score;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public String getGame() {
        return game;
    }

    @Override
    public int compareTo(Score score1) {
        if(score < score1.getScore())
            return -1;
        if (score > score1.getScore())
            return 1;
        else
            return 0;
    }

}
