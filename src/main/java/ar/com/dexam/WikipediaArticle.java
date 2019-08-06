package ar.com.dexam;

        import com.google.gson.Gson;
        import com.google.gson.JsonObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.util.concurrent.atomic.AtomicInteger;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;


public class WikipediaArticle {

    private WikipediaArticle(){}

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

        while (true) {
            int index = allWords.indexOf(word);
            if(index < 0){
                break;
            }

            if(isValidWord(allWords, index, word.length())){
               amount++;
           }

            allWords = allWords.substring(index + word.length());
        }

        return amount;
    }

    private static boolean isValidWord(String allWords, int index, int length) {
        char previousLetter = allWords.charAt(index - 1);
        if(Character.isLetterOrDigit(previousLetter)){
            return false;
        }

        char nextLetter = allWords.charAt(index + length);
        if(Character.isLetterOrDigit(nextLetter)){
            return false;
        }

        System.out.println(allWords.substring(index -1, index +length +1));
        return true;
    }

    private static int countWordPattern(String word, String allWords) {
        String regex = "\\b"+word+"\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(allWords);

        int amount = 0;
        while (matcher.find()) {
            amount++;
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
