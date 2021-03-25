package AllRecipesScraper;

import java.io.Serializable;
import java.util.List;

public class RecipeCard implements Serializable {
    private String imageUrl;
    private String recipeName;
    private String recipeUrl;
    private List<String> ingredients;
    private List<String> cookingInstructions;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(List<String> cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public RecipeCard(String imageUrl, String recipeName, String recipeUrl) {
        this.imageUrl = imageUrl;
        this.recipeName = recipeName;
        this.recipeUrl = recipeUrl;
    }

    public String getRecipeImage() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }


}
