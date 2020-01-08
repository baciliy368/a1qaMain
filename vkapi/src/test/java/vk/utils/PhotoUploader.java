package vk.utils;

import framework.utils.ModelGenerator;
import models.PhotoModel;
import models.api.ParamRequestModel;
import models.api.PhotoOnServerModel;
import models.api.UploadServer;
import vk.api.JsonApi;
import vk.enums.EndPoints;
import java.io.File;

public class PhotoUploader {
    private JsonApi jsonApi;

    public PhotoUploader(JsonApi jsonApi) {
        this.jsonApi = jsonApi;
    }

    public PhotoModel getPhotoOnServer(File doc, ParamRequestModel params) {
        String linkToUploadFile = getLinkToUploadFile(params);
        PhotoModel[] photoModels = savePhoto(uploadPhotoOnWall(linkToUploadFile, doc));
        return photoModels[photoModels.length - 1];
    }

    private String getLinkToUploadFile(ParamRequestModel params) {
        return jsonApi.executeGet(EndPoints.PHOTOS_WALL_UPLOAD_SERVER, params, UploadServer.class).getUploadUrl();
    }

    private PhotoOnServerModel uploadPhotoOnWall(String urlToUpload, File photo) {
        return ModelGenerator.getModelByMapping(jsonApi.uploadFileByUrl(urlToUpload, photo), PhotoOnServerModel.class);
    }

    private PhotoModel[] savePhoto(PhotoOnServerModel photo) {
        return jsonApi.executeGet(EndPoints.SAVE_PHOTO, photo.getParamsToSave(), PhotoModel[].class);
    }
}
