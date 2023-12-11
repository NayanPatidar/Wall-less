package ArrayListTesting;

import ArrayListTesting.Product;

import java.util.ArrayList;

class Two {
    public ArrayList<Product> products;

    public Two(ArrayList<Product> products) {
        this.products = products;
    }

    public void processProducts() {
        while (true) {
            for (Product product : products) {
                System.out.println("Product: " + product.getName() + ", Allowed: " + product.getStatus());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Process");
        }
    }
}