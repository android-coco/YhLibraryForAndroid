
package org.yh.yhframe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.yh.library.YHFragment;

/**
 * 具有ActionBar的Activity的基类
 *
 *
 */
public abstract class BaseFragment extends YHFragment
{

    /**
     * 封装一下方便一起返回(JAVA没有结构体这么一种东西实在是个遗憾)
     *
     * @author kymjs (http://www.kymjs.com/)
     */
    public class ActionBarRes
    {
        public CharSequence title;
        public int backImageId;
        public Drawable backImageDrawable;
        public int menuImageId;
        public Drawable menuImageDrawable;
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
        setTitle(actionBarRes.title);
        if (actionBarRes.backImageId == 0)
        {
            setBackImage(actionBarRes.backImageDrawable);
        }
        else
        {
            setBackImage(actionBarRes.backImageId);
        }
        if (actionBarRes.menuImageId == 0)
        {
            setMenuImage(actionBarRes.menuImageDrawable);
        }
        else
        {
            setMenuImage(actionBarRes.menuImageId);
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
    public void onBackClick()
    {
    }

    /**
     * 当ActionBar上的菜单键被按下时
     */
    public void onMenuClick()
    {
    }

    /**
     * 设置标题
     *
     * @param text
     */
    protected void setTitle(CharSequence text)
    {
        if (outsideAty != null)
        {
            outsideAty.mTvTitle.setText(text);
        }
    }

    /**
     * 设置返回键图标
     */
    protected void setBackImage(int resId)
    {
        if (outsideAty != null)
        {
            //outsideAty.mImgBack.setImageResource(resId);
        }
    }

    /**
     * 设置返回键图标
     */
    protected void setBackImage(Drawable drawable)
    {
        if (outsideAty != null)
        {
            //outsideAty.mImgBack.setImageDrawable(drawable);
        }
    }

    /**
     * 设置菜单键图标
     */
    protected void setMenuImage(int resId)
    {
        if (outsideAty != null)
        {
            //outsideAty.mImgMenu.setImageResource(resId);
        }
    }

    /**
     * 设置菜单键图标
     */
    protected void setMenuImage(Drawable drawable)
    {
        if (outsideAty != null)
        {
            //outsideAty.mImgMenu.setImageDrawable(drawable);
        }
    }
}