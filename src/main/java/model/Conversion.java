package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;

public class Conversion {

    public static String getConversionResult(String url_str) throws IOException, URISyntaxException {
        URL url = new URI(url_str).toURL();
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject.get("conversion_result").getAsString();
    }

    public static String formatResult(String result) {
        double resultValue = Double.parseDouble(result);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(resultValue);
    }
}
