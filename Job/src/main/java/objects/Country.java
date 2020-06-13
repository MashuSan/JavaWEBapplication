package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Matus Valko
 */
public class Country {
    public String name;
    public String shortName;
    public List values;
    public int position;

    public Country(String shortName, int position){
        this.shortName = shortName;
        this.position = position;
        this.values = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return position == country.position &&
                name.equals(country.name) &&
                shortName.equals(country.shortName) &&
                values.equals(country.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName, values, position);
    }
}
