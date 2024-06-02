package a2.ppl.tugas.akhir.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import a2.ppl.tugas.model.User;
import io.restassured.response.Response;

public class ErrorChecker {
    public static List<String> checkResponse(User expecUser, Response actualRes) {
        List<String> listMessage = new ArrayList<String>();
        User actual = actualRes.as(User.class);
        if (actual.getError() != null) {
            listMessage.add(actual.getError());
        }
        List<String> differences = expecUser.compare(actual);
        listMessage.addAll(differences);
        // If any assertions failed, throw CustomAssertionError
        return listMessage;
    }
}
