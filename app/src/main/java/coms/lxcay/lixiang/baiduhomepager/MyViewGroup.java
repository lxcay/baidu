package coms.lxcay.lixiang.baiduhomepager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewGroup extends ViewGroup {
    private Scroller mScroller;

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View headerView = getChildAt(0);
        int height = headerView.getMeasuredHeight();
        headerView.layout(l, -height, r, 0);//这里的l，t，r，b，对应的是屏幕的左上右下,所以高为负数，默认不显示，底边在屏幕上

        View contentView = getChildAt(1);
        contentView.layout(l, t, r, b);//高在0，屏幕的最上面，其他的跟屏幕一样
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View headerView = getChildAt(0);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = headerView.getLayoutParams().height;

        if (mode == MeasureSpec.EXACTLY) {//MeasureSpec.EXACTLY 精确的测量,MeasureSpec.AT_MOST最大不超过父容器的测量，模糊测量
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        headerView.measure(widthMeasureSpec, height);
        View contentView = getChildAt(1);
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    private float eventY1;//第一按下的位置
    private float eventY2;//第二次按下的位置
    private boolean flag;
    private int dxY;//实际移动的距离

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                eventY1 = event.getY();//第一按下的位置

                break;
            case MotionEvent.ACTION_MOVE:
                eventY2 = event.getY();//第二次按下的位置

                dxY = (int) (eventY1 - eventY2);//实际移动的距离

                eventY1 = eventY2;//复制变量让后一次当做前一次来使用

                int newScrollY = getScrollY() + dxY;//getScrollY()当前第一个子view的底部距屏幕的距离

                if (newScrollY < -getChildAt(0).getHeight()) {//第一个子view的底部距屏幕的距离小于第一个子view的高

                    scrollTo(0, -getChildAt(0).getHeight());//直接移动到子view的高
                } else if (newScrollY > 0) {//当前第一个子view的底部距屏幕的距离大于0，相当于要把屏幕的view往上拖拽
                    scrollTo(0, 0);//直接移动到屏幕的00
                } else {
                    scrollBy(0, dxY);// 慢慢移动
                }

                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();

                Log.i("eventY1", "dxY:" + dxY);
                if (scrollY < 0) {//往下拖
                    flag = true;
                }
                if (dxY > 0) {//大于0 往上拖
                    flag = false;
                }
                switchScreen();
                break;
            default:
                break;
        }
        return true;//事件消费掉
    }

    /**
     * 根据mCurrentScreen切换屏幕
     */
    private void switchScreen() {
        int scrollY = getScrollY(); // 当前x轴的偏移量
        int dx;

        if (flag == true) { // 切换到主界面
            dx = -getChildAt(0).getHeight() - scrollY;// 到菜单页面去的，新偏移量
        } else { // 切换到菜单界面
            dx = 0 - scrollY;// 到主页面去的，新偏移量
        }
        // --------------开始模拟的位置，y不变，增量，y不变，模拟的状态 这里为了防止效果不佳的显示，让他们的位移都乘5倍,看起来移动的慢一点。
        mScroller.startScroll(0, scrollY, 0, dx, Math.abs(dx) * 5);

        invalidate(); // invalidate -> drawChild -> child.draw -> computeScroll
        // 其实他执行的方法是调用computeScroll
    }

    /**
     * invalidate出发此方法, 更新屏幕的x轴的偏移量
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) { // 判断是否正在模拟数据中, true 正在进行 false
            // 数据模拟完毕
            scrollTo(0, mScroller.getCurrY());// 如果是正在运行,就一直执行下面这个方法。
            // 而这个方法会一直调用// invalidate -> drawChild -> child.draw ->
            // computeScroll,只要没有移动到最left那条边就一直移动下去。
            invalidate(); // 这个引起computeScroll的调用
        }
    }

}
