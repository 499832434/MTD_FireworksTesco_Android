package com.chem99.fireworkstesco.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chem99.fireworkstesco.R;
import com.chem99.fireworkstesco.utils.DensityUtil;
import com.chem99.fireworkstesco.view.MyScrollView;

import java.lang.reflect.Field;

/**
 * Created by zongshuo on 2017/7/5.
 */
public class RecommendFragment extends Fragment implements OnRefreshListener,MyScrollView.OnScrollListener{
    private View currentView = null;
    private SwipeToLoadLayout swipeToLoadLayout;
    private MyScrollView scrollView;
    private TextView tv1,tv2,tv3;
    private int topHeight,tv2Height;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView();
        return currentView;
    }

    private void initView(){
        topHeight=getStatusBarHeight();

        tv1= (TextView) currentView.findViewById(R.id.tv1);
        tv2= (TextView) currentView.findViewById(R.id.tv2);
        tv3= (TextView) currentView.findViewById(R.id.tv3);
        tv2Height= DensityUtil.dip2px(getActivity(), 50);

        Log.e("", topHeight + "===" + getTv2Height() + "====" + tv2Height);
        scrollView = (MyScrollView) currentView.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) currentView.findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(this);

        scrollView.setOnScrollListener(this);
        //当布局的状态或者控件的可见性发生改变回调的接口
        currentView.findViewById(R.id.swipeToLoadLayout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(scrollView.getScrollY());
                Log.e("eee", String.valueOf(scrollView.getScrollY()));
            }
        });
        scrollView.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);


        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
        
    }

    ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {

        @Override
        public void onScrollChanged() {
            if (scrollView.getChildAt(0).getHeight() < scrollView.getScrollY() + scrollView.getHeight() && !ViewCompat.canScrollVertically(scrollView, 1)) {
                swipeToLoadLayout.setLoadingMore(true);
            }
        }
    };

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void onScroll(int scrollY) {
        int v12ParentTop = Math.max(scrollY, tv1.getTop());
        tv2.layout(0, v12ParentTop, tv2.getWidth(), v12ParentTop + tv2.getHeight());
        getss();
    }

    private void getss(){
//        int width,height;
//        Point p=new Point();
//        getWindowManager().getDefaultDisplay().getSize(p);
//        width=p.x;
//        height=p.y;
//
//        Rect rect=new Rect(0,500,width,height);
//
//        if(tv3.getLocalVisibleRect(rect)){
//            System.out.println("控件在屏幕显示范围内");
//            tv2.setText("111");
//        }else{
//            System.out.println("控件已滑出屏幕…………");
//            tv2.setText("222");
//        }

        int[] location = new int[2];
        tv3.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        Log.e("zzzz",x+"===="+y);

        if(y<topHeight){
            tv2.setText("222");
        }else{
            tv2.setText("111");
        }


//        Rect scrollBounds = new Rect();
//        scrollView.getHitRect(scrollBounds);
//        if (tv3.getLocalVisibleRect(scrollBounds)) {
//            tv2.setText("111");
//        }else{
//            tv2.setText("222");
//        }
    }

    public  int getStatusBarHeight(){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    private int getTv2Height(){
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        tv2.measure(w, h);
        int height =tv2.getMeasuredHeight();
        int width =tv2.getMeasuredWidth();
        return height;

    }
}
