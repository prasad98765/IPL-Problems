import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class BowlingMockTest {
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "/home/admin1/Desktop/Prasad/IPL-Problems-master/src/test/resources/IPL2019FactsheetMostWkts.csv";
        @Mock
        TestStaticMethodIPLFactory loadDataFactory;

        @Rule
        public MockitoRule mockitoRule = MockitoJUnit.rule();

        @Test
        public void givenData_ReturnsBowlingSummaryUsingMockito() {
            try {
                Map<String, CricketDataDAO> bowlingMap = new HashMap<>();
                bowlingMap.put("abc",new CricketDataDAO());
                IPLAnalyser iplAnalyser = new IPLAnalyser(loadDataFactory,IPLAnalyser.Player.Bowling);
                when(loadDataFactory.loadData(IPLAnalyser.Player.Bowling,IPL_MOST_WKTS_CSV_FILE_PATH)).thenReturn(bowlingMap);
                int numOfRecords = iplAnalyser.lodeIPLCricketData(IPL_MOST_WKTS_CSV_FILE_PATH);
                Assert.assertEquals(1, numOfRecords);
            } catch (IPLAnalyserException e) {
                e.printStackTrace();
            }
        }

    }
