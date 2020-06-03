package objects;

import org.json.JSONArray;

/**
 * @author Matus Valko
 */
public class Country {
    int numberOfYears;
    int startingYear;
    int endingYear;
    int[] values;

    public Country(int number, int start, int end, int[] values){
        numberOfYears = number;
        startingYear = start;
        endingYear = end;
        this.values = values;
    }

    public int getStartingYear(){
        return startingYear;
    }
}
