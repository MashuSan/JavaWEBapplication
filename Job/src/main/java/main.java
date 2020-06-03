/**
 * @author Matus Valko
 */

import objects.Country;
import objects.MyReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class main {
    public static void main(String[] args) throws Exception {

        MyReader reader = new MyReader();
        JSONObject json = reader.convert();
        JSONArray array = json.getJSONArray("value");
        System.out.println(reader.read());
        String[] yearSpan = json.getJSONObject("dimension").getJSONObject("year").get("label").toString().split("-");
        Country country;
        for (int i = Integer.parseInt(yearSpan[0]); i <= Integer.parseInt(yearSpan[1]); i++){
            int pointer = 0;

        }
        country = new Country(12, Integer.parseInt(yearSpan[0]), Integer.parseInt(yearSpan[1]), null);
        System.out.println(country.getStartingYear());


    }
}
