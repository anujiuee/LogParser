package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dto.LoggerDTO;
import enums.RequestMapping;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class CsvUtil {

    public  List<LoggerDTO> parseCSV(String filePath){
        List<LoggerDTO> inputList = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();;
            List<String[]> records = csvReader.readAll();

            for(String[] record : records){
                LoggerDTO dto = new LoggerDTO();
                dto.setTimestamp(record[0]);

                //url handling
                String finalUrl = new String();
                List<String> urlDelimited = Arrays.asList(record[1].split("/"));
                for(int i=0; i<urlDelimited.size(); i++){
                    if(isNumeric(urlDelimited.get(i))){
                        urlDelimited.set(i, "{id}") ;
                    }

                    finalUrl += urlDelimited.get(i);
                }

                dto.setUrl(StringUtils.join(urlDelimited, "/"));
                dto.setMethod(RequestMapping.valueOf(record[2]));
                dto.setResponseTime(Integer.valueOf(record[3]));
                dto.setResponseCode(Integer.valueOf(record[4]));

                inputList.add(dto);
            }
        } catch (Exception ex){

        }

        return inputList;
    }

    public void writeEntryToFile(String lineItem, Path path){

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(lineItem.getBytes());
        } catch (Exception ex) {

        }
    }

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public  boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
