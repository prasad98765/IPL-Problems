import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPLAnalysisTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019MostRunsPlayer.csv";
    private static final String WRONG_CSV_FILE = "./src/test/resources/IPL2019Player.csv";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/IdeaProjects/IPL/src/test/resources/WrongCSVFileData.csv";

    @Test
    public void givenIPLRunsPlayerDataCSVFile_ReturnsCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopBattingAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<MostRunsCSV> list = iplAnalyser.getSortedBattingAverage();
            Assert.assertEquals(83.2, list.get(0).average, 0);
            Assert.assertEquals(0.0, list.get(99).average, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongIPLCSVFile_shouldReturnException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(WRONG_CSV_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongType_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongDelimiter_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_WithWrongHeader_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopBattingStrikeRates() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<MostRunsCSV> list = iplAnalyser.getSortedBattingStrikeRates();
            Assert.assertEquals(333.33, list.get(0).strikeRate, 0);
            Assert.assertEquals(63.15, list.get(99).strikeRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopPlayer6sAnd4s() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<MostRunsCSV> list = iplAnalyser.getSortedMostPlayer6sand4s();
            Assert.assertEquals("Andre Russell", list.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsPlayerDataCSVFile_shouldReturnTopPlayer6sAnd4sWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLMostRunsPlayerData(IPL_MOST_RUNS_CSV_FILE_PATH);
            List<MostRunsCSV> list = iplAnalyser.getSortedMostPlayer6sand4sWithStrikeRate();
            Assert.assertEquals("Andre Russell", list.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", list.get(99).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}
