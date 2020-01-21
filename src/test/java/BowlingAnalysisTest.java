import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BowlingAnalysisTest {
    private static final String WRONG_CSV_FILE = "./src/test/resources/IPL2019Player.csv";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/IdeaProjects/IPL/src/test/resources/WrongCSVFileData.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IPL/src/test/resources/FactsheetMostWkts.csv";

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_ReturnsCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.lodeIPLWicketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnTopBowlingAvg() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLWicketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<CricketDataDAO> list = iplAnalyser.getSortedDatafieldsWise("Bowling", FieldWiseSorting.fields.BOWLING_AVERAGE);
            Assert.assertEquals(166.0, list.get(0).average, 0);
            Assert.assertEquals(0.0, list.get(98).average, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnTopStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLWicketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<CricketDataDAO> list = iplAnalyser.getSortedDatafieldsWise("Bowling", FieldWiseSorting.fields.BOWLING_STRIKE_RATE);
            Assert.assertEquals(120.0, list.get(0).strikeRate, 0);
            Assert.assertEquals(0.0, list.get(98).strikeRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    //Exception test
    @Test
    public void givenWrongIPLCSVFile_shouldReturnException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLRunsData(WRONG_CSV_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongType_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLRunsData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongDelimiter_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLRunsData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongHeader_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.lodeIPLRunsData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }
}
