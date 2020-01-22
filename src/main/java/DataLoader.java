
import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public abstract class DataLoader {
    public abstract  <E> Map<String,CricketDataDAO> loadIPLRunsPlayerData(String... csvFilePath) throws IPLAnalyserException ;

    Map<String,CricketDataDAO> playerList = new HashMap<>();

    public <E> Map<String,CricketDataDAO> loadIPLRunsPlayerData(Class<E> cricketCSVClass, String... csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath[0])));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> playerList1 = icsvBuilder.getCSVFileInList(reader, cricketCSVClass);
            if (cricketCSVClass.getName().equals("BatsmanData"))
                StreamSupport.stream(playerList1.spliterator(), false)
                        .map(BatsmanData.class::cast)
                        .forEach(cricketCSV -> playerList.put(cricketCSV.player, new CricketDataDAO(cricketCSV)));
            if (cricketCSVClass.getName().equals("BowlingData"))
                StreamSupport.stream(playerList1.spliterator(), false)
                        .map(BowlingData.class::cast)
                        .forEach(cricketCSV -> playerList.put(cricketCSV.player, new CricketDataDAO(cricketCSV)));
            return playerList;
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