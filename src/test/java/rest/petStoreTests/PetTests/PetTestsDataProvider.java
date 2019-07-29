package rest.petStoreTests.PetTests;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.params.provider.Arguments;
import petStore.models.petModel.PetStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PetTestsDataProvider {

    public static Stream<Arguments> requiredFieldsStatus200() {
        return Stream.of(
                arguments(1, "-9223372036854775808", "6", "dog", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(2, "9223372036854775807", "26", "кот", PetStatus.SOLD.nameLowerCase()),
                arguments(3, "0", "4556", "dog", PetStatus.PENDING.nameLowerCase()),
                arguments(4, "543", "-9223372036854775808", "собака", PetStatus.SOLD.nameLowerCase()),
                arguments(5, "545", "9223372036854775807", "кот", PetStatus.PENDING.nameLowerCase()),
                arguments(6, "547", "0", "собака", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(7, "548", "0", "", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(8, "5448", "6", "", PetStatus.PENDING.nameLowerCase()),
                arguments(9, "549", "26", "#@sпнльGGSAШывф+/-#", PetStatus.SOLD.nameLowerCase()),
                arguments(10, "66346", "6", "dog", PetStatus.AVAILABLE.nameLowerCase())
        );
    }

    public static Stream<Arguments> requiredFieldsStatus405() {
        return Stream.of(
                arguments(1, "-9223372036854775809","16", "dog", PetStatus.PENDING.nameLowerCase()),
                arguments(2, "9223372036854775808", "346", "cat", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(3, "544", "-9223372036854775809", "cat", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(4, "546", "9223372036854775808", "dog", PetStatus.SOLD.nameLowerCase()),
                arguments(5, "550","336", "cat", ""),
                arguments(6, "551", "6", "dog", "testStatus"),
                arguments(7, "0", "336", "cat", ""),
                arguments(8, "id", "7", "dog", PetStatus.AVAILABLE.nameLowerCase()),
                arguments(9, "66347", "id", "cat", PetStatus.SOLD.nameLowerCase())
        );
    }

    public static Stream<Arguments> allFieldsStatus200() {
        return Stream.of(
                arguments(1, "-9223372036854775808", "6", "dog", "bobik", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("2345", "sale","2346", "vip"), PetStatus.AVAILABLE.nameLowerCase()),
                arguments(2, "9223372036854775807", "26", "cat", "kisa", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("2347", "sale","2348", "vip"), PetStatus.SOLD.nameLowerCase()),
                arguments(3, "0", "16", "dog", "tom", Arrays.asList("https://photo1.jpg", "https://photo2.jpg"),
                        Map.of("2346", "hot","2347", "vip"), PetStatus.AVAILABLE.nameLowerCase())
        );
    }

    public static Stream<Arguments> pairZeroValue() {
        JsonNodeFactory factory = new JsonNodeFactory(false);

        return Stream.of(
                arguments(1, "5550", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatus.PENDING.nameLowerCase(), 200),
                arguments(2, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), PetStatus.SOLD.nameLowerCase(), 405),
                arguments(3, "552", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", 405),
                arguments(4, "0", factory.objectNode(), "", factory.arrayNode(), factory.arrayNode(), "", 405)
        );
    }
}
