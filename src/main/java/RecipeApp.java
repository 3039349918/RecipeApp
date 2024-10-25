import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RecipeApp {
    public static void main(String[] args) {
        RecipeOrganizer organizer = new RecipeOrganizer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Recipe Organizer!");

        while (true) {
            System.out.println("\n1. Add Recipe");
            System.out.println("2. View Recipes by Category");
            System.out.println("3. Display All Recipes");
            System.out.println("4. Search Online Recipes");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter ingredients (comma separated): ");
                    List<String> ingredients = Arrays.asList(scanner.nextLine().split(","));

                    System.out.print("Enter instructions: ");
                    String instructions = scanner.nextLine();

                    System.out.print("Enter tags (comma separated): ");
                    List<String> tags = Arrays.asList(scanner.nextLine().split(","));

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    Recipe recipe = new Recipe(name, ingredients, instructions, tags);
                    organizer.addRecipe(category, recipe);
                    System.out.println("Recipe added successfully!");
                    break;

                case 2:
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine();
                    List<Recipe> recipes = organizer.getRecipesByCategory(cat);
                    if (recipes.isEmpty()) {
                        System.out.println("No recipes found in this category.");
                    } else {
                        recipes.forEach(System.out::println);
                    }
                    break;

                case 3:
                    organizer.displayAllRecipes();
                    break;

                case 4:
                    System.out.print("Enter recipe to search online: ");
                    String query = scanner.nextLine();
                    try {
                        EdamamAPI.searchRecipe(query);
                    } catch (IOException e) {
                        System.out.println("Failed to fetch recipes from API.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for using Recipe Organizer!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
