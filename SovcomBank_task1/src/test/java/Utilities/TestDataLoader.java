package Utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataLoader {
    public static List<TestData> loadTestData(String filePath, String testType) throws IOException {
        Gson gson = new Gson();
        List<TestData> allTestData = gson.fromJson(new FileReader(filePath), new TypeToken<List<TestData>>(){}.getType());

        return allTestData.stream()
                .filter(data -> data.getTestType().equals(testType))
                .collect(Collectors.toList());
    }
}
