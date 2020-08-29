package com.wuyr.canvasxtest;

import android.graphics.*;
import com.wuyr.canvasx.CanvasX;

import javax.imageio.ImageIO;
import java.io.File;

public class Test {
    public static void main(String[] s) throws Exception {
        //创建指定大小的Canvas对象
        CanvasX canvas = new CanvasX(1080, 1920);

        ///////////////////////////////////////////////////////////////////////////
        // 以下均是常规Canvas操作，可以与Android下的代码通用
        ///////////////////////////////////////////////////////////////////////////
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setTextSize(100);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.parseColor("#ff226648"));
        canvas.drawText("落花滿天蔽月光", 50, 200, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 300, paint);
        canvas.drawText("asb5465we.()", 50, 1400, paint);
        canvas.drawText("126./!@#%%+_", 50, 1500, paint);
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText("落花滿天蔽月光", 50, 400, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 500, paint);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("落花滿天蔽月光", 50, 600, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 700, paint);
        canvas.drawCircle(200, 500, 100, paint);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setFakeBoldText(true);
        canvas.drawText("落花滿天蔽月光", 50, 800, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 900, paint);
        paint.setTextSkewX(.5F);
        canvas.drawText("落花滿天蔽月光", 50, 1000, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 1100, paint);
        paint.setTextSkewX(-.5F);
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC));
        canvas.drawText("落花滿天蔽月光", 50, 1200, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 1300, paint);
        paint.setColor(Color.YELLOW);
        Path path = new Path();
        path.moveTo(250, 400);
        path.rLineTo(600, 300);
        path.rLineTo(-600, 300);
        path.rLineTo(600, 300);
        path.rLineTo(-600, 300);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F,
                400, new int[]{Color.BLUE, Color.TRANSPARENT}, new float[]{.2F, 1F}, Shader.TileMode.CLAMP));
        canvas.drawCircle(canvas.getWidth() / 2F, canvas.getHeight() / 2F, 400, paint);

        //保存图片到运行目录下
        ImageIO.write(canvas.getBufferedImage(), "png", new File(System.getProperty("user.dir"), "test.png"));

    }
}
