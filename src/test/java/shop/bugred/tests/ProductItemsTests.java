package shop.bugred.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import shop.bugred.utils.models.*;

import static org.hamcrest.Matchers.*;
import static shop.bugred.common.ProductItemSteps.*;
import static shop.bugred.utils.client.Specification.prepareRequest;
import static shop.bugred.utils.client.Specification.responseSpecification;

public class ProductItemsTests {

    @RetryingTest(maxAttempts = 3, minSuccess = 1)
    @Description("Perform get item request using random id and verify that required fields not empty")
    public void getRandomItemByIdAndVerifyRequiredFieldsNotEmpty() {
        IdRequest request =  new IdRequest(getRandomId());
        prepareRequest().when().body(request).get("get/")
                .then().spec(responseSpecification())
                .body("result.name", not(hasValue(nullValue())))
                .body("result.section", not(hasValue(nullValue())))
                .body("result.description", not(hasValue(nullValue())));
    }

    @Description("Perform get item request using id that does not exist and verify error message")
    @Test
    public void getItemByNotExistingId() {
        int notExistingId = 4440;
        IdRequest request =  new IdRequest(notExistingId);
        prepareRequest().when().body(request).get("get/")
                .then().body("status", equalTo("error"))
                .body("error", equalTo("item_with_id_not_found"));
    }

    @Description("Create product item via post request and compares response with get request by id of created product")
    @Test
    public void createProductItem() {
        String name = "Блуза";
        String section = "Платья";
        String description = "Моднaя блуза из новой коллекции!";
        String color = "RED";
        String size = "44";
        float price = 880;
        String params = "";
        ProductItem item = new ProductItem(name, section, description, color, size, price, params);
        SingleProductItemResponse createItemResponse = prepareRequest()
                .when().body(item).post("create/")
                .then().spec(responseSpecification())
                .extract().as(SingleProductItemResponse.class);
        SingleProductItemResponse getCreatedItemResponse = getProductItemResponseById(createItemResponse.getResult().getId());
        Assertions.assertTrue(createItemResponse.getResult().equals(getCreatedItemResponse.getResult()));
    }

    @Description("Update existing item using put request and verify that fields are updated using get request")
    @Test
    public void updateProductItem() {
        String initialName = "Блуза";
        String initialSection = "Рубашки";
        String initialDescription = "Моднaя блуза из новой коллекции!";
        String newName = "Джинсы";
        String newSection = "Брюки";
        String newDescription = "Классические джинсы с ремнем";
        String successMessage = "Товар обновлен!";
        ProductItem item = createProductItemsWithMinFields(initialName, initialSection, initialDescription);
        item.setName(newName);
        item.setSection(newSection);
        item.setDescription(newDescription);

        prepareRequest().when().body(item).put("update/")
                .then().spec(responseSpecification())
                .body("result", equalTo(successMessage));

        SingleProductItemResponse getUpdatedItemResponse = getProductItemResponseById(item.getId());
        Assertions.assertTrue(getUpdatedItemResponse.getResult().getName().equals(newName));
        Assertions.assertTrue(getUpdatedItemResponse.getResult().getSection().equals(newSection));
        Assertions.assertTrue(getUpdatedItemResponse.getResult().getDescription().equals(newDescription));
    }

    @Description("Delete product item and verify that get request by id of deleted product returns an error")
    @Test
    public void deleteProductItem() {
        String name = "Блуза";
        String section = "Рубашки";
        String description = "Моднaя блуза из новой коллекции!";
        String successMessage = "успешно удален";
        ProductItem item = createProductItemsWithMinFields(name, section, description);
        IdRequest request =  new IdRequest(item.getId());
        prepareRequest().when().body(request).delete("delete/")
                .then().spec(responseSpecification())
                .body("result", containsString(successMessage));
        verifyThatProductNotFound(item.getId());
    }

    @Description("Search for product using valid query and verify that returned product name match the query")
    @Test
    public void searchForProductUsingValidQuery() {
        String validSearchQuery = "Шорты-юбка";
        ShortSearchResponse response = prepareRequest()
                .when().body(new SearchQuery(validSearchQuery))
                .get("search/")
                .then().spec(responseSpecification())
                .extract().as(ShortSearchResponse.class);
        Assertions.assertTrue(response.getResult().get(0).getName().contains(validSearchQuery));
    }

    @Description("Search for query that has more than 10 results and verify that returned titles match the query")
    @Test
    public void searchForQueryWithMoreThanTenResults() {
        String searchQuery = "Платье";
        LongSearchResponse response = prepareRequest()
                .when().body(new SearchQuery(searchQuery))
                .get("search/")
                .then().spec(responseSpecification())
                .extract().as(LongSearchResponse.class);
        Assertions.assertTrue(response.getResult().stream().allMatch(s -> s.getTitle().contains(searchQuery)));
    }

    @Description("Search for query without results and verify that response list is empty")
    @Test
    public void searchForQueryWithoutResults() {
        String searchQueryWithoutResults = "Some text";
        ShortSearchResponse response = prepareRequest()
                .when().body(new SearchQuery(searchQueryWithoutResults))
                .get("search/")
                .then().spec(responseSpecification())
                .extract().as(ShortSearchResponse.class);
        Assertions.assertTrue(response.getResult().size() == 0);
    }
}
