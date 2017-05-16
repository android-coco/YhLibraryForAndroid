
package org.yh.yhframe.frame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.yh.library.YHFragment;
import org.yh.library.utils.StringUtils;
import org.yh.yhframe.app.MyApplication;

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
     * 方便Fragment中设置ActionBar资源
     *
     * @param actionBarRes
     * @return
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
