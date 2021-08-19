package top.lyijian.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;
import top.lyijian.community.dto.AccessTokenDto;
import top.lyijian.community.dto.GiteeUser;

import java.io.IOException;


@Component
public class GiteeProvider {

//    public String getAccessToken(AccessTokenDto accessTokenDto) {
//        OkHttpClient client = new OkHttpClient();
//        RequestBody formBody = new FormBody.Builder()
//                .add("code", accessTokenDto.getCode())
//                .add("client_id", accessTokenDto.getClient_id())
//                .add("client_secret", accessTokenDto.getClient_secret())
//                .add("redirect_uri", accessTokenDto.getRedirect_uri())
//                .add("grant_type", accessTokenDto.getGrant_type())
//                .build();
//        Request request = new Request.Builder()
//                .url("https://gitee.com/oauth/token")
//                .post(formBody)
//                .build();
//        Call call = client.newCall(request);
//        try {
//            Response response = call.execute();
//            String string = response.body().string();
//            System.out.println(string);
//            return string;
//        } catch (IOException e) {
//        }
//        return null;
//    }

    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String retToken = response.body().string();
            JSONObject jsonObject = JSON.parseObject(retToken);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (IOException e) {
        }
        return null;
    }

    public GiteeUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String string = response.body().string();
            GiteeUser giteeUser = JSON.parseObject(string, GiteeUser.class);
            return giteeUser;
        } catch (IOException e) {
        }
        return null;
    }

}
