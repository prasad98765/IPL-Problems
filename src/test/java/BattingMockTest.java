import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;


public class BattingMockTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "/home/admin1/Desktop/Prasad/IPL-Problems-master/src/test/resources/BattingData.csv";
    @Mock
    TestStaticMethodIPLFactory loadDataFactory;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void givenData_ReturnsBattingSummaryUsingMockito() {
        try {
            Map<String, CricketDataDAO> battingMap = new HashMap<>();
            battingMap.put("abc",new CricketDataDAO());
             IPLAnalyser iplAnalyser = new IPLAnalyser(loadDataFactory,IPLAnalyser.Player.Batting);
            when(loadDataFactory.loadData(IPLAnalyser.Player.Batting,IPL_MOST_RUNS_CSV_FILE_PATH)).thenReturn(battingMap);
            int numOfRecords = iplAnalyser.lodeIPLCricketData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(1, numOfRecords);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

}
