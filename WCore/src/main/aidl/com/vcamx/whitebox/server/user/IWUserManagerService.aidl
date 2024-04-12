// IWUserManagerService.aidl
package com.vcamx.whitebox.server.user;

import com.vcamx.whitebox.server.user.WUserInfo;
import java.util.List;


interface IWUserManagerService {
    WUserInfo getUserInfo(int userId);
    boolean exists(int userId);
    WUserInfo createUser(int userId);
    List<WUserInfo> getUsers();
    void deleteUser(int userId);
}