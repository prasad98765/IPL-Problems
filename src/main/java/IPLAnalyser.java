import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.newBufferedReader;

public class IPLAnalyser {
    List<MostRunsCSV> playerList = new ArrayList<>();


    public int loadIPLMostRunsPlayerData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            playerList = icsvBuilder.getCSVFileInList(reader, MostRunsCSV.class);
            return playerList.size();
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
        Collections.sort(playerList, mostRunsCSVComparable);
        return playerList;
    }

    public List<MostRunsCSV> getSortedBattingStrikeRates() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = (o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) > 0) ? -1 : 1;
        Collections.sort(playerList, mostRunsCSVComparable);
        return playerList;
    }

    public List<MostRunsCSV> getSortedMostPlayer6sand4s() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = Comparator.comparingInt(o -> (o.sixes * 6 + (o.fours * 4)));
        Collections.sort(playerList, mostRunsCSVComparable);
        Collections.reverse(playerList);
        return playerList;
    }

    public List<MostRunsCSV> getSortedMostPlayer6sand4sWithStrikeRate() {
        Comparator<MostRunsCSV> mostRunsCSVComparable = Comparator.comparingInt(o -> (o.sixes * 6 + (o.fours * 4)));
        mostRunsCSVComparable = mostRunsCSVComparable.thenComparing((o1, o2) -> ((o1.strikeRate - (o2.strikeRate)) < 0) ? -1 : 1);
        Collections.sort(playerList, mostRunsCSVComparable);
        Collections.reverse(playerList);
        return playerList;
    }
}
