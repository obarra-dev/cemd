package ar.com.dexam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WikipediaArticleDespegar {

    private WikipediaArticleDespegar(){}

    private static String getDataFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                builder.append(chars, 0, read);

            return builder.toString();
        }
    }

    private static String getDataText(String allWords) {
        JsonObject jsonObject = new Gson().fromJson(allWords, JsonObject.class);
        return jsonObject.get("parse").getAsJsonObject().get("text").toString();
    }

    private static int countWord(String word, String allWords) {
        int amount = 0;
        int index = allWords.indexOf(word);
        while (index != -1) {
            amount++;
            allWords = allWords.substring(index + word.length());
            index = allWords.indexOf(word);
        }

        return amount;
    }
    public static int getTopicCount(String word) {
        int count = 0;
        try {
            String allWords = getDataFromURL(
                    "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=" + word);
            String text = getDataText(allWords);
            count = countWord(word, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
