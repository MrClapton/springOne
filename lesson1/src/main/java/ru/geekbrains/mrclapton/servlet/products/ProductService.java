package ru.geekbrains.mrclapton.servlet.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductService {

    public static List<Product> getProducts(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String title = "продукт" + i;
            products.add(new Product(i, title, (float) (Math.random()*3)+1));
        }
        return Collections.unmodifiableList(products);
    }

}
