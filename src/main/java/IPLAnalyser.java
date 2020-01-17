import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class IPLAnalyser {
    public int loadIPLMostRunsPlayerData(String csvFilePath) {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<MostRunsCSV> censusCSVList = icsvBuilder.getCSVFileInList(reader, MostRunsCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
