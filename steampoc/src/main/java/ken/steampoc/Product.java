package ken.steampoc;

import lombok.ToString;

@ToString
public class Product {

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    private int price;

    private String name;

    public Product(final int price, final String name) {
        this.price = price;
        this.name = name;
    }

}
