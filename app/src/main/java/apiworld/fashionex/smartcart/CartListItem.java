package apiworld.fashionex.smartcart;

/**
 * Created by sihao.chua on 9/11/16.
 */
public class CartListItem {

    private int item_image;
    private String item_name;
    private String item_price;
    private String item_quantity;
    private String item_size;

    public CartListItem(int image, String name, String price, String quantity, String size) {
        this.item_image = image;
        this.item_name = name;
        this.item_price = price;
        this.item_quantity = quantity;
        this.item_size = size;
    }

    public int getImage() {
        return this.item_image;
    }

    public String getName() {
        return this.item_name;
    }

    public String getPrice() {
        return this.item_price;
    }

    public String getQuantity() {
        return this.item_quantity;
    }

    public String getSize() {
        return this.item_size;
    }
}
