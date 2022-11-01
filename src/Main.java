import java.util.Scanner;

public class Main {
    public static String[] products = {"Хлеб", "Пачка гречки", "Упаковка яиц", "Мороженка"};
    public static int[] prices = {50, 135, 65, 53};
    public static int MIN_COST_FOR_BONUS = 1000;

    // В стоимости этих товаров каждые три товара должны стоить как два:
    public static String[] productsOnSale = {"Хлеб", "Мороженка"};

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в магазин!");
        System.out.println("Наш ассортимент:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + products[i] + " за " + prices[i] + " за шт. ");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int[] counts = new int[products.length];

        while (true) {
            System.out.print("Введите номер товара и количество через пробел или end: ");
            String line = scanner.nextLine();

            if ("end".equals(line)) {
                break;
            }

            String[] parts = line.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            counts[productNum] += productCount;
        }

        System.out.println("Ваша корзина покупок:");
        int sumToCheck = 0;
        boolean isOnSale;
        for (int i = 0; i < products.length; i++) {
            sumToCheck += prices[i] * counts[i];
        }
        boolean doBonus = sumToCheck >= MIN_COST_FOR_BONUS;
        int sum = 0;
        for (int j = 0; j < products.length; j++) {
            isOnSale = false;
            if (counts[j] != 0) {
                for (String saleProduct : productsOnSale) {
                    if (products[j].equals(saleProduct)) {
                        isOnSale = true;
                        break;
                    }
                }
                int currentPrice = (isOnSale ? prices[j] * (counts[j] / 3 * 2 + counts[j] % 3) : prices[j] * counts[j]);
                sum += currentPrice;
                if (doBonus) {
                    counts[j] += 1;
                    System.out.println("\t" + products[j] + " " + counts[j] + " шт. за " + currentPrice + " руб." + (isOnSale ? "(распродажа!)" : ""));
                } else {
                    System.out.println("\t" + products[j] + " " + counts[j] + " шт. за " + currentPrice + " руб." + (isOnSale ? "(распродажа!)" : ""));
                }
            }
        }
        System.out.println("Итого: " + sum + " руб.");
    }
}
