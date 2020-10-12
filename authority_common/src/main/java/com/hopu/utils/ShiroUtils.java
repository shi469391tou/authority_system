package com.hopu.utils;

import com.hopu.domain.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

public class ShiroUtils {

	public static User encPass(User user){
		if (StringUtils.isEmpty(user.getSalt())) {
			user.setSalt(UUIDUtils.getID());
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName() + user.getSalt());
        Object obj = new SimpleHash(ConstantUtils.MD5, user.getPassword(), credentialsSalt, ConstantUtils.HASHITERATIONS);
        user.setPassword(obj.toString());
        return user;
	}

	public static void main(String[] args) {
		String salt = UUIDUtils.getID();
        String username = "root";
        String hashAlgorithmName = "MD5";
        String password = "root";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username + salt);
        Object obj = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
        System.out.println("salt："+ salt);
    }
}
