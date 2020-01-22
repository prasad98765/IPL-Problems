import java.util.Map;

public class BowlingAdapter extends DataLoader {
    @Override
    public <E> Map<String, CricketDataDAO> loadIPLRunsPlayerData(String... csvFilePath) throws IPLAnalyserException {
        Map<String,CricketDataDAO> cricketDataDAOMap=super.loadIPLRunsPlayerData(BowlingData.class, csvFilePath[0]);
        return cricketDataDAOMap;
    }
}
