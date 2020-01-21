
import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public class DataLoader {
    List<CricketDataDAO> playerList = new ArrayList<>();

    public<E> List<CricketDataDAO> loadIPLRunsPlayerData(Class<E> cricketCSVClass,String... csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath[0])));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> playerList1 = icsvBuilder.getCSVFileInList(reader, cricketCSVClass);
            if (cricketCSVClass.getName().equals("BatsmanData"))
            StreamSupport.stream(playerList1.spliterator(), false)
                    .map(BatsmanData.class::cast)
                    .forEach(cricketCSV -> playerList.add(new CricketDataDAO(cricketCSV)));
            if (cricketCSVClass.getName().equals("WicketsData"))
                StreamSupport.stream(playerList1.spliterator(), false)
                        .map(WicketsData.class::cast)
                        .forEach(cricketCSV -> playerList.add(new CricketDataDAO(cricketCSV)));
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