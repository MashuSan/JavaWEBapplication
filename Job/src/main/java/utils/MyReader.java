package utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Matus Valko
 */
class MyReader {

    /**
     * Method reads data from given link
     * @return buffer with read data if succesful, null if not
     * @throws IOException if somethings goes wrong while trying to read
     */
    private String read() throws IOException {
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
            System.out.println("The function has raised an exception " + e);
            return null;
        }
        finally {
            if (reader != null)
                reader.close();
        }
    }

    /**
     * Converts read text from private method read() into JSONObject
     * @return JSONObject with data
     */
    protected JSONObject convert(){
        try {
            String jsonText = read();
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch(Exception e){
            System.out.println("The function has raised an exception " + e);
            return null;
        }
    }
}

