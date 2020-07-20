package Services;

import dto.LoggerDTO;
import dto.TimeDto;

import java.util.HashMap;
import java.util.List;

public class GererateResultMapServices {

    public void generateMap(HashMap<String, Integer> frequencyMap, HashMap<String, TimeDto> timeMap, List<LoggerDTO> dtoList){
        for(LoggerDTO dto : dtoList){
            String key = dto.getMethod().toString() + "," + dto.getUrl();
            frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);


            Integer resp = dto.getResponseTime();
            if(timeMap.get(key) != null){
                TimeDto time = timeMap.get(key);
                if(resp > time.getMaxTime())
                    time.setMaxTime(resp);

                if(resp < time.getMinTime())
                    time.setMinTime(resp);

                time.setTimeSum(resp + time.getTimeSum());

                time.setCount(time.getCount() + 1);

            } else{
                TimeDto timeDto = new TimeDto();
                timeDto.setMaxTime(resp);
                timeDto.setMinTime(resp);
                timeDto.setTimeSum(resp);
                timeDto.setCount(1);
                timeMap.put(key, timeDto);
            }
        }

    }
}
