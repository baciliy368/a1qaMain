package vk.utils;

import framework.utils.ModelGenerator;
import models.Doc;
import models.DocFile;
import models.api.DocOnServerModel;
import models.api.ParamRequestModel;
import models.api.UploadServer;
import vk.api.JsonApi;
import vk.enums.EndPoints;
import java.io.File;

public class DocUploader {
    private JsonApi jsonApi;

    public DocUploader(JsonApi jsonApi) {
        this.jsonApi = jsonApi;

    }

    public Doc getDocOnServer(File doc, ParamRequestModel params) {
        String linkToUploadFile = getLinkToUploadFile(params);
        return saveDoc(uploadDocOnWall(linkToUploadFile, doc));
    }

    private String getLinkToUploadFile(ParamRequestModel params) {
        return jsonApi.executeGet(EndPoints.DOCS_WALL_UPLOAD_SERVER, params, UploadServer.class).getUploadUrl();
    }

    private DocOnServerModel uploadDocOnWall(String urlToUpload, File doc) {
        return ModelGenerator.getModelByMapping(jsonApi.uploadFileByUrl(urlToUpload, doc), DocOnServerModel.class);
    }

    private Doc saveDoc(DocOnServerModel doc) {
        return jsonApi.executeGet(EndPoints.SAVE_DOCS, doc.getParamsToSave(), DocFile.class).getDoc();
    }
}
