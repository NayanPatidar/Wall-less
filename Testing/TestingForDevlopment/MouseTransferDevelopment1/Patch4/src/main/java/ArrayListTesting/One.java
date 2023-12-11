package ArrayListTesting;

import java.util.ArrayList;

public class One {
    public static ArrayList<Product> products = new ArrayList<>();

    private void addProduct(Product product){
        products.add(product);
    }
    public static void main(String[] args) {
        One one = new One();

        Product productA = new Product("ProductA", true);
        Product productB = new Product("ProductB", false);

        one.addProduct(productA);
        one.addProduct(productB);

        Two two = new Two(products);

        new Thread(() -> {
            two.processProducts();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiting Compleete");
            Product product = products.get(1);
            product.setStatus(true);
            two.products.set(1, product);

        }).start();
    }
}
