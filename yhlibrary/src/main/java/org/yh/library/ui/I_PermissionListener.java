package org.yh.library.ui;

import java.util.List;

/**
 * 作者：38314 on 2017/8/11 16:16
 * 邮箱：yh_android@163.com
 * 权限管理
 */
public interface I_PermissionListener
{
    //授权成功
    void onSuccess();

    //授权部分
    void onGranted(List<String> grantedPermission);

    //拒绝授权
    void onFailure(List<String> deniedPermission);
}
