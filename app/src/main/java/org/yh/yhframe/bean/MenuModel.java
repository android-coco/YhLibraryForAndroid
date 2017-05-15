package org.yh.yhframe.bean;

import com.google.gson.annotations.SerializedName;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;

import org.yh.library.bean.YHModel;

/**
 * Created by yhlyl on 2017/5/13.
 */
@Table("_menu")
public class MenuModel extends YHModel
{
    public static final String BASE_IMG_URL = "http://image.51efan.com";
    public static final String COL_MENUID = "_menuid";
    public static final String COL_NAME = "_menuname";
    public static final String COL_PIC = "_pic";
    public static final String COL_PRICE = "_price";
    @Column(COL_MENUID)
    @SerializedName("menuid")
    private String menuid;//菜品ID
    @Column(COL_NAME)
    @SerializedName("menuname")
    private String menuname;//菜品名称
    @Column(COL_PIC)
    @SerializedName("pic")
    private String pic;//菜品图片
    @Column(COL_PRICE)
    @SerializedName("price")
    private String price;//菜品价钱

    public MenuModel(String menuid, String menuname, String pic, String price)
    {
        this.menuid = menuid;
        this.menuname = menuname;
        this.pic = pic;
        this.price = price;
    }

    public String getMenuid()
    {
        return menuid;
    }

    public void setMenuid(String menuid)
    {
        this.menuid = menuid;
    }

    public String getMenuname()
    {
        return menuname;
    }

    public void setMenuname(String menuname)
    {
        this.menuname = menuname;
    }

    public String getPic()
    {
        return BASE_IMG_URL + pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "MenuModel{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                "} " ;
    }
}
