package rest.petStoreTests.PetTests;

import org.junit.jupiter.params.provider.Arguments;
import petStore.models.petModel.Category;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PetTestsDataProvider {

//    public static Stream<Arguments> requiredFieldsStatus200() {
//        return Stream.of(
//                arguments(1, -9223372036854775808L, new Category(6l, "dog"), "available"),
//                arguments(2, 9223372036854775807L, new Category(26l, "кот"), "sold"),
//                arguments(3, 0L, new Category(4556L, "dog"), "pending"),
//                arguments(4, 543L, new Category(-9223372036854775808L, "собака"), "sold"),
//                arguments(5, 545L, new Category(9223372036854775807L, "кот"), "pending"),
//                arguments(6, 547L, new Category(0L, "собака"), "available"),
//                arguments(7, 548L, new Category(0L, ""), "available"),
//                arguments(8, 5448L, new Category(6L, ""), "pending"),
//                arguments(9, 549L, new Category(26L, "#@sпнльGGSAШывф+/-#"), "sold"),
//                arguments(10, 5550L, new Category(), "pending"),
//                arguments(11, 0L, new Category(), "sold"),
//                arguments(12, 66346L, new Category(6L, "dog"), "available")
//        );
//    }

    public static Stream<Arguments> requiredFieldsStatus200() {
        return Stream.of(
                arguments(1, "-9223372036854775808", new Category("6", "dog"), "available"),
                arguments(2, "9223372036854775807", new Category("26", "кот"), "sold"),
                arguments(3, "0", new Category("4556", "dog"), "pending"),
                arguments(4, "543", new Category("-9223372036854775808", "собака"), "sold"),
                arguments(5, "545", new Category("9223372036854775807", "кот"), "pending"),
                arguments(6, "547", new Category("0", "собака"), "available"),
                arguments(7, "548", new Category("0", ""), "available"),
                arguments(8, "5448", new Category("6", ""), "pending"),
                arguments(9, "549", new Category("26", "#@sпнльGGSAШывф+/-#"), "sold"),
//                arguments(10, "5550", new Category(), "pending"),
                arguments(11, "0", new Category(), "sold"),
                arguments(12, "66346", new Category("6", "dog"), "available")
        );
    }

    public static Stream<Arguments> requiredFieldsStatus405BorderVerification() {
        return Stream.of(
                arguments(1, "-9223372036854775809", new Category("16", "dog"), "pending"),
                arguments(2, "9223372036854775808", new Category("346", "cat"), "available"),
                arguments(3, "544", new Category("-9223372036854775809", "cat"), "available"),
                arguments(4, "546", new Category("9223372036854775808", "dog"), "sold"),
                arguments(5, "550", new Category("336", "cat"), ""),
                arguments(6, "551", new Category("6", "dog"), "testStatus"),
                arguments(1, "0", new Category("336", "cat"), "")
        );
    }
}
