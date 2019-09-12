package rest.petStoreTests.PetTests.dataProviders;

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

public class PetDataProvider {

    public static Stream<Arguments> postRequiredFieldsStatus200() {
        return Stream.of(
                arguments(1, "-9223372036854775808", "6", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "9223372036854775807", "26", "кот", PetStatuses.SOLD.nameLowerCase()),
                arguments(3, "0", "4556", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(4, "543", "-9223372036854775808", "собака", PetStatuses.SOLD.nameLowerCase()),
                arguments(5, "545", "9223372036854775807", "кот", PetStatuses.PENDING.nameLowerCase()),
                arguments(6, "547", "0", "собака", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(7, "548", "0", "", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(8, "5448", "6", "", PetStatuses.PENDING.nameLowerCase()),
                arguments(9, "549", "26", "#@sпнльGGSAШывф+/-#", PetStatuses.SOLD.nameLowerCase()),
                arguments(10, "66346", "6", "dog", PetStatuses.AVAILABLE.nameLowerCase())
        );
    }

    public static Stream<Arguments> postRequiredFieldsStatus405() {
        return Stream.of(
                arguments(1, "-9223372036854775809","16", "dog", PetStatuses.PENDING.nameLowerCase()),
                arguments(2, "9223372036854775808", "346", "cat", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(3, "544", "-9223372036854775809", "cat", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(4, "546", "9223372036854775808", "dog", PetStatuses.SOLD.nameLowerCase()),
                arguments(5, "550","336", "cat", ""),
                arguments(6, "551", "6", "dog", "testStatus"),
                arguments(7, "0", "336", "cat", ""),
                arguments(8, "id", "7", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(9, "0.8", "7", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(10, "0,8", "7", "dog", PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(11, "9223372036854775807.1", "id", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(12, "66347", "id", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(13, "66347", "45.5", "cat", PetStatuses.SOLD.nameLowerCase()),
                arguments(14, "66347", "45,6", "cat", PetStatuses.SOLD.nameLowerCase())
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
                arguments(1, "45556", "233956", "fish", "", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("235434", "best"), PetStatuses.SOLD.nameLowerCase()),
                arguments(2, "4564", "2336", "cat", "[|]'~<!--@/*$%^&#*/()?sпнльGGSAШывф+/-#", Arrays.asList("https://photo1.jpg"),
                        Map.of("2355", "vip", "2356", "sale"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(3, "4552", "2336", "cat", "tom", Arrays.asList(),
                        Map.of("2346", "hot","2347", "sale"), PetStatuses.AVAILABLE.nameLowerCase()),

                arguments(5, "4556", "2336", "cat", "lisa", Arrays.asList("https://photo1.jpg"),
                        Map.of("9223372036854775807", "vip"), PetStatuses.PENDING.nameLowerCase()),

                arguments(9, "4561", "2337", "cat", "tom", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("1515", "\"[|]'~<!--@/*$%^&#*/()?sпнльGGSAШывф+/-#"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(8, "4560", "2336", "cat", "bobik", Arrays.asList("https://photo1.jpg"),
                        Map.of("2366", ""), PetStatuses.SOLD.nameLowerCase()),
                arguments(10, "4563", "2339", "dog", "", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("2354", "hot", "2355", "vip"), PetStatuses.SOLD.nameLowerCase())
        );
    }

    public static Stream<Arguments> postAllFieldsStatus405() {
        return Stream.of(
                arguments(4, "4554", "2336", "cat", "bobik", Arrays.asList("https://photo1.jpg"),
                        Map.of("-9223372036854775808", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(1, "4555", "2339", "dog", "tom", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of(" -9223372036854775809",  "vip"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "4557", "2337", "cat", "bobik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("9223372036854775808", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(6, "4558", "2338", "dog", "tom", Arrays.asList("https://photo1.jpg"),
                        Map.of("0", "hot"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(7, "4559", "2339", "dog", "lisa", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("0", ""), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(3, "45591", "2339", "dog", "tom", Arrays.asList("https://photo1.jpg"),
                        Map.of("id", "hot"), PetStatuses.AVAILABLE.nameLowerCase())
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
