package com.example.projectbankend.DTO;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static Map<String, Object> response(Object data, int totalPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "thực hiện thành công");
        response.put("status", 200);
        response.put("data", data);
        response.put("pages", totalPage);
        return response;
    }

    public static Map<String, Object> responseWithoutData() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "thực hiện thành công");
        response.put("status", 200);
        return response;
    }
}
