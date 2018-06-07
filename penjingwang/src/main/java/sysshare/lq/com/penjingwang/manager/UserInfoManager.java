package sysshare.lq.com.penjingwang.manager;

import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;

import javax.crypto.spec.SecretKeySpec;

import sysshare.lq.com.penjingwang.bean.UserBean;
import sysshare.lq.com.penjingwang.common.Const;
import sysshare.lq.com.penjingwang.utils.AesEncryptionUtils;
import sysshare.lq.com.penjingwang.utils.PreUtils;

/**
 * author: 康栋普
 * date: 2018/3/1
 */

public class UserInfoManager {

    public static UserBean getUserInfo() {
        UserBean userBean = null;
        SecretKeySpec keySpec = getAesKey();
        String userInfo = AesEncryptionUtils.decrypt(keySpec, (String) PreUtils.get(Const.USERINFO_KEY.USER_INFO, ""));
        if (TextUtils.isEmpty(userInfo)) return null;
        userBean = new Gson().fromJson(userInfo, UserBean.class);
        return userBean;
    }
    public static void saveUserInfo(UserBean userBean){
        String userInfo = new Gson().toJson(userBean);
        SecretKeySpec key = AesEncryptionUtils.createKey();
        String aesContent = AesEncryptionUtils.encrypt(key, userInfo);
        //保存用户信息
        PreUtils.put(Const.USERINFO_KEY.USER_INFO, aesContent);
        //保存密钥
        saveAesKey(key);
    }

    public static void saveAesKey(SecretKeySpec keySpec){
        PreUtils.put(Const.USERINFO_KEY.AES, Base64.encodeToString(keySpec.getEncoded(),Base64.DEFAULT));
    }

    public static SecretKeySpec getAesKey(){
        String keyStr = (String) PreUtils.get(Const.USERINFO_KEY.AES, "");
        return AesEncryptionUtils.getSecretKey(Base64.decode(keyStr, Base64.DEFAULT));
    }

    public static boolean isLogin() {
        return (boolean) PreUtils.get(Const.USERINFO_KEY.IS_LOGIN, false);
    }

    public static void saveIsLogin(boolean isLogin){
        PreUtils.put(Const.USERINFO_KEY.IS_LOGIN,isLogin);
    }
}
