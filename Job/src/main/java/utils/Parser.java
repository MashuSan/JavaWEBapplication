package utils;

import objects.Country;
import utils.MyReader;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

/**
 * @author Matus Valko
 */
public class Parser {
    private MyReader reader;
    private JSONObject json;
    private static final int VALUE_OFF_SET = 12;
    private static final int LAST_COUNTRY_INDEX = 33;
    private List<Country> countries;

    public Parser() throws Exception {
        reader = new MyReader();
        json = reader.convert();
        countries = new ArrayList<Country>();
        parseInput();
    }

    private String getStartYear(){
        return json.getJSONObject("dimension").getJSONObject("year").get("label").toString().split("-")[0];
    }

    private String getEndYear(){
        return json.getJSONObject("dimension").getJSONObject("year").get("label").toString().split("-")[1];
    }

    private Map<String, Object> getShortNames(){
        return json.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("index").toMap();
    }

    private Map<String, Object> getLongNames(){
        return json.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("label").toMap();
    }

    private List getValues(){
        return json.getJSONArray("value").toList();
    }


    private void parseInput(){
        var shortNames = getShortNames();
        var longNames = getLongNames();
        var values = getValues();
        Iterator<Map.Entry<String, Object>> itr = shortNames.entrySet().iterator();

        while(itr.hasNext())
        {
            var entry = itr.next();
            Country newCountry = new Country(entry.getKey(), (Integer) entry.getValue());
            newCountry.setName(longNames.get(entry.getKey()).toString());
            for(int pos = newCountry.position * VALUE_OFF_SET; pos < newCountry.position * VALUE_OFF_SET + VALUE_OFF_SET; pos++)
            {
                newCountry.values.add(values.get(pos));
            }
            countries.add(newCountry);
        }


    }

    public void printLowestURCountries(String year){
        try {
            var lowestCountries = this.countries.stream()
                    .collect(Collectors.toMap(a -> a.shortName, a -> a.values
                            .get(Integer.parseInt(year) - Integer.parseInt(getStartYear()))));
            var lowest = lowestCountries.values().stream().sorted().limit(3).toArray();
            for(var low : lowest){
                for (var pair : lowestCountries.entrySet()){
                    if (pair.getValue() == low){
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                    }
                }
            }


        }catch(Exception e){
            System.out.println("Parse exception raised!");
        }
    }

    public void printHighestURCountries(String year){
        try {
            var lowestCountries = this.countries.stream()
                    .collect(Collectors.toMap(a -> a.shortName, a -> a.values
                            .get(Integer.parseInt(year) - Integer.parseInt(getStartYear()))));
            var lowest = lowestCountries.values().stream().sorted(Collections.reverseOrder()).limit(3).toArray();
            for(var low : lowest){
                for (var pair : lowestCountries.entrySet()){
                    if (pair.getValue() == low){
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                    }
                }
            }


        }catch(Exception e){
            System.out.println("Parse exception raised!");
        }
    }
}
