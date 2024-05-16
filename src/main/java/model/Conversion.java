package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Conversion {

    public static String getConversionResult(String url_str) throws IOException, URISyntaxException {
        URL url = new URI(url_str).toURL();
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        var result = jsonObject.get("conversion_result").getAsString();
        double resultValue = Double.parseDouble(result);
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(resultValue);
    }

    public static ArrayList<String> getCurrencyCodes() throws URISyntaxException, IOException {
        Dotenv dotenv = Dotenv.load();
        String API_KEY = dotenv.get("API_KEY");
        String url_str = "https://v6.exchangerate-api.com/v6/" + API_KEY +"/latest/BRL";
        URL url = new URI(url_str).toURL();
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject conversionRates = (JsonObject) jsonObject.get("conversion_rates");

        return new ArrayList<>(conversionRates.keySet());
    }
}
