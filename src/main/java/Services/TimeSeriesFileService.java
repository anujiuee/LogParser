package Services;

import dto.TimeDto;
import utils.CsvUtil;

import java.nio.file.Path;
import java.util.HashMap;

public class TimeSeriesFileService {

    public void generateTimeSeriesFile(HashMap<String, TimeDto> timeMap, Path pathOut){
        CsvUtil util = new CsvUtil();
        for(String key: timeMap.keySet()){
            TimeDto dtoTime = timeMap.get(key);

            float averageTime = ((float)dtoTime.getTimeSum()/(float)dtoTime.getCount());
            String line = key + ","+dtoTime.getMinTime() + "," + dtoTime.getMaxTime() + "," + averageTime + "\n";
            util.writeEntryToFile(line, pathOut);
        }

    }
}
