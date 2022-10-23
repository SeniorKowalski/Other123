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
        int[] productSum = new int[products.length];

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
        int sum = 0;
        boolean isOnSale = false;
        for (int i = 0; i < products.length; i++) {
            if (counts[i] != 0) {
                isOnSale = false;
                for (String saleProduct : productsOnSale) {
                    if (products[i].equals(saleProduct)) {
                        isOnSale = true;
                    }
                }
                    productSum[i] += (isOnSale ? prices[i] * (counts[i] / 3 * 2 + counts[i] % 3) : prices[i] * counts[i]);
                    sum += productSum[i];
            }
        }
        boolean doBonus = sum >= MIN_COST_FOR_BONUS;
        for (int j = 0; j < products.length; j++) {
            isOnSale = false;
            if (counts[j] != 0) {
                for (String saleProduct : productsOnSale) {
                    if (products[j].equals(saleProduct)) {
                        isOnSale = true;
                    }
                }
                if (doBonus) {
                    counts[j] += 1;
                    System.out.println("\t" + products[j] + " " + counts[j] + " шт. за " + productSum[j] + " руб." + (isOnSale ? "(распродажа!)" : ""));
                } else {
                    System.out.println("\t" + products[j] + " " + counts[j] + " шт. за " + productSum[j] + " руб." + (isOnSale ? "(распродажа!)" : ""));
                }
            }
        }
            System.out.println("Итого: " + sum + " руб.");
    }
}
