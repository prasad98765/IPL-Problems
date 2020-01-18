import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class DataLoader {
    List<MostRunsCSV> playerList = new ArrayList<>();

    public List loadIPLMostRunsPlayerData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            playerList = icsvBuilder.getCSVFileInList(reader, MostRunsCSV.class);
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
