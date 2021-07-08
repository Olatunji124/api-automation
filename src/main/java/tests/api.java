package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.*;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class api {

    @DataProvider
    public static Object[][] zipAndCountryCodes(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @Test
    @UseDataProvider("zipAndCountryCodes")
    public void assertResponseMessage(String countryCode, String zipCode, String expectedPlaceName) {

        given()
                .pathParam("countryCode", countryCode).pathParam("zipCode",  zipCode)
                .spec(requestSpec)
                .when()
                .get("{countryCode}/{zipCode}")
                .then()
                .log().body()
                .assertThat()
                .body("places[0].'place name'", equalTo(expectedPlaceName))
                .statusCode(200);
    }

}
