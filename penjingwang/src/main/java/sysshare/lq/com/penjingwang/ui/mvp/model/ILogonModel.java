package sysshare.lq.com.penjingwang.ui.mvp.model;


import sysshare.lq.com.penjingwang.bean.UserBean;
import sysshare.lq.com.penjingwang.net.callback.RxObserver;

public interface ILogonModel {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    void login(String username, String password, RxObserver<UserBean> callback);


    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     */
    void register(String username, String password, RxObserver<String> callback);


    /**
     * 保存用户信息
     * @param userBean
     */
    void saveUserInfo(UserBean userBean);

//    /**
//     * 检验帐号
//     * @param username
//     * @param password
//     * @return
//     */
//    boolean verifyAccount(String username, String password, VerifyAccountCallback callback);
}
