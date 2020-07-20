package Services;

import utils.CsvUtil;

import java.nio.file.Path;
import java.util.*;

public class TopFrequencyFileService {

    public void generateTopFrequencyFile(HashMap<String, Integer> frequencyMap, Path pathOut){
        CsvUtil util = new CsvUtil();
        HashMap<String, Integer> resultMap =  sortByValue(frequencyMap);

        int count = 0;
        for(String s: resultMap.keySet()){
            if(count < 5){
                String lineItem = s + "," + resultMap.get(s) + "\n";
                util.writeEntryToFile(lineItem, pathOut);
                count++;
            }
        }
    }

    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
