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
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            int numOfRecords = iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnTopBowlingAvg() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise( FieldWiseSorting.fields.BOWLING_AVERAGE);
            Assert.assertEquals(166.0, list.get(0).average, 0);
            Assert.assertEquals(0.0, list.get(98).average, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnTopStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOWLING_STRIKE_RATE);
            Assert.assertEquals(120.0, list.get(0).strickRate, 0);
            Assert.assertEquals(0.0, list.get(98).strickRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnBestEconomy() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOWLING_ECONOMY_RATE);
            Assert.assertEquals(13.5, list.get(0).econ, 0);
            Assert.assertEquals(4.8, list.get(98).econ, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnBestStrikeRateWith5wAnd4w() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOWLING_STRIKE_RATE_WITH_AVERAGE);
            Assert.assertEquals("Krishnappa Gowtham", list.get(98).player);
            Assert.assertEquals("Kagiso Rabada", list.get(0).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnBestAverageWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOWLING_AVERAGE_WITH_STRIKE_RATE);
            Assert.assertEquals("Suresh Raina", list.get(0).player);
            Assert.assertEquals("Krishnappa Gowtham", list.get(98).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_shouldReturnBestWicketsWithBestAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
            List<BowlingData> list = iplAnalyser.getSortedDatafieldsWise(FieldWiseSorting.fields.BOWLING_WICKET_WITH_AVERAGE);
            Assert.assertEquals("Imran Tahir", list.get(0).player);
            Assert.assertEquals("Yusuf Pathan", list.get(98).player);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    //Exception test
    @Test
    public void givenWrongIPLCSVFile_shouldReturnException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongType_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongDelimiter_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIPLBowlingPlayerDataCSVFile_WithWrongHeader_ShouldCustomException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.Player.Bowling);
            iplAnalyser.lodeIPLCricketData(WRONG_CSV_FILE_DATA);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_PROBLEM, e.type);
        }
    }


}
