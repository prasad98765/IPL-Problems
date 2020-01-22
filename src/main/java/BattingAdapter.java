import java.util.Map;

public class BattingAdapter extends DataLoader {
    @Override
    public <E> Map<String, CricketDataDAO> loadIPLRunsPlayerData(String... csvFilePath) throws IPLAnalyserException {
        Map<String,CricketDataDAO> cricketDataDAOMap=super.loadIPLRunsPlayerData(BatsmanData.class, csvFilePath[0]);
        return cricketDataDAOMap;
    }
}
