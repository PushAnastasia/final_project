package shop.bugred.common;

import shop.bugred.utils.models.ProductItem;
import shop.bugred.utils.models.IdRequest;
import shop.bugred.utils.models.SingleProductItemResponse;

import java.util.Random;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static shop.bugred.utils.client.Specification.prepareRequest;
import static shop.bugred.utils.client.Specification.responseSpecification;

public class ProductItemSteps {

    public static ProductItem createProductItemsWithMinFields(String name, String section, String description) {
        ProductItem item = new ProductItem(name, section, description);

        int id = Integer.parseInt(prepareRequest().when().body(item).post("create/")
                .then().spec(responseSpecification())
                .extract().path("result.id"));
        item.setId(id);
        return item;
    }

    public static SingleProductItemResponse getProductItemResponseById(int id) {
        IdRequest requestBody = new IdRequest(id);
        return prepareRequest().when().body(requestBody).get("get/")
                .then().spec(responseSpecification())
                .extract().as(SingleProductItemResponse.class);
    }

    public static void verifyThatProductNotFound(int id) {
        IdRequest requestBody = new IdRequest(id);
        prepareRequest().when().body(requestBody).get("get/")
                .then().body("status", equalTo("error"))
                .body("message", containsString("не найден!"));
    }

    public static int getRandomId() {
        Random random = new Random();
        int maxProductItemAmount = 80;
        return random.ints(1, maxProductItemAmount).findFirst().getAsInt();
    }
}
