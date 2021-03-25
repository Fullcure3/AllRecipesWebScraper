package AllRecipesScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    private String categoryUrl; //Popular Category after searching an ingredient in AllRecipes
    //Consider making this the primary page on AllRecipes
    //then create a helper method to generate the category url

    public WebScraper(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public List<RecipeCard> createRecipeCards() throws IOException{ //url is the category main page
        String htmlElement = "div.card__imageContainer";
        Elements htmlElements = getMatchingElements(categoryUrl, htmlElement);
        var recipeCards = addCardsToList(htmlElements);
        return recipeCards;
    }

    private List<RecipeCard> addCardsToList(Elements htmlElements) throws IOException {
        var recipeCards = new ArrayList<RecipeCard>();
        for(var recipe : htmlElements){
            if(isValidRecipeURL(recipe)) {
                var card = new RecipeCard(scrapeRecipeImage(recipe), scrapeRecipeName(recipe), scrapeRecipeUrl(recipe));
                addIngredients(card.getRecipeUrl(), card);
                addInstructions(card.getRecipeUrl(), card);
                recipeCards.add(card);
            }
        }
        return recipeCards;
    }

    private void addIngredients(String url, RecipeCard card) throws IOException { //url from the recipe card
        var ingredients = new ArrayList<String>();

        String htmlElement = "span.ingredients-item-name";
        Elements htmlElements = getMatchingElements(url, htmlElement);
        for(var ingredient : htmlElements) {
            var text = getText(ingredient); //filterIngredients
            ingredients.add(text);
        }
        card.setIngredients(ingredients);
    }

    private void addInstructions(String url, RecipeCard card) throws IOException{
        var instructions = new ArrayList<String>();

        String htmlElement = "li.subcontainer.instructions-section-item";
        Elements htmlElements = getMatchingElements(url, htmlElement);
        for(var cookingStep : htmlElements) {
            var text = getText(cookingStep).replace("Advertisement", "");
            instructions.add(text);
        }
        card.setCookingInstructions(instructions);
    }

    private Elements getMatchingElements(String url, String htmlElement) throws IOException {
        Document webPage = scrapeRecipeCategoryData(url);
        Elements htmlElements = filterElements(webPage, htmlElement);
        return htmlElements;
    }

    private String getText(Element element) {
        return element.text();
    }

    private Elements filterElements(Document webPage, String htmlElement) {
        return webPage.select(htmlElement);
    }

    private boolean isValidRecipeURL(Element recipe) {
        return !scrapeRecipeUrl(recipe).contains("gallery");
    }

    private Document scrapeRecipeCategoryData(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private String scrapeRecipeImage(Element element) {
        return element.select("img").attr("src");
    }

    private String scrapeRecipeName(Element element) {
        return element.select("a").attr("title");
    }

    private String scrapeRecipeUrl(Element element) {
        return element.select("a").attr("href");
    }
}


