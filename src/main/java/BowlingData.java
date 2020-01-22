import com.opencsv.bean.CsvBindByName;

public class BowlingData {

    @CsvBindByName(column = "pos", required = true)
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double over;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wicket;

    @CsvBindByName(column = "BBI", required = true)
    public int betsBowlingInning;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Econ", required = true)
    public double econ;

    @CsvBindByName(column = "SR", required = true)
    public double strickRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWicks;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWicks;

    public BowlingData(int position, String player, int match, int innings, double strikeRate, double over, int runs, int wicket, int betsBowlingInning, double average, double econ, int fourWicks, int fiveWicks) {
        this.position =position;
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.strickRate = strikeRate;
        this.over = over;
        this.runs = runs;
        this.wicket = wicket;
        this.betsBowlingInning = betsBowlingInning;
        this.average = average;
        this.econ = econ;
        this.fourWicks = fourWicks;
        this.fiveWicks = fiveWicks;
    }

    public BowlingData() {
    }

    @Override
    public String toString() {
        return "BowlingData{" +
                "position=" + position +
                ", player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", over=" + over +
                ", runs=" + runs +
                ", wicket=" + wicket +
                ", betsBowlingInning=" + betsBowlingInning +
                ", average=" + average +
                ", econ=" + econ +
                ", strickRate=" + strickRate +
                ", fourWicks=" + fourWicks +
                ", fiveWicks='" + fiveWicks + '\'' +
                '}';
    }
}
