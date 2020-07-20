package services;

import Services.GererateResultMapServices;
import dto.LoggerDTO;
import dto.TimeDto;
import enums.RequestMapping;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;


public class GenerateMapTest {

    @Test
    public void testGererateResultMapServices(){
        GererateResultMapServices gererateResultMapServices = new GererateResultMapServices();

        List<LoggerDTO> inputList = new ArrayList<>();
        LoggerDTO loggerDTO = new LoggerDTO("123","abcd.com", RequestMapping.PUT,25,200);
        inputList.add(loggerDTO);


        HashMap<String, Integer> frequencyMap = new LinkedHashMap<>();
        HashMap<String, TimeDto> timeMap = new HashMap<>();
        gererateResultMapServices.generateMap(frequencyMap,timeMap,inputList);

        int a = frequencyMap.get("PUT,abcd.com");

        assertEquals(1,a);

        int b = timeMap.get("PUT,abcd.com").getMinTime();

        assertEquals(25,b);

    }


    @Test
    public void testGererateResultMapServicesForFailed(){
        GererateResultMapServices gererateResultMapServices = new GererateResultMapServices();

        List<LoggerDTO> inputList = new ArrayList<>();
        LoggerDTO loggerDTO = new LoggerDTO("123","abcd.com", RequestMapping.PUT,25,200);
        inputList.add(loggerDTO);


        HashMap<String, Integer> frequencyMap = new LinkedHashMap<>();
        HashMap<String, TimeDto> timeMap = new HashMap<>();
        gererateResultMapServices.generateMap(frequencyMap,timeMap,inputList);

        int a = frequencyMap.get("PUT,abcd.com");

        assertEquals(1,a);

        int b = timeMap.get("PUT,abcd.com").getMinTime();

        assertNotSame(26,b);

    }
}
