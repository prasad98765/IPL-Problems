import java.util.List;

public class LoadDataFactory {
    public static List<CricketDataDAO> loadCricketData(IPLAnalyser.Player player, String csvFilePath) throws IPLAnalyserException {
        if (player.equals(IPLAnalyser.Player.Batting)) {
            return new DataLoader().loadIPLRunsPlayerData(BatsmanData.class, csvFilePath);
        }
        if (player.equals(IPLAnalyser.Player.Bowling)) {
            return new DataLoader().loadIPLRunsPlayerData(BowlingData.class, csvFilePath);
        } else {
            throw new IPLAnalyserException("INCORRECT_COUNTRY", IPLAnalyserException.ExceptionType.INCORRECT_TYPE);
        }
    }
}