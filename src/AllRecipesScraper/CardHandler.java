package AllRecipesScraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardHandler {

    public static void saveCards(List<RecipeCard> cards, String fileName) {
        try (var fos = new FileOutputStream(fileName + ".ser");
             var buffer = new BufferedOutputStream(fos);
             var oos = new ObjectOutputStream(buffer)
        ) {
            serializeRecipeCards(cards, oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<RecipeCard> loadCards(String fileName) {
        List<RecipeCard> recipeCards = new ArrayList<>();
        try (var fis = new FileInputStream(fileName);
             var buffer = new BufferedInputStream(fis);
             var ois = new ObjectInputStream(buffer)
        ) {
            recipeCards = (List<RecipeCard>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recipeCards;
    }

    public static void convertToJson(List<RecipeCard> cards, String filename){
        String json = createFormattedJson(cards);

        try(var fw = new FileWriter(filename + ".json");
            var buffer = new BufferedWriter(fw)
        ) {
            createJsonFile(json, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void validateCards(List<RecipeCard> cards) {
        for (var recipe : cards) {
            System.out.println(recipe.getRecipeUrl());
            System.out.println(recipe.getIngredients().toString());
            System.out.println(recipe.getCookingInstructions().toString());
        }
    }

    private static void serializeRecipeCards(List<RecipeCard> cards, ObjectOutputStream oos) throws IOException {
        oos.writeObject(cards);
    }

    private static void createJsonFile(String json, BufferedWriter buffer) throws IOException {
        buffer.write(json);
    }

    private static String createFormattedJson(List<RecipeCard> cards) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cards);
        return json;
    }
}
