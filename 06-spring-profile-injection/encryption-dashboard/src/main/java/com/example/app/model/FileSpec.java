package com.example.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileSpec {
    private Integer id;
    private String filename;
    private byte[] fileContent; // WARN: 항상 byte[] 타입으로 변환 후 들어가야 함
    private String password; // WARN: 항상 SecretKey 타입으로 변환 후 들어가야 함

    public FileSpec() {
    };

    public Integer getId() {
        return id;
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFromJSON(String rawJson) throws Exception {
        if (rawJson == null)
            return;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(rawJson);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0); // 항상 첫 번째 json만 사용한다
        this.filename = (String) jsonObject.get("filename");
        this.password = (String) jsonObject.get("password");
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

}
