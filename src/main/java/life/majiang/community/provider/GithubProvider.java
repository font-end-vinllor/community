package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 该注解仅仅是将该类初始化到spring的上下文
@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDto dto){
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (
                Response response = client.newCall(request).execute()) {
                String s = response.body().string();
            String s1 = s.split("&")[0].split("=")[1];
                return s1;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {

        }
        return null;
    }
}
