package api.specs.itemlist;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.specs.BaseSpec.baseRequestSpec;
import static io.restassured.RestAssured.with;

public class AddItemToListSpec {

    public static RequestSpecification addItemRequestSpec = with()
            .spec(baseRequestSpec)
            .contentType(ContentType.URLENC);

}
