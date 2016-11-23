package com.example.administrator.google.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 流式布局
 * Created by Administrator on 2016/7/28 0028.
 */
public class FlowLayout extends ViewGroup {
    private int horizontalSpacing = 15;//水平间距
    private int verticalSpacing = 15;//行与行之间的垂直间距
    private ArrayList<Line> lineList = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置水平间距
     *
     * @param horizontalSpacing
     */
    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    /**
     * 设置垂直间距
     *
     * @param verticalSpacing
     */
    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    //遍历所有的view判断哪几个view属于那几行
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取flowlayoue的width
        int flowWidth = MeasureSpec.getSize(widthMeasureSpec);
        //获取用于实际比较的宽
        int noPaddingWidth = flowWidth - getPaddingLeft() - getPaddingRight();
        Line line = new Line();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(0, 0);  //保证能够获取到宽高
            if (line.getViewList().size() == 0) {
                line.addLineView(childView);
            } else if (line.getLineWidth() + horizontalSpacing + childView.getMeasuredWidth() > noPaddingWidth) {
                lineList.add(line);
                line = new Line();
                line.addLineView(childView);
            } else {
                line.addLineView(childView);
            }
            //如果当前的view是最后一行的话那么要保存最后
            if (i == (getChildCount() - 1)) {
                lineList.add(line);
            }

        } //循环结束,每一个line都记录了自己的子view
        //计算FLowLayout需要的高度
        int height = getPaddingBottom() + getPaddingTop();
        for (int i = 0; i < lineList.size(); i++) {
            height += lineList.get(i).getLineHeighe();
        }
        height += (lineList.size() - 1) * verticalSpacing;
        setMeasuredDimension(flowWidth,height); //设置当前控件的宽高，或者向父VIew申请宽高

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i = 0; i < lineList.size(); i++) {
            Line line = lineList.get(i);
            //从第二行开始，每行的top总是比上一行的top多一个行高和垂直间距
            if (i > 0) {
                paddingTop += line.getLineHeighe() + verticalSpacing;
            }
            ArrayList<View> viewList = line.getViewList();//获取line的view的集合
            //获取每一行的留白
            int lineRemainSpacing = gettLineRemainSpacing(line);
            int perSpacing = lineRemainSpacing / viewList.size();
            for (int j = 0; j < viewList.size(); j++) {
                View childView = viewList.get(j);
                //3.将得到的perSpacing增加到view的宽度上面
                int widthSpec = MeasureSpec.makeMeasureSpec((int) perSpacing + childView.getMeasuredWidth(), MeasureSpec.EXACTLY);
                childView.measure(widthSpec, 0);
                //如果是每行的第一个,直接拜访,否则后边的那个根据前一个进行摆放
                if (j == 0) {
                    childView.layout(paddingLeft, paddingTop, paddingLeft + childView.getMeasuredWidth(), paddingTop + childView.getMeasuredHeight());

                } else {
                    View preView = viewList.get(j - 1);
                    int left = preView.getRight() + horizontalSpacing;
                    childView.layout(left, preView.getTop(), left + childView.getMeasuredWidth(), preView.getBottom());
                }

            }
        }
    }

    /**
     * 获取指定line的留白
     *
     * @param
     * @return
     */
    public int gettLineRemainSpacing(Line line) {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - line.getLineWidth();

    }

    class Line {
        public int lineHeiht;
        public int lineWidth;
        public ArrayList<View> viewList;  //存放子view

        public Line() {
            viewList = new ArrayList<>();
        }

        public void addLineView(View child) {
            if (!viewList.contains(child)) {
                viewList.add(child);
                if (viewList.size() == 1) {
                    lineWidth = child.getMeasuredWidth();
                } else {
                    lineWidth += child.getMeasuredWidth() + horizontalSpacing;
                }

                lineHeiht = Math.max(lineHeiht, child.getMeasuredHeight());
            }
        }

        public int getLineHeighe() {
            return lineHeiht;
        }

        public int getLineWidth() {
            return lineWidth;
        }

        /**
         * 获取当前行的所有的子View
         *
         * @return
         */
        public ArrayList<View> getViewList() {
            return viewList;
        }
    }
}

