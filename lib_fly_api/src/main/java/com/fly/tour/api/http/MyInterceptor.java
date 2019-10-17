package com.fly.tour.api.http;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("POST")) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                // 先复制原来的参数
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                // 添加公共参数
//                formBody = bodyBuilder
//                        .addEncoded("version", "1.0.0")
//                        .addEncoded("appkey", "yeyuanxinyi")
//                        .addEncoded("timestamp", String.valueOf(System.currentTimeMillis()))
//                        .build();

                request = request.newBuilder().post(formBody).build();
            }
        }
        return chain.proceed(request);
    }
}
