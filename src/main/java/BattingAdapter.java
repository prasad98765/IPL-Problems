import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BattingAdapter extends DataLoader {
    @Override
    public <E> Map<String, CricketDataDAO> loadIPLRunsPlayerData(String... csvFilePath) throws IPLAnalyserException {
        Map<String, CricketDataDAO> cricketDataDAOMap = super.loadIPLRunsPlayerData(BatsmanData.class, csvFilePath[0]);
        if (csvFilePath.length == 2)
            this.combneData(cricketDataDAOMap, csvFilePath[1]);
        return cricketDataDAOMap;
    }


    private <E> int combneData(Map<String, CricketDataDAO> cricketDataDAOMap, String csvpath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvpath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<BowlingData> playerList1 = icsvBuilder.getCSVFileInList(reader, BowlingData.class);
            StreamSupport.stream(playerList1.spliterator(), false)
                    .filter(Bowlingdata -> cricketDataDAOMap.get(Bowlingdata.player) != null)
                    .forEach(Bowlingdata -> cricketDataDAOMap.get(Bowlingdata.player).bolavg = Bowlingdata.average);
            return cricketDataDAOMap.size();
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.INVALID_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM);
        }
    }
}

