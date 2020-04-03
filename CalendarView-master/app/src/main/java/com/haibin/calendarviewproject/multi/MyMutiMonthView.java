package com.haibin.calendarviewproject.multi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MultiMonthView;

public class MyMutiMonthView extends MultiMonthView {
    private Paint mPaintOutRange;
    Calendar mCal;
    public MyMutiMonthView(Context context) {
        super(context);
        mPaintOutRange = new Paint();
        mPaintOutRange.setColor(Color.DKGRAY);

        mCal = new Calendar();
        mCal.setYear(2020);
        mCal.setMonth(3);
        mCal.setDay(13);
    }

    @Override
    protected void onPreviewHook() {
       // mSchemePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme,
                                     boolean isSelectedPre, boolean isSelectedNext) {

    //    Toast.makeText(this.getContext(),cal.toString()+"距离"+calendar.toString()+"有"+calendar.differ(cal)+"天",Toast.LENGTH_SHORT).show();
        if(calendar.differ(mCal)>0) {
            canvas.drawRect(x, y, x+mItemWidth, y+mItemHeight, mSelectedPaint);
        }else{
            //前面的不能选。。。。设置不同的外观，或者不画？
        }
        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        if(calendar.differ(mCal)>0) {
            canvas.drawRect(x, y, x + mItemWidth, y + mItemHeight, mSchemePaint);
        }else{
            canvas.drawRect(x, y, x + mItemWidth, y + mItemHeight, mPaintOutRange);
        }
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        boolean isInRange = isInRange(calendar);
        boolean isEnable = !onCalendarIntercept(calendar);

        if(calendar.differ(mCal)<0)//当前日期前的之前的不显示
            return;

        if (isSelected) {//画选中文本颜色
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    mSelectTextPaint);
        } else if (hasScheme) {//画日程文本颜色
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() && isInRange && isEnable? mSchemeTextPaint : mOtherMonthTextPaint);

        } else {
            //画普通文本颜色
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() && isInRange && isEnable? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }
}
