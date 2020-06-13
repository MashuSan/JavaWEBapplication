package utils;

import objects.Country;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Matus Valko
 */
public class Parser {
    private MyReader reader;
    private JSONObject json;
    private static final int VALUE_OFF_SET = 12;
    private static final String EU_15_COUNTRIES = "EU15";
    private static final String ALL_COUNTRIES = "OECD";
    private List<Country> countries;

    public Parser(){
        reader = new MyReader();
        json = reader.convert();
        countries = new ArrayList<Country>();
        parseInput();
    }

    private String getStartYear(){
        return json.getJSONObject("dimension").getJSONObject("year").get("label").toString().split("-")[0];
    }

    private Map<String, Object> getShortNames(){
        return json.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("index").toMap();
    }

    private Map<String, Object> getLongNames(){
        return json.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("label").toMap();
    }

    private List<Object> getValues(){
        return json.getJSONArray("value").toList();
    }

    /**
     * Parses input from json into Country objects
     */
    private void parseInput(){
        Map<String, Object> shortNames = getShortNames();
        Map<String, Object> longNames = getLongNames();
        List<Object> values = getValues();
        Iterator<Map.Entry<String, Object>> itr = shortNames.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, Object> entry = itr.next();
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
        System.out.println("3 countries with lowest unemployment rate : ");
        try {
            Map<String, Object> lowestCountries = this.countries.stream()
                    .filter(e -> !e.shortName.equals(EU_15_COUNTRIES) && !e.shortName.equals(ALL_COUNTRIES))
                    .collect(Collectors.toMap(a -> a.shortName, a -> a.values
                            .get(Integer.parseInt(year) - Integer.parseInt(getStartYear()))));

            Object[] lowest = lowestCountries.values().stream().sorted().limit(3).toArray();
            for(Object low : lowest) {
                for (Map.Entry<String, Object> pair : lowestCountries.entrySet()) {
                    if (pair.getValue() == low) {
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Parse exception raised!");
        }
    }

    public void printHighestURCountries(String year){
        System.out.println("3 countries with highest unemployment rate : ");
        try {
            Map<String, Object> lowestCountries = this.countries.stream()
                    .filter(e -> !e.shortName.equals(EU_15_COUNTRIES) && !e.shortName.equals(ALL_COUNTRIES))
                    .collect(Collectors.toMap(a -> a.shortName, a -> a.values
                            .get(Integer.parseInt(year) - Integer.parseInt(getStartYear()))));
            Object[] highest = lowestCountries.values().stream().sorted(Collections.reverseOrder()).limit(3).toArray();
            for(Object high : highest){
                for (Map.Entry<String, Object> pair : lowestCountries.entrySet()){
                    if (pair.getValue() == high){
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Parse exception raised!");
        }
    }
}
