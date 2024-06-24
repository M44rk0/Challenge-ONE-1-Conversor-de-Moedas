package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Conversion {

    private static JsonObject connect(String url_str) throws IOException, URISyntaxException {
        URL url = new URI(url_str).toURL();
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));

        return jsonElement.getAsJsonObject();
    }

    public static String getConversionResult(String API_KEY, String from, String to, String amountValue) throws IOException, URISyntaxException {
        String url_str = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                API_KEY, from, to, amountValue);
        JsonObject jsonObject = Conversion.connect(url_str);
        var result = jsonObject.get("conversion_result").getAsString();
        double resultValue = Double.parseDouble(result);
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(resultValue);
    }

    public static ArrayList<String> getCurrencyCodes(String API_KEY) throws URISyntaxException, IOException {
        String url_str = String.format("https://v6.exchangerate-api.com/v6/%s/latest/BRL", API_KEY);
        JsonObject jsonObject = Conversion.connect(url_str);
        JsonObject conversionRates = (JsonObject) jsonObject.get("conversion_rates");

        return new ArrayList<>(conversionRates.keySet());
    }
}
