public class CricketDataDAO {
    public int position;
    public String player;
    public int match;
    public int innings;
    public int notOut;
    public int runs;
    public int highestScore;
    public double average;
    public int ballsFaced;
    public double strikeRate;
    public int centuries;
    public int halfcenturies;
    public int fours;
    public int sixes;
    public double over;
    public int wicket;
    public int betsBowlingInning;
    public double econ;
    public int fourWicks;
    public int fiveWicks;

    public CricketDataDAO(BatsmanData batsmanData) {
        this.position = batsmanData.position;
        this.player = batsmanData.player;
        this.match = batsmanData.match;
        this.innings = batsmanData.innings;
        this.notOut = batsmanData.notOut;
        this.runs = batsmanData.runs;
        this.highestScore = batsmanData.highestScore;
        this.average = batsmanData.average;
        this.ballsFaced = batsmanData.ballsFaced;
        this.strikeRate = batsmanData.strikeRate;
        this.centuries = batsmanData.centuries;
        this.halfcenturies = batsmanData.halfcenturies;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
    }

    public CricketDataDAO(BowlingData wicketsData) {
        this.position = wicketsData.position;
        this.player = wicketsData.player;
        this.match = wicketsData.match;
        this.innings = wicketsData.innings;
        this.strikeRate = wicketsData.strickRate;
        this.over = wicketsData.over;
        this.runs = wicketsData.runs;
        this.wicket = wicketsData.wicket;
        this.betsBowlingInning = wicketsData.betsBowlingInning;
        this.average = wicketsData.average;
        this.econ = wicketsData.econ;
        this.fourWicks = wicketsData.fourWicks;
        this.fiveWicks = wicketsData.fiveWicks;
    }

    @Override
    public String toString() {
        return "CricketDataDAO{" +
                "position=" + position +
                ", player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highestScore=" + highestScore +
                ", average=" + average +
                ", ballsFaced=" + ballsFaced +
                ", strikeRate=" + strikeRate +
                ", centuries=" + centuries +
                ", halfcenturies=" + halfcenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", over=" + over +
                ", wicket=" + wicket +
                ", betsBowlingInning=" + betsBowlingInning +
                ", econ=" + econ +
                ", fourWicks=" + fourWicks +
                ", fiveWicks=" + fiveWicks +
                '}';
    }

    public Object getIPLDTO(IPLAnalyser.Player players) {
        if (players.equals(IPLAnalyser.Player.Batting))
            return new BatsmanData(position, player, match, innings, notOut, runs, highestScore, average, ballsFaced, strikeRate, centuries, halfcenturies, fours, sixes);
            return new BowlingData(position, player, match, innings, strikeRate, over, runs, wicket, betsBowlingInning, average, econ, fourWicks, fiveWicks);
    }
}
