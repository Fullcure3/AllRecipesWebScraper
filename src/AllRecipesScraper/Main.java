package AllRecipesScraper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://www.allrecipes.com/recipes/201/meat-and-poultry/chicken/?internalSource=hubcard&referringContentType=Search&clickId=cardslot%201";
        String url2 = "https://www.allrecipes.com/recipes/200/meat-and-poultry/beef/?internalSource=hubcard&referringContentType=Search&clickId=cardslot%201";
        String url3 = "https://www.allrecipes.com/recipes/411/seafood/fish/?internalSource=hubcard&referringContentType=Search&clickId=cardslot%201";
        var webScraper = new WebScraper(url3);
//        var recipeCards = webScraper.createRecipeCards();

        var recipeCards = CardHandler.loadCards("FishRecipeCards.ser");
            CardHandler.convertToJson(recipeCards, "FishRecipes");
        }
    }



