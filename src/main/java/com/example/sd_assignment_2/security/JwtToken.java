package com.example.sd_assignment_2.security;

import com.example.sd_assignment_2.business.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    public static String getJwtoken(User user){
        Map<String, Object> claims = new HashMap<String,Object>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        claims.put("password",user.getPassword());
        return Jwts.builder().setHeaderParam("alg","RS256").setHeaderParam("typ","JWT")
                .setClaims(claims)
                .compact();
    }

    public static User getUser(String token){
        try{
            String payload = token.split("\\.")[1];
            String json = new String(Base64.decodeBase64(payload),"UTF-8");
            JSONObject obj = new JSONObject(json);
            String password = obj.getString("password");
            String username = obj.getString("username");
            long id = obj.getLong("id");
            return new User(id,username,password);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
