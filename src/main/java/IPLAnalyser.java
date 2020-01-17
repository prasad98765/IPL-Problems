import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.newBufferedReader;

public class IPLAnalyser {
    List<MostRunsCSV> censusCSVList = new ArrayList<>();


    public int loadIPLMostRunsPlayerData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = icsvBuilder.getCSVFileInList(reader, MostRunsCSV.class);
            return censusCSVList.size();
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


    public List<MostRunsCSV> getSortedBattingAverage() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> ((o1.average - (o2.average)) > 0) ? -1 : 1;
        Collections.sort(censusCSVList, mostRunsCSVComparable);
        return censusCSVList;
    }

    public List<MostRunsCSV> getSortedBattingStrikeRates() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) > 0) ? -1 : 1;
        Collections.sort(censusCSVList, mostRunsCSVComparable);
        return censusCSVList;
    }
}
