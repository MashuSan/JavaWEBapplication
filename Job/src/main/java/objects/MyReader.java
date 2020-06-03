package objects;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Matus Valko
 */
public class MyReader {
    public String read() throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL("https://json-stat.org/samples/oecd.json");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();

        } catch(IOException e){

        }
        finally {
            if (reader != null)
                reader.close();
        }
        return null;
    }

    public JSONObject convert() throws Exception {
        try {
            String jsonText = read();
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch(Exception ex){}
        return null;
    }
}

