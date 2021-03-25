package AllRecipesScraper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SQLTest {

    public static void main(String[] args) {
        var sqlTest = new SQLTest();
        sqlTest.simpleStatement();
        sqlTest.preparedStatement();
    }

    public void preparedStatement(){
        try (Connection allRecipesCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/json_test", "root", "correct")) {
            var statement = allRecipesCon.prepareStatement(
                    "SELECT * " +
                    "FROM chickenrecipes" +
                    " WHERE recipe_id < ?");

            statement.setInt(1, 3);
            var resultSet = statement.executeQuery();

            while(resultSet.next()) {
                System.out.println(resultSet.getString("recipeName"));
                var ingredients = resultSet.getString("ingredients")
                        .replace('[', ' ')
                        .replace(']', ' ')
                        .replace("\",", "\"\n")
                        .replace('\"', ' ');
                System.out.println(ingredients);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void simpleStatement(){
        try (Connection allRecipesCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/json_test", "root", "correct")) {
            var statement = allRecipesCon.createStatement();
            var resultSet = statement.executeQuery(
                    "SELECT ingredients ->> '$[1 to 3]' AS ingredients " +
                            "FROM chickenrecipes" +
                            " WHERE recipe_id = 1");
            resultSet.next();
            var ingredients = resultSet.getString("ingredients");
            System.out.println(ingredients);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
