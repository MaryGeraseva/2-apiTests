package petStore.models.builders;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

import static petStore.models.enums.PetFields.ID;

public class DataGenerator {

    private static String getRandomNumericString(int bound) {
        return String.valueOf(new Random().nextInt(bound) + 1);
    }

    public static String getRandomId() {
        return getRandomNumericString(2147483646);
    }

    public static String getNewFieldValue(String field) {
        String newNestedField;
        if (field.equals(ID.getValue())) {
            newNestedField = getRandomId();
        } else {
            newNestedField = RandomStringUtils.randomAlphabetic(10);
        }
        return newNestedField;
    }
}
