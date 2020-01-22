import com.opencsv.bean.CsvBindByName;

public class BatsmanData {

    @CsvBindByName(column = "pos", required = true)
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public int highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int centuries;

    @CsvBindByName(column = "50", required = true)
    public int halfcenturies;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public BatsmanData(int position, String player, int match, int innings, int notOut, int runs, int highestScore, double average, int ballsFaced, double strikeRate, int centuries,int halfcenturies, int fours, int sixes) {
        this.position=position;
        this.player=player;
        this.match=match;
        this.innings=innings;
        this.notOut=notOut;
        this.runs=runs;
        this.highestScore=highestScore;
        this.average=average;
        this.ballsFaced=ballsFaced;
        this.strikeRate=strikeRate;
        this.centuries=centuries;
        this.halfcenturies=halfcenturies;
        this.fours=fours;
        this.sixes=sixes;
    }

    public BatsmanData() {
    }

    @Override
    public String toString() {
        return "BatsmanData{" +
                "position=" + position++ +
                ", player='" + player +
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
                '}';
    }


}
