import java.util.List;
import java.util.Map;

public class TestStaticMethodIPLFactory {

    public Map<String, CricketDataDAO>  loadData(IPLAnalyser.Player player,String... csvFilePath) throws IPLAnalyserException {
            return LoadDataFactory.loadCricketData(player,csvFilePath);
    }
}
