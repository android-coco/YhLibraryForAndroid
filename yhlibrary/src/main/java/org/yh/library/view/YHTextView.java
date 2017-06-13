package org.yh.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.yh.library.R;


/**
 * 万能的textView
 * 作者：yh on 2017/6/12 16:57
 * 邮箱：yh_android@163.com
 */
public class YHTextView extends RelativeLayout
{
    private Context mContext;
    private int defaultBgColor = 0xFFFFFFFF;//默认背景颜色
    // private int defaultLineColor = 0xFFE8E8E8;//线的默认颜色
    //private int defaultLineColor = 0xFF535353;//文字默认颜色
    private int defaultLinePadding = 0;//线的左右边距

    private ImageView leftIconIV;//左边图标
    private ImageView rightIconIV;//右边图标
    private CheckBox rightCheckBox;//右边checkbox
    private Drawable rightCheckBoxBg;//checkBox的背景

    private TextView leftTV;//左边textView
    private TextView centerTV;//中间textView
    private TextView rightTV;//右边textView

    private TextView leftTopTV;//左上的textView
    private TextView leftBottomTV;//左下的textView
    private TextView leftBottomTV2;//左下第二个textView


    private Drawable leftIconRes;//左边图标资源
    private Drawable rightIconRes;//右边图标资源
    private String leftTextString;//左边显示的文字
    private String centerTextString;//中间显示的文字
    private String rightTextString;//右边显示的文字
    private String leftTopTextString;//左上显示的文字
    private String leftBottomTextString;//左下显示的文字
    private String leftBottomTextString2;//左下第二个显示的文字


    private int defaultPadding = 0;//默认边距

    private int centerSpaceHeight;//中间空间的高度

    private int bothLineWidth;
    private int topLineWidth;
    private int bottomLineWidth;
    private int lineColor = 0xFFE8E8E8;//线的默认颜色

    private int topLineMargin;//上边线的左右边距
    private int topLineMarginLeft;//上边线的左边距
    private int topLineMarginRight;//上边线的右边距

    private int bottomLineMargin;//下边线的左右边距
    private int bottomLineMarginLeft;//下边线的左边距
    private int bottomLineMarginRight;//下边线的右边距

    private int bothLineMargin;//两条线的左右边距
    private int bothLineMarginLeft;//两条线的左边距
    private int bothLineMarginRight;//两条线的右边距

    private int leftIconMarginLeft;//左边图标的左边距

    private int leftTVMarginLeft;//左边文字的左边距

    private int leftIconWidth;//左边图标的宽
    private int leftIconHeight;//左边图标的高

    private int rightIconWidth;//右边图标的宽
    private int rightIconHeight;//右边图标的高

    private int leftTopMarginLeft;//左上文字的左边距
    private int leftBottomMarginLeft;//左下文字的左边距
    private int leftBottomMarginLeft2;//左下第二个文字的左边距

    private int rightTVMarginRight;//右边文字的右边距
    private int rightIconMarginRight;//右边图标的右边距
    private int rightCheckBoxMarginRight;//右边checkBox的右边距
    private boolean showCheckBox;//是否显示右边选择框
    private boolean isChecked;//是否默认选中

    private int defaultSize = 0;//默认字体大小

    private int leftTVSize;//左边文字字体大小
    private int leftTopTVSize;//左上文字字体大小
    private int leftBottomTVSize;//左下文字字体大小
    private int leftBottomTVSize2;//左下第二个文字字体大小
    private int rightTVSize;//右边文字字体大小
    private int centerTVSize;//中间文字字体大小


    private int defaultColor = 0xFF373737;//文字默认颜色

    private int backgroundColor;//背景颜色
    private int leftTVColor;//左边文字颜色
    private int leftTopTVColor;//左上文字颜色
    private int leftBottomTVColor;//左下文字颜色
    private int leftBottomTVColor2;//左下第二个文字颜色
    private int rightTVColor;//右边文字颜色
    private int centerTVColor;//中间文字颜色

    private boolean isSingLines = true;//是否单行显示   默认单行
    private int maxLines = 1;//最多几行    默认显示一行
    private int maxEms = 10;//最多几个字    默认显示10个汉子

    private static final int NONE = 0;
    private static final int TOP = 1;
    private static final int BOTTOM = 2;
    private static final int BOTH = 3;
    private static final int DEFAULT = BOTTOM;

    public static final int leftTextViewId = 0;
    public static final int leftTopTextViewId = 1;
    public static final int leftBottomTextViewId = 2;
    public static final int leftBottomTextViewId2 = 3;
    public static final int rightTextViewId = 4;
    public static final int centerTextViewId = 5;
    public static final int leftImageViewId = 6;
    public static final int rightImageViewId = 7;

    private boolean useRipple;

    private int lineType;
    private LayoutParams centerBaseLineParams, topLineParams, bottomLineParams, leftImgParams, leftTextParams, centerTextParams, leftTopTextParams, leftBottomParams,
            leftBottomParams2, rightTextParams, rightImgParams, rightCheckBoxParams;

    private OnSuperTextViewClickListener onSuperTextViewClickListener;
    private Drawable rightTextStringRightIconRes;
    private int rightTextStringRightIconPadding;

    private boolean mLeftTopViewIsClickable = false;
    private boolean mLeftBottomViewIsClickable = false;
    private boolean mLeftBottomView2IsClickable = false;

    private Drawable mBackground_drawable;

    public YHTextView(Context context) {
        super(context);
    }

    public YHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        defaultLinePadding = dip2px(context, 16);
        defaultPadding = dip2px(context, 16);
        defaultSize = sp2px(context, 14);
        centerSpaceHeight = dip2px(context, 10);
        getAttr(attrs);

        initLayout();

    }

    /**
     * 获取自定义属性值
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.YHTextView);

        ////////设置文字或者图片资源////////
        leftIconRes = typedArray.getDrawable(R.styleable.YHTextView_sLeftIconRes);
        rightIconRes = typedArray.getDrawable(R.styleable.YHTextView_sRightIconRes);
        rightCheckBoxBg = typedArray.getDrawable(R.styleable.YHTextView_sRightCheckBoxRes);

        leftTextString = typedArray.getString(R.styleable.YHTextView_sLeftTextString);
        centerTextString = typedArray.getString(R.styleable.YHTextView_sCenterTextString);
        rightTextString = typedArray.getString(R.styleable.YHTextView_sRightTextString);

        rightTextStringRightIconRes = typedArray.getDrawable(R.styleable.YHTextView_sRightTextStringRightIconRes);
        rightTextStringRightIconPadding = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightTextStringRightIconResPadding, dip2px(mContext, 5));

        leftTopTextString = typedArray.getString(R.styleable.YHTextView_sLeftTopTextString);
        leftBottomTextString = typedArray.getString(R.styleable.YHTextView_sLeftBottomTextString);
        leftBottomTextString2 = typedArray.getString(R.styleable.YHTextView_sLeftBottomTextString2);

        showCheckBox = typedArray.getBoolean(R.styleable.YHTextView_sRightCheckBoxShow, false);
        isChecked = typedArray.getBoolean(R.styleable.YHTextView_sIsChecked, false);
        useRipple = typedArray.getBoolean(R.styleable.YHTextView_sUseRipple, false);

        lineType = typedArray.getInt(R.styleable.YHTextView_sLineShow, DEFAULT);

        /////////设置view的边距////////
        centerSpaceHeight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sCenterSpaceHeight, centerSpaceHeight);

        bothLineWidth = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBothLineWidth, dip2px(mContext, 0.5f));
        topLineWidth = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sTopLineWidth, dip2px(mContext, 0.5f));
        bottomLineWidth = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBottomLineWidth, dip2px(mContext, 0.5f));

        lineColor = typedArray.getColor(R.styleable.YHTextView_sLineColor, lineColor);

        topLineMargin = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sTopLineMargin, defaultLinePadding);
        topLineMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sTopLineMarginLeft, defaultLinePadding);
        topLineMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sTopLineMarginRight, defaultLinePadding);

        bottomLineMargin = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBottomLineMargin, defaultLinePadding);
        bottomLineMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBottomLineMarginLeft, defaultLinePadding);
        bottomLineMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBottomLineMarginRight, defaultLinePadding);

        bothLineMargin = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBothLineMargin, defaultLinePadding);
        bothLineMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBothLineMarginLeft, defaultLinePadding);
        bothLineMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sBothLineMarginRight, defaultLinePadding);

        leftIconMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftIconMarginLeft, defaultPadding);
        leftTVMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftTextMarginLeft, defaultPadding);

        leftTopMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftTopTextMarginLeft, defaultPadding);
        leftBottomMarginLeft = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftBottomTextMarginLeft, defaultPadding);
        leftBottomMarginLeft2 = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftBottomTextMarginLeft2, defaultPadding);
        rightTVMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightTextMarginRight, defaultPadding);
        rightIconMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightIconMarginRight, defaultPadding);
        rightCheckBoxMarginRight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightCheckBoxMarginRight, defaultPadding);
        //////设置字体颜色////////
        backgroundColor = typedArray.getColor(R.styleable.YHTextView_sBackgroundColor, defaultBgColor);
        leftTVColor = typedArray.getColor(R.styleable.YHTextView_sLeftTextColor, defaultColor);
        leftTopTVColor = typedArray.getColor(R.styleable.YHTextView_sLeftTopTextColor, defaultColor);
        leftBottomTVColor = typedArray.getColor(R.styleable.YHTextView_sLeftBottomTextColor, defaultColor);
        leftBottomTVColor2 = typedArray.getColor(R.styleable.YHTextView_sLeftBottomTextColor2, defaultColor);
        rightTVColor = typedArray.getColor(R.styleable.YHTextView_sRightTextColor, defaultColor);
        centerTVColor = typedArray.getColor(R.styleable.YHTextView_sCenterTextColor, defaultColor);
        //////设置字体大小////////
        leftTVSize = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftTextSize, defaultSize);
        leftTopTVSize = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftTopTextSize, defaultSize);
        leftBottomTVSize = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftBottomTextSize, defaultSize);
        leftBottomTVSize2 = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftBottomTextSize2, defaultSize);
        rightTVSize = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightTextSize, defaultSize);
        centerTVSize = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sCenterTextSize, defaultSize);

        ///////设置textView的属性///////////SuperTextViewxEms
        isSingLines = typedArray.getBoolean(R.styleable.YHTextView_sIsSingLines, isSingLines);
        maxLines = typedArray.getInt(R.styleable.YHTextView_sMaxLines, maxLines);
        maxEms = typedArray.getInt(R.styleable.YHTextView_sMaxEms, maxEms);

        leftIconWidth = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftIconWidth, 0);
        leftIconHeight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sLeftIconHeight, 0);

        rightIconWidth = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightIconWidth, 0);
        rightIconHeight = typedArray.getDimensionPixelSize(R.styleable.YHTextView_sRightIconHeight, 0);

        mLeftTopViewIsClickable = typedArray.getBoolean(R.styleable.YHTextView_sLeftTopViewIsClickable, false);
        mLeftBottomViewIsClickable = typedArray.getBoolean(R.styleable.YHTextView_sLeftBottomViewIsClickable, false);
        mLeftBottomView2IsClickable = typedArray.getBoolean(R.styleable.YHTextView_sLeftBottomView2IsClickable, false);

        mBackground_drawable = typedArray.getDrawable(R.styleable.YHTextView_sBackgroundDrawableRes);

        typedArray.recycle();
    }

    /**
     * 初始化布局
     */
    private void initLayout() {

        initSuperTextView();
        initCenterBaseLine();

        if (leftIconRes != null) {
            initLeftIcon();
        }
        if (leftTopTextString != null) {
            initLeftTopText();
        }
        if (leftBottomTextString != null) {
            initLeftBottomText();
        }
        if (leftBottomTextString2 != null) {
            initLeftBottomText2();
        }
        if (leftTextString != null) {
            initLeftText();
        }
        if (centerTextString != null) {
            initCenterText();
        }
        if (rightIconRes != null) {
            initRightIcon();
        }
        if (rightTextString != null || rightTextStringRightIconRes != null) {
            initRightText();
        }
        if (showCheckBox) {
            initRightCheckBox();
        }

        switch (lineType) {
            case NONE:
                break;
            case TOP:
                setTopLineMargin();
                break;
            case BOTTOM:
                setBottomLineMargin();
                break;
            case BOTH:
                setTopLineMargin();
                setBottomLineMargin();
                break;
        }
    }


    /**
     * 设置顶部分割线的左右边距
     */
    private void setTopLineMargin() {
        if (topLineMargin != 0) {
            initTopLine(topLineMargin, topLineMargin, topLineWidth);
        } else if (bothLineMarginLeft != 0 | bothLineMarginRight != 0) {
            initTopLine(bothLineMarginLeft, bothLineMarginRight, topLineWidth);
        } else {
            initTopLine(topLineMarginLeft, topLineMarginRight, topLineWidth);
        }
    }

    /**
     * 设置底部分割线的左右边距
     */
    private void setBottomLineMargin() {
        if (bottomLineMargin != 0) {
            initBottomLine(bottomLineMargin, bottomLineMargin, bottomLineWidth);
        } else if (bothLineMarginLeft != 0 | bothLineMarginRight != 0) {
            initBottomLine(bothLineMarginLeft, bothLineMarginRight, topLineWidth);
        } else {
            initBottomLine(bottomLineMarginLeft, bottomLineMarginRight, topLineWidth);
        }
    }

    /**
     * 初始化上边的线
     */
    private void initTopLine(int lineMarginLeft, int lineMarginRight, int lineWidth) {
        View topLine = new View(mContext);
        topLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, lineWidth);
        topLineParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
        topLineParams.setMargins(lineMarginLeft, 0, lineMarginRight, 0);
        topLine.setLayoutParams(topLineParams);
        topLine.setBackgroundColor(lineColor);
        addView(topLine);
    }

    /**
     * 初始化下边的线
     */
    private void initBottomLine(int lineMarginLeft, int lineMarginRight, int lineWidth) {
        View bottomLine = new View(mContext);
        bottomLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, lineWidth);
        bottomLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
        bottomLineParams.setMargins(lineMarginLeft, 0, lineMarginRight, 0);
        bottomLine.setLayoutParams(bottomLineParams);
        bottomLine.setBackgroundColor(lineColor);
        addView(bottomLine);
    }

    /**
     * 初始化SuperTextView
     */
    private void initSuperTextView() {

        this.setBackgroundColor(backgroundColor);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onSuperTextViewClick();
                }
            }
        });

        if (useRipple) {
            this.setBackgroundResource(R.drawable.touchable_background_white);
        }
        if (mBackground_drawable != null) {
            this.setBackgroundDrawable(mBackground_drawable);
        }
    }


    /**
     * 为了设置上下两排文字居中对齐显示而需要设置的基准线
     */
    private void initCenterBaseLine() {
        View view = new View(mContext);
        centerBaseLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, centerSpaceHeight);
        centerBaseLineParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        view.setId(R.id.sCenterBaseLineId);
        view.setLayoutParams(centerBaseLineParams);
        addView(view);
    }


    /**
     * 初始化左边图标
     */
    private void initLeftIcon() {
        leftIconIV = new ImageView(mContext);
        leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftImgParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (leftIconHeight != 0 && leftIconWidth != 0) {
            leftImgParams.width = leftIconWidth;
            leftImgParams.height = leftIconHeight;
        }
        setMargin(leftImgParams, leftIconMarginLeft, 0, 0, 0);
        leftIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        leftIconIV.setId(R.id.sLeftIconId);
        leftIconIV.setLayoutParams(leftImgParams);
        if (leftIconRes != null) {
            leftIconIV.setImageDrawable(leftIconRes);
        }
        addView(leftIconIV);
    }

    /**
     * 初始化左边文字
     */
    private void initLeftText() {
        leftTV = new TextView(mContext);
        leftTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        leftTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftTextParams, leftTVMarginLeft, 0, dip2px(mContext, 10), 0);
        leftTV.setId(R.id.sLeftTextId);
        leftTV.setLayoutParams(leftTextParams);
        leftTV.setText(leftTextString);

        setTextViewParams(leftTV, isSingLines, maxLines, maxEms);

        setTextColor(leftTV, leftTVColor);
        setTextSize(leftTV, leftTVSize);
        addView(leftTV);
    }

    /**
     * 设置通用的textView显示效果属性
     *
     * @param textView    view
     * @param isSingLines 是否单行显示
     * @param maxLines    显示最大行
     * @param maxEms      最多显示多少个字
     */
    private void setTextViewParams(TextView textView, boolean isSingLines, int maxLines, int maxEms) {
        textView.setSingleLine(isSingLines);
        textView.setMaxLines(maxLines);
        textView.setMaxEms(maxEms);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 初始化左上文字
     */
    private void initLeftTopText() {
        leftTopTV = new TextView(mContext);
        leftTopTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftTopTextParams.addRule(RelativeLayout.ABOVE, R.id.sCenterBaseLineId);
        leftTopTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftTopTextParams, leftTopMarginLeft, 0, 0, 0);
        leftTopTV.setId(R.id.sLeftTopTextId);
        leftTopTV.setLayoutParams(leftTopTextParams);
        leftTopTV.setText(leftTopTextString);
        setTextColor(leftTopTV, leftTopTVColor);
        setTextSize(leftTopTV, leftTopTVSize);
        if (mLeftTopViewIsClickable) {
            leftTopTV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSuperTextViewClickListener != null) {
                        onSuperTextViewClickListener.onLeftTopClick();
                    }
                }
            });
        }
        setTextViewParams(leftTopTV, isSingLines, maxLines, maxEms);
        addView(leftTopTV);
    }

    /**
     * 初始化左下文字
     */
    private void initLeftBottomText() {
        leftBottomTV = new TextView(mContext);
        leftBottomParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftBottomParams.addRule(RelativeLayout.BELOW, R.id.sCenterBaseLineId);
        leftBottomParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftIconId);
        setMargin(leftBottomParams, leftBottomMarginLeft, 0, 0, 0);
        leftBottomTV.setId(R.id.sLeftBottomTextId);
        leftBottomTV.setLayoutParams(leftBottomParams);
        leftBottomTV.setText(leftBottomTextString);
        setTextColor(leftBottomTV, leftBottomTVColor);
        setTextSize(leftBottomTV, leftBottomTVSize);
        if (mLeftBottomViewIsClickable) {
            leftBottomTV.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSuperTextViewClickListener != null) {
                        onSuperTextViewClickListener.onLeftBottomClick();
                    }
                }
            });
        }
        setTextViewParams(leftBottomTV, isSingLines, maxLines, maxEms);
        addView(leftBottomTV);
    }

    /**
     * 初始化左下第二个文字
     */
    private void initLeftBottomText2() {
        leftBottomTV2 = new TextView(mContext);
        leftBottomParams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftBottomParams2.addRule(RelativeLayout.BELOW, R.id.sCenterBaseLineId);
        leftBottomParams2.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftBottomTextId);
        setMargin(leftBottomParams2, leftBottomMarginLeft2, 0, 0, 0);
        leftBottomTV2.setId(R.id.sLeftBottomTextId2);
        leftBottomTV2.setLayoutParams(leftBottomParams2);
        leftBottomTV2.setText(leftBottomTextString2);
        setTextColor(leftBottomTV2, leftBottomTVColor2);
        setTextSize(leftBottomTV2, leftBottomTVSize2);
        if (mLeftBottomView2IsClickable) {
            leftBottomTV2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSuperTextViewClickListener != null) {
                        onSuperTextViewClickListener.onLeftBottomClick2();
                    }
                }
            });
        }
        setTextViewParams(leftBottomTV2, isSingLines, maxLines, maxEms);
        addView(leftBottomTV2);
    }

    /**
     * 初始化中间文字
     */
    private void initCenterText() {
        centerTV = new TextView(mContext);
        centerTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerTextParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        centerTV.setId(R.id.sCenterTextId);
        centerTV.setLayoutParams(centerTextParams);
        centerTV.setText(centerTextString);
        setTextColor(centerTV, centerTVColor);
        setTextSize(centerTV, centerTVSize);
        setTextViewParams(centerTV, isSingLines, maxLines, maxEms);
        addView(centerTV);
    }

    /**
     * 初始化右边文字
     */
    private void initRightText() {
        rightTV = new TextView(mContext);
        rightTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        rightTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftTextId);
        rightTextParams.addRule(RelativeLayout.LEFT_OF, R.id.sRightIconId);
        setMargin(rightTextParams, 0, 0, rightTVMarginRight, 0);
        rightTV.setId(R.id.sRightTextId);
        rightTV.setLayoutParams(rightTextParams);
        rightTV.setText(rightTextString);
        setTextColor(rightTV, rightTVColor);
        setTextSize(rightTV, rightTVSize);
        setTextViewRightDrawble(rightTV, rightTextStringRightIconRes, rightTextStringRightIconPadding);
        rightTV.setGravity(Gravity.RIGHT);
        setTextViewParams(rightTV, isSingLines, maxLines, maxEms);
        addView(rightTV);
    }

    /**
     * 初始化右边图标
     */
    private void initRightIcon() {
        rightIconIV = new ImageView(mContext);
        rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightImgParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (rightIconHeight != 0 && rightIconWidth != 0) {
            rightImgParams.width = rightIconWidth;
            rightImgParams.height = rightIconHeight;
        }
        setMargin(rightImgParams, 0, 0, rightIconMarginRight, 0);
        rightIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        rightIconIV.setId(R.id.sRightIconId);
        rightIconIV.setLayoutParams(rightImgParams);
        if (rightIconRes != null) {
            rightIconIV.setImageDrawable(rightIconRes);
        }
        addView(rightIconIV);
    }

    /**
     * 初始化右边选择框
     */
    private void initRightCheckBox() {
        rightCheckBox = new CheckBox(mContext);

        rightCheckBoxParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        rightCheckBoxParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightCheckBoxParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        setMargin(rightCheckBoxParams, 0, 0, rightCheckBoxMarginRight, 0);
        rightCheckBox.setLayoutParams(rightCheckBoxParams);
        if (rightCheckBoxBg != null) {
            rightCheckBox.setGravity(CENTER_IN_PARENT);
            rightCheckBox.setButtonDrawable(rightCheckBoxBg);
        }
        rightCheckBox.setChecked(isChecked);
        addView(rightCheckBox);
    }

    private void setMargin(LayoutParams params, int left, int top, int right, int bottom) {
        params.setMargins(left, top, right, bottom);
    }

    /**
     * 设置view的边距
     *
     * @param view   view对象
     * @param left   左边边距
     * @param top    上边边距
     * @param right  右边边距
     * @param bottom 下边边距
     */
    private void setPadding(View view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
    }

    /**
     * 设置文字的字体大小
     *
     * @param textView textView对象
     * @param size     文字大小
     */
    private void setTextSize(TextView textView, int size) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * 设置文字的颜色
     *
     * @param textView textView对象
     * @param color    文字颜色
     */
    private void setTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }

    //////////对外公布的方法///////////////

    /**
     * 设置左边图标
     *
     * @param leftIcon 左边图标
     * @return 返回对象
     */
    public YHTextView setLeftIcon(Drawable leftIcon) {
        leftIconRes = leftIcon;
        if (leftIconIV == null) {
            initLeftIcon();
        } else {
            leftIconIV.setImageDrawable(leftIcon);
        }
        return this;
    }

    /**
     * 设置右边图标
     *
     * @param rightIcon 右边图标
     * @return 返回对象
     */
    public YHTextView setRightIcon(Drawable rightIcon) {
        rightIconRes = rightIcon;
        if (rightIconIV == null) {
            initRightIcon();
        } else {
            rightIconIV.setImageDrawable(rightIcon);
        }
        return this;
    }

    /**
     * 设置左边显示文字
     *
     * @param leftString 左边文字
     * @return 返回对象
     */
    public YHTextView setLeftString(String leftString) {
        leftTextString = leftString;
        if (leftTV == null) {
            initLeftText();
        } else {
            leftTV.setText(leftString);
        }
        return this;
    }

    /**
     * 设置左上显示的文字
     *
     * @param leftTopString 左上文字
     * @return 返回对象
     */
    public YHTextView setLeftTopString(String leftTopString) {
        leftTopTextString = leftTopString;
        if (leftTopTV == null) {
            initLeftTopText();
        } else {
            leftTopTV.setText(leftTopString);
        }
        return this;
    }

    /**
     * 设置左下显示的文字
     *
     * @param leftBottomString 左下第一个文字
     * @return 返回对象
     */
    public YHTextView setLeftBottomString(String leftBottomString) {
        leftBottomTextString = leftBottomString;
        if (leftBottomTV == null) {
            initLeftBottomText();
        } else {
            leftBottomTV.setText(leftBottomString);
        }
        return this;
    }

    /**
     * 设置左下第二个显示的文字
     *
     * @param leftBottomString2 左下第二个文字
     * @return 返回对象
     */
    public YHTextView setLeftBottomString2(String leftBottomString2) {
        leftBottomTextString2 = leftBottomString2;
        if (leftBottomTV2 == null) {
            initLeftBottomText2();
        } else {
            leftBottomTV2.setText(leftBottomString2);
        }
        return this;
    }

    /**
     * 设置右边显示的文字
     *
     * @param rightString 右边文字
     * @return 返回对象
     */
    public YHTextView setRightString(String rightString) {
        rightTextString = rightString;
        if (rightTV == null) {
            initRightText();
        } else {
            rightTV.setText(rightString);
        }
        return this;
    }

    /**
     * 设置右边显示的文字和图片
     *
     * @param rightString     右边文字
     * @param drawable        drawable
     * @param drawablePadding drawablePadding
     * @return
     */
    public YHTextView setRightString(String rightString, Drawable drawable, int drawablePadding) {
        rightTextString = rightString;
        rightTextStringRightIconRes = drawable;
        rightTextStringRightIconPadding = drawablePadding;
        if (rightTV == null) {
            initRightText();
        } else {
            rightTV.setText(rightString);
        }
        return this;
    }


    /**
     * 设备中间文字
     *
     * @param centerString 中间文字
     * @return 返回对象
     */
    public YHTextView setCenterString(String centerString) {
        centerTextString = centerString;
        if (centerTV == null) {
            initCenterText();
        } else {
            centerTV.setText(centerString);
        }
        return this;
    }

    /**
     * @param checked 是否选中
     * @return 返回值
     */
    public YHTextView setCbChecked(boolean checked) {
        isChecked = checked;
        if (rightCheckBox == null) {
            initRightCheckBox();
        } else {
            rightCheckBox.setChecked(checked);
        }
        return this;
    }

    /**
     * 设置checkbox的背景图
     *
     * @param drawable drawable对象
     * @return 返回对象
     */
    public YHTextView setCbBackground(Drawable drawable) {
        rightCheckBoxBg = drawable;
        if (rightCheckBox == null) {
            initRightCheckBox();
        } else {
            rightCheckBox.setBackgroundDrawable(drawable);
        }
        return this;
    }

    /**
     * 获取checkbox状态
     *
     * @return 返回选择框当前选中状态
     */
    public boolean getCbisChecked() {
        boolean isChecked = false;
        if (rightCheckBox != null) {
            isChecked = rightCheckBox.isChecked();
        }
        return isChecked;
    }

    /**
     * 设置左边文字的颜色
     *
     * @param textColor 文字颜色值
     * @return 返回对象
     */
    public YHTextView setLeftTVColor(int textColor) {
        leftTVColor = textColor;
        if (leftTV == null) {
            initLeftText();
        } else {
            leftTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * 设置右边文字的颜色
     *
     * @param textColor 文字颜色值
     * @return 返回对象
     */
    public YHTextView setRightTVColor(int textColor) {
        rightTVColor = textColor;
        if (rightTV == null) {
            initRightText();
        } else {
            rightTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * 设置左上边文字的颜色
     *
     * @param textColor 文字颜色值
     * @return 返回对象
     */
    public YHTextView setLeftTopTVColor(int textColor) {
        leftTopTVColor = textColor;
        if (leftTopTV == null) {
            initLeftTopText();
        } else {
            leftTopTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * 设置左下边文字的颜色
     *
     * @param textColor 文字颜色值
     * @return 返回对象
     */
    public YHTextView setLeftBottomTVColor(int textColor) {
        leftBottomTVColor = textColor;
        if (leftBottomTV == null) {
            initLeftBottomText();
        } else {
            leftBottomTV.setTextColor(textColor);
        }
        return this;
    }

    /**
     * 设置左下第二个文字的颜色
     *
     * @param textColor 文字颜色值
     * @return 返回对象
     */
    public YHTextView setLeftBottomTVColor2(int textColor) {
        leftBottomTVColor2 = textColor;
        if (leftBottomTV2 == null) {
            initLeftBottomText2();
        } else {
            leftBottomTV2.setTextColor(textColor);
        }
        return this;
    }

    //////////设置View的点击事件/////////////////

    /**
     * 点击事件
     *
     * @param listener listener对象
     * @return 返回对象
     */
    public YHTextView setOnSuperTextViewClickListener(OnSuperTextViewClickListener listener) {
        onSuperTextViewClickListener = listener;
        return this;
    }

    /**
     * 点击事件接口
     */
    public static class OnSuperTextViewClickListener {
        public void onSuperTextViewClick() {
        }

        public void onLeftTopClick() {
        }

        public void onLeftBottomClick() {
        }

        public void onLeftBottomClick2() {
        }

    }

    /**
     * 获取控件ID便于根据ID设置值
     *
     * @param viewName 需要的textViewName
     * @return 返回ID
     */
    public int getViewId(int viewName) {
        int viewId = 0;
        switch (viewName) {
            case leftTextViewId:
                if (leftTV == null) {
                    initLeftText();
                }
                viewId = R.id.sLeftTextId;
                break;
            case leftTopTextViewId:
                if (leftTopTV == null) {
                    initLeftTopText();
                }
                viewId = R.id.sLeftTopTextId;
                break;
            case leftBottomTextViewId:
                if (leftBottomTV == null) {
                    initLeftBottomText();
                }
                viewId = R.id.sLeftBottomTextId;
                break;
            case leftBottomTextViewId2:
                if (leftBottomTV2 == null) {
                    initLeftBottomText2();
                }
                viewId = R.id.sLeftBottomTextId2;
                break;
            case centerTextViewId:
                if (centerTV == null) {
                    initCenterText();
                }
                viewId = R.id.sCenterTextId;
                break;
            case rightTextViewId:
                if (rightTV == null) {
                    initRightText();
                }
                viewId = R.id.sRightTextId;
                break;
            case leftImageViewId:
                if (leftIconIV == null) {
                    initLeftIcon();
                }
                viewId = R.id.sLeftIconId;
                break;
            case rightImageViewId:
                if (rightIconIV == null) {
                    initRightIcon();
                }
                viewId = R.id.sRightIconId;
                break;
        }
        return viewId;
    }

    /**
     * 获取view对象
     *
     * @param viewName 传入viewName
     * @return 返回view
     */
    public View getView(int viewName) {
        View view = null;
        switch (viewName) {

            case leftImageViewId:
                if (leftIconIV == null) {
                    initLeftIcon();
                }
                view = leftIconIV;
                break;
            case rightImageViewId:
                if (rightIconIV == null) {
                    initRightIcon();
                }
                view = rightIconIV;
                break;
        }
        return view;
    }


    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static void setTextViewRightDrawble(TextView textView, Drawable drawable, int drawablePadding) {
        if (drawable != null && textView != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
            textView.setCompoundDrawablePadding(drawablePadding);
        }
    }

    /**
     * 设置左上view可点击
     *
     * @param isClickable boolean类型
     * @return 返回
     */
    public YHTextView setLeftTopViewIsClickable(boolean isClickable) {
        if (isClickable) {
            if (leftTopTV != null) {
                leftTopTV.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onSuperTextViewClickListener != null) {
                            onSuperTextViewClickListener.onLeftTopClick();
                        }
                    }
                });
            }
        }

        return this;
    }

    /**
     * 设置左下第一个view可点击
     *
     * @param isClickable boolean类型
     * @return 返回
     */
    public YHTextView setLeftBottomViewIsClickable(boolean isClickable) {
        if (isClickable) {
            if (leftBottomTV != null) {
                leftBottomTV.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onSuperTextViewClickListener != null) {
                            onSuperTextViewClickListener.onLeftBottomClick();
                        }
                    }
                });
            }
        }
        return this;
    }

    /**
     * 设置左下第二个view可点击
     *
     * @param isClickable boolean类型
     * @return 返回
     */
    public YHTextView setLeftBottomView2IsClickable(boolean isClickable) {
        if (isClickable) {
            if (leftBottomTV2 != null) {
                leftBottomTV2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onSuperTextViewClickListener != null) {
                            onSuperTextViewClickListener.onLeftBottomClick2();
                        }
                    }
                });
            }
        }
        return this;
    }
}
