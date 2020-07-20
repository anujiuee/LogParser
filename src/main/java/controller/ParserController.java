package controller;

import Services.GererateResultMapServices;
import Services.TimeSeriesFileService;
import Services.TopFrequencyFileService;
import dto.LoggerDTO;
import dto.TimeDto;
import utils.CsvUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParserController {


    public static void main(String[] args) {

        String path = "C:/Users/anujk/Downloads/parser.csv";
        Path pathOutTimeSeries = Paths.get("C:/Users/anujk/Downloads/timeSeries.csv");
        Path pathOutTopFive = Paths.get("C:/Users/anujk/Downloads/topFive.csv");

        CsvUtil util = new CsvUtil();
        List<LoggerDTO> dtoList = util.parseCSV(path);

        HashMap<String, Integer> frequencyMap = new LinkedHashMap<>();
        HashMap<String, TimeDto> timeMap = new HashMap<>();

        GererateResultMapServices gererateResultMapServices = new GererateResultMapServices();
        gererateResultMapServices.generateMap(frequencyMap,timeMap,dtoList);



        //Generate Time Series Data
        TimeSeriesFileService ts = new TimeSeriesFileService();
        ts.generateTimeSeriesFile(timeMap, pathOutTimeSeries);

        //Genearate Top Frequency File
        TopFrequencyFileService gf = new TopFrequencyFileService();
        gf.generateTopFrequencyFile(frequencyMap, pathOutTopFive);


    }



}
