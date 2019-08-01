package rest.petStoreTests.PetTests.dataProviders;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.params.provider.Arguments;
import petStore.models.enums.PetStatuses;
import petStore.responses.StatusCodes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PetPostDataProvider {

    public static Stream<Arguments> requiredFieldsStatus200() {
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

    public static Stream<Arguments> requiredFieldsStatus405() {
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

    public static Stream<Arguments> pairZeroValue() {
        JsonNodeFactory factory = new JsonNodeFactory(false);

        return Stream.of(
                arguments(1, "5550", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatuses.PENDING.nameLowerCase(), StatusCodes.CODE200.getCode()),
                arguments(2, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatuses.SOLD.nameLowerCase(), StatusCodes.CODE405_POST.getCode()),
                arguments(3, "552", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", StatusCodes.CODE405_POST.getCode()),
                arguments(4, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", StatusCodes.CODE405_POST.getCode())
        );
    }

    public static Stream<Arguments> allFieldsStatus200() {
        return Stream.of(
                arguments(1, "4552", "2336", "cat", "tom", Arrays.asList(),
                        Map.of("2346", "hot","2347", "sale"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "4554", "2336", "cat", "bobik", Arrays.asList("https://photo1.jpg"),
                        Map.of("-9223372036854775808", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(3, "4556", "2336", "cat", "lisa", Arrays.asList("https://photo1.jpg"),
                        Map.of("9223372036854775807", "vip"), PetStatuses.PENDING.nameLowerCase()),
                arguments(4, "4558", "2338", "dog", "tom", Arrays.asList("https://photo1.jpg"),
                        Map.of("0", "hot"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(5, "4559", "2339", "dog", "lisa", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("0", ""), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(6, "4560", "2336", "cat", "bobik", Arrays.asList("https://photo1.jpg"),
                        Map.of("2366", ""), PetStatuses.SOLD.nameLowerCase()),
                arguments(7, "4561", "2337", "cat", "tom", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("0", "\"[|]'~<!--@/*$%^&#*/()?sпнльGGSAШывф+/-#"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(8, "4563", "2339", "dog", "", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("2354", "hot", "2355", "vip"), PetStatuses.SOLD.nameLowerCase()),
                arguments(9, "4564", "2336", "cat", "[|]'~<!--@/*$%^&#*/()?ф+/-#", Arrays.asList("https://photo1.jpg"),
                        Map.of("2355", "vip", "2356", "sale"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(10, "45556", "233956", "fish", "", Arrays.asList("https://photo1.jpg","https://photo2.jpg"),
                        Map.of("235434", "best"), PetStatuses.SOLD.nameLowerCase())
        );
    }

    public static Stream<Arguments> allFieldsStatus405() {
        return Stream.of(
                arguments(1, "4555", "2339", "dog", "tom", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of(" -9223372036854775809",  "vip"), PetStatuses.AVAILABLE.nameLowerCase()),
                arguments(2, "4557", "2337", "cat", "bobik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("9223372036854775808", "sale"), PetStatuses.SOLD.nameLowerCase()),
                arguments(3, "45591", "2339", "dog", "tom", Arrays.asList("https://photo1.jpg"),
                        Map.of("id", "hot"), PetStatuses.AVAILABLE.nameLowerCase())
        );
    }

    public static Stream<Arguments> negativeTests() {
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
}
