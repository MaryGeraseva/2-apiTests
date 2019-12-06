package petStoreTests.testData;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.params.provider.Arguments;
import petStore.models.enums.PetFields;
import petStore.models.enums.PetStatuses;
import petStore.responses.StatusCodes;
import petStore.сontrollers.enums.PetStoreHeaders;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

//petStoreTests.testData.DataProvider

public class DataProvider {

    public static Stream<Arguments> postRequiredFieldsStatus200() {
        return Stream.of(
                arguments(1, "9223372036854775807", "1", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "545", "9223372036854775807", "cat", PetStatuses.PENDING.nameLowerCase()),
                arguments(3, "546", "2", "", PetStatuses.SOLD.nameLowerCase()),
                arguments(4, "547", "3", "#@sпнльGGSAШывф+/-#", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(5, "548", "4", "dog", PetStatuses.PENDING.nameLowerCase())
        );
    }

    public static Stream<Arguments> postRequiredFieldsStatus405() {
        return Stream.of(
                arguments(1, "-9223372036854775808", "1", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "-9223372036854775809", "2", "cat", PetStatuses.PENDING.nameLowerCase()),
                arguments(3, "0", "3", "fish", PetStatuses.SOLD.nameLowerCase()),
                arguments(4, "id", "4", "кот", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(5, "0,5", "5", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(6, "22.0", "6", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(7, "9223372036854775808", "7", "fish", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(8, "9223372036854775807.1", "8", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(9, "4212", "-9223372036854775808", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(10, "4213", "-9223372036854775809", "fish", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(11, "4214", "0", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(12, "4215", "id", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(13, "4216", "1,5", "fish", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(14, "4217", "44.5", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(15, "4218", "9223372036854775808", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(16, "4219", "0", "", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(17, "4220", "9", "dog", ""),
                arguments(18, "4221", "10", "cat", "testStatus")
        );
    }

    public static Stream<Arguments> postPairZeroValue() {
        JsonNodeFactory factory = new JsonNodeFactory(false);

        return Stream.of(
                arguments(1, "5550", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatuses.PENDING.nameLowerCase(), StatusCodes.CODE200),
                arguments(2, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatuses.SOLD.nameLowerCase(), StatusCodes.CODE405_POST),
                arguments(3, "5520", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", StatusCodes.CODE405_POST),
                arguments(4, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", StatusCodes.CODE405_POST)
        );
    }

    public static Stream<Arguments> postAllFieldsStatus200() {
        return Stream.of(
                arguments(1, "4550", "2335", "fish", "", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("2354", "best"), PetStatuses.SOLD.nameLowerCase()),
                arguments(2, "4551", "2336", "cat", "[|]'~<!--@/*$%^&#*/()?sпнльGGSAШывф+/-#", Arrays.asList("https://photo1.jpg"),
                        Map.of("2355", "vip", "2356", "sale"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(3, "4552", "2337", "dog", "bob", Arrays.asList(),
                        Map.of("2357", "hot", "2356", "sale"), PetStatuses.PENDING.nameLowerCase()),
                arguments(4, "4353", "2338", "fish", "nemo", Arrays.asList("https://photo1.jpg"),
                        Map.of("9223372036854775807", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(5, "4354", "2336", "cat", "tom", Arrays.asList("https://photo1.jpg"),
                        Map.of("2358", ""), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(6, "4355", "2337", "dog", "bobik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("1515", "\"[|]'~<!--@/*$%^&#*/()?sпнльGGSAШывф+/-#"), PetStatuses.PENDING.nameLowerCase()),
                arguments(7, "4356", "2338", "fish", "goldy", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("2357", "hot", "2355", "vip"), PetStatuses.SOLD.nameLowerCase())
        );
    }

    public static Stream<Arguments> postAllFieldsStatus405() {
        return Stream.of(
                arguments(1, "6767", "1414", "cat", "murzik", Arrays.asList("https://photo1.jpg"),
                        Map.of("-9223372036854775808", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(2, "6768", "1515", "dog", "bobik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of(" -9223372036854775809", "vip"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(3, "6769", "1414", "cat", "tom", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("9223372036854775808", "sale"), PetStatuses.PENDING.nameLowerCase()),
                arguments(4, "6770", "1515", "dog", "palkan", Arrays.asList("https://photo1.jpg"),
                        Map.of("0", "hot", "0", "best"), PetStatuses.SOLD.nameLowerCase()),
                arguments(5, "6771", "1414", "cat", "murik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("0", ""), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(6, "6772", "1515", "dog", "druzok", Arrays.asList("https://photo1.jpg"),
                        Map.of("id", "hot"), PetStatuses.PENDING.nameLowerCase())
        );
    }

    public static Stream<Arguments> negativeWithJsonData() {
        return Stream.of(
                arguments(1, "{}"),
                arguments(2, "{\n\"id\": 1234,\n" +
                        "\"category\": {\n\"id\": 222,\n\"name\": \"fish\"\n},\n" +
                        "  \"name\": \"nemo\",\n" +
                        "  \"testField\": \"testValue\",\n" +
                        "  \"photoUrls\": [\n\"string\"\n],\n" +
                        "  \"tags\": [\n{\n\"id\": 23,\n\"name\": \"sale\"\n}\n],\n" +
                        "  \"status\": \"available\"\n}"),
                arguments(3, "{\n\"category\": {\n\"id\": 222,\n\"name\": \"fish\"\n},\n" +
                        "  \"name\": \"nemo\",\n" +
                        "  \"photoUrls\": [\n\"string\"\n],\n" +
                        "  \"tags\": [\n{\n\"id\": 23,\n\"name\": \"sale\"\n}\n],\n" +
                        "  \"status\": \"available\"\n}"),
                arguments(4, "{\n\"id\": 1234,\n" +
                        "  \"name\": \"nemo\",\n" +
                        "  \"photoUrls\": [\n\"string\"\n],\n" +
                        "  \"tags\": [\n{\n\"id\": 23,\n\"name\": \"sale\"\n}\n],\n" +
                        "  \"status\": \"available\"\n}"),
                arguments(5, "{\n\"id\": 1234,\n" +
                        " \"category\": {\n\"id\": 222,\n\"name\": \"fish\"\n},\n" +
                        "  \"name\": \"nemo\",\n" +
                        "  \"photoUrls\": [\n\"string\"\n],\n" +
                        "  \"tags\": [\n{\n\"id\": 23,\n\"name\": \"sale\"\n}\n]}")
        );
    }

    public static Stream<Arguments> putPositive() {
        return Stream.of(
                arguments(1, PetFields.ID.getValue(), null),
                arguments(2, PetFields.CATEGORY.getValue(), PetFields.ID.getValue()),
                arguments(3, PetFields.CATEGORY.getValue(), PetFields.NAME.getValue()),
                arguments(4, PetFields.NAME.getValue(), null),
                arguments(5, PetFields.PHOTO_URLS.getValue(), null),
                arguments(6, PetFields.TAGS.getValue(), PetFields.ID.getValue()),
                arguments(7, PetFields.TAGS.getValue(), PetFields.NAME.getValue()),
                arguments(8, PetFields.STATUS.getValue(), null),
                arguments(9, null, null),
                arguments(10, "all", "all")
        );
    }

    public static Stream<Arguments> invalidId() {
        return Stream.of(
                arguments(1, "-9223372036854775809"),
                arguments(2, "-9223372036854775808"),
                arguments(3, "0"),
                arguments(4, "9223372036854775808"),
                arguments(5, "0.4"),
                arguments(6, "45,6"),
                arguments(7, "id"),
                arguments(8, ""),
                arguments(9, "/")
        );
    }

    public static Stream<Arguments> invalidHeaders() {
        return Stream.of(
                arguments(1, Map.of("api_key", "testKey", "Content-Type", "application/json")),
                arguments(2, Map.of("api_key", "null", "Content-Type", "application/json")),
                arguments(3, Map.of("api_key", "", "Content-Type", "application/json"))
        );
    }

    public static Stream<Arguments> nonexistentHeaders() {
        return Stream.of(
                arguments(1, Map.of(PetStoreHeaders.KEY.getHeaderName(), PetStoreHeaders.KEY.getHeaderValue(),
                        PetStoreHeaders.CONTENT_TYPE.getHeaderName(), PetStoreHeaders.CONTENT_TYPE.getHeaderValue(),
                        "X-Pingback", "test")),
                arguments(2, Map.of(PetStoreHeaders.KEY.getHeaderName(), PetStoreHeaders.KEY.getHeaderValue(),
                        PetStoreHeaders.CONTENT_TYPE.getHeaderName(), PetStoreHeaders.CONTENT_TYPE.getHeaderValue(),
                        "Test_header", "test_value"))
        );
    }
}
