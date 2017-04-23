package ohtu;

public class TennisGame {

    private static final String FORTY = "Forty";
    private static final String THIRTY = "Thirty";
    private static final String FIFTEEN = "Fifteen";
    private static final String LOVE = "Love";
    private static final String DEUCE = "Deuce";
    private static final int DEUCE_POINTS = 4;
    private static final String ALL = "-All";

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            player1Score += 1;
        else if (player2Name.equals(playerName))
            player2Score += 1;
    }

    public String getScore() {
        String score = "";
        if (player1Score == player2Score)
            score = getTiedScoreString(player1Score);
        else if (player1Score >= DEUCE_POINTS || player2Score >= DEUCE_POINTS)
            score = getOverplayScoreString(player1Score, player2Score);
        else
            score = getDefaultScoreString(score);
        return score;
    }

    private String getDefaultScoreString(String score) {
        score += addScoreString(player1Score);
        score += "-";
        score += addScoreString(player2Score);
        return score;
    }

    private String addScoreString(int tempScore) {
        switch (tempScore) {
            case 0:
                return LOVE;
            case 1:
                return FIFTEEN;
            case 2:
                return THIRTY;
            default:
                return FORTY;
        }
    }

    private String getTiedScoreString(int score) {
        if (score < DEUCE_POINTS)
            return addScoreString(score) + ALL;
        else
            return DEUCE;
    }

    private String getOverplayScoreString(int score1, int score2) {
        int difference = score1 - score2;
        if (difference > 0) {
            if (difference == 1)
                return "Advantage player1";
            else
                return "Win for player1";
        } else {
            if (difference == -1)
                return "Advantage player2";
            else
                return "Win for player2";
        }
    }
}
