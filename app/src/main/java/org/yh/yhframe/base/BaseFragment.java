
package org.yh.yhframe.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import org.yh.library.YHActivity;
import org.yh.library.YHFragment;
import org.yh.library.ui.I_PermissionListener;
import org.yh.library.utils.StringUtils;
import org.yh.yhframe.app.MyApplication;

import java.util.ArrayList;
import java.util.List;

import static org.yh.library.utils.SystemUtils.isGranted;

/**
 * 具有ActionBar的Activity的基类
 */
public abstract class BaseFragment extends YHFragment
{

    /**
     * 封装一下方便一起返回(JAVA没有结构体这么一种东西实在是个遗憾)
     *
     * @author yh (https://github.com/android-coco)
     */
    public class ActionBarRes
    {
        public CharSequence title;
        public int leftImageId;
        public Drawable leftImageDrawable;
        public int mainImageId;
        public Drawable mainImageDrawable;
        public int rightImageId;
        public Drawable rightImageDrawable;
    }

    private final ActionBarRes actionBarRes = new ActionBarRes();
    protected BaseActiciy outsideAty;
    protected MyApplication app;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        if (getActivity() instanceof BaseActiciy)
        {
            outsideAty = (BaseActiciy) getActivity();
        }
        app = MyApplication.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setActionBarRes(actionBarRes);
        if (!StringUtils.isEmpty(actionBarRes.title))
        {
            setMainTitle(actionBarRes.title);
        }else
        {
            setMainTitle("标题");
        }
        if (actionBarRes.leftImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.leftImageDrawable))
            {
                seLeftImage(actionBarRes.leftImageDrawable);
            }
        } else
        {
            seLeftImage(actionBarRes.leftImageId);
        }
        if (actionBarRes.mainImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.mainImageDrawable))
            {
                setMianImage(actionBarRes.mainImageDrawable);
            }
        } else
        {
            setMianImage(actionBarRes.mainImageId);
        }

        if (actionBarRes.rightImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.rightImageDrawable))
            {
                setRightImage(actionBarRes.rightImageDrawable);
            }
        } else
        {
            setRightImage(actionBarRes.rightImageId);
        }
    }

    @Override
    public void onChange()
    {
        super.onChange();
        setActionBarRes(actionBarRes);
        if (!StringUtils.isEmpty(actionBarRes.title))
        {
            setMainTitle(actionBarRes.title);
        }else
        {
            setMainTitle("标题");
        }
        if (actionBarRes.leftImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.leftImageDrawable))
            {
                seLeftImage(actionBarRes.leftImageDrawable);
            }
        } else
        {
            seLeftImage(actionBarRes.leftImageId);
        }
        if (actionBarRes.mainImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.mainImageDrawable))
            {
                setMianImage(actionBarRes.mainImageDrawable);
            }
        } else
        {
            setMianImage(actionBarRes.mainImageId);
        }

        if (actionBarRes.rightImageId == 0)
        {
            if (!StringUtils.isEmpty(actionBarRes.rightImageDrawable))
            {
                setRightImage(actionBarRes.rightImageDrawable);
            }
        } else
        {
            setRightImage(actionBarRes.rightImageId);
        }
    }
    /**
     * 权限申请
     *
     * @param permissions 待申请的权限集合
     * @param listener    申请结果监听事件
     */
    protected void requestRunTimePermission(String[] permissions, I_PermissionListener listener)
    {
        outsideAty.mlistener = listener;
        //用于存放为授权的权限
        List<String> permissionList = new ArrayList<>();
        //遍历传递过来的权限集合
        for (String permission : permissions)
        {
            //判断是否已经授权
            if (!isGranted(aty, permission))
            {
                //未授权，则加入待授权的权限集合中
                permissionList.add(permission);
            }
        }
        //判断集合
        if (!StringUtils.isEmpty(permissionList))
        {  //如果集合不为空，则需要去授权
            ActivityCompat.requestPermissions(aty, permissionList.toArray(new String[permissionList.size()]), YHActivity.REQUST_CODE);
        } else
        {  //为空，则已经全部授权
            listener.onSuccess();
        }
    }
    /**
     * 方便Fragment中设置ActionBar资源
     *
     * @param actionBarRes
     */
    protected void setActionBarRes(ActionBarRes actionBarRes)
    {
    }

    /**
     * 当ActionBar上的返回键被按下时
     */
    protected void onBackClick()
    {
        if (null != outsideAty)
        {
            outsideAty.onBackClick();
        }
    }

    /**
     * 当ActionBar上的菜单键被按下时
     */
    protected void onMenuClick()
    {
        if (null != outsideAty)
        {
            outsideAty.onMenuClick();
        }
    }

    /**
     * 设置标题
     *
     * @param text
     */
    protected void setMainTitle(CharSequence text)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setMainTitle(text);
        }
    }

    /**
     * 设置左边键图标
     */
    protected void seLeftImage(int resId)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setLeftTitleDrawable(resId);
        }
    }

    /**
     * 设置左边键图标
     */
    protected void seLeftImage(Drawable drawable)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setLeftTitleDrawable(drawable);
        }
    }

    /**
     * 设置标题右边图标
     */
    protected void setRightImage(int resId)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setRightTitleDrawable(resId);
        }
    }

    /**
     * 设置标题右边图标
     */
    protected void setRightImage(Drawable drawable)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setRightTitleDrawable(drawable);
        }
    }


    /**
     * 设置中间图标
     */
    protected void setMianImage(int resId)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setMainTitleDrawable(resId);
        }
    }

    /**
     * 设置中间图标
     */
    protected void setMianImage(Drawable drawable)
    {
        if (outsideAty != null)
        {
            outsideAty.toolbar.setMainTitleDrawable(drawable);
        }
    }
}
