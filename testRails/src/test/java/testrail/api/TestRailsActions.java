package testrail.api;

import framework.utils.Log;
import models.UserModel;
import org.json.simple.JSONObject;
import testrail.enums.ParamsPostTestRails;
import testrail.execeptions.APIException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

public class TestRailsActions {
    APIClient client;

    public TestRailsActions() {

    }

    public void configurator(UserModel userModel) {
        client = new APIClient(userModel.getServer());
        client.setUser(userModel.getLogin());
        client.setPassword(userModel.getPass());
    }

    public String addResultForCase(String runId, String caseId, Map<ParamsPostTestRails, Object> data) {
        try {
            return ((JSONObject) client.sendPost(String.format("add_result_for_case/%s/%s", runId, caseId), data))
                    .get("id").toString();
        } catch (IOException | APIException e) {
            Log.error(String.format("Can`t add your attachments to case id %s",caseId),Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException(String.format("Can`t add your attachments to result id %s", caseId));
        }
    }

    public void addAttachToResult(String resultId, String filePath) {
        try {
            client.sendPost(String.format("add_attachment_to_result/%s", resultId), filePath);
        } catch (IOException | APIException e) {
            Log.error(String.format("Can`t add your attachments to result id %s",resultId),Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException(String.format("Can`t add your attachments to result id %s",resultId));
        }
    }
}
