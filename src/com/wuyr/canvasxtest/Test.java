package com.wuyr.canvasxtest;

import android.graphics.*;
import android.view.LayoutInflater;
import com.android.resources.Density;
import com.wuyr.canvasx.CanvasX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test {
    public static void main(String[] s) throws Exception {
        //创建指定尺寸的Canvas对象
        CanvasX canvas = new CanvasX(1080, 1920);
        //当然了，也可以指定屏幕密度（类似真实Android设备屏幕）
        //CanvasX canvas = new CanvasX(1080, 1920, Density.DPI_360);

        ///////////////////////////////////////////////////////////////////////////
        // 接下来就可以正常操作Canvas了，可以与Android项目中的代码通用
        ///////////////////////////////////////////////////////////////////////////

        //初始化Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setTextSize(100);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        //画白色背景
        canvas.drawColor(Color.WHITE);

        //红色字体
        paint.setColor(Color.RED);
        //文字测试
        canvas.drawText("落花滿天蔽月光", 50, 200, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 300, paint);
        canvas.drawText("asb5465we.()", 50, 400, paint);
        canvas.drawText("126./!@#%%+_", 50, 500, paint);

        //画Path
        paint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(250, 400);
        path.rLineTo(600, 300);
        path.rLineTo(-600, 300);
        path.rLineTo(600, 300);
        path.rLineTo(-600, 300);
        path.rLineTo(600, 300);
        canvas.drawPath(path, paint);

        //在画布中心画一个渐变圆
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F,
                400, new int[]{Color.BLUE, Color.TRANSPARENT}, new float[]{.2F, 1F}, Shader.TileMode.CLAMP));
        canvas.drawCircle(canvas.getWidth() / 2F, canvas.getHeight() / 2F, 400, paint);

        //保存BufferedImage到运行目录下，文件名为test.png
        BufferedImage image = canvas.getBufferedImage();
        ImageIO.write(image, "png", new File(System.getProperty("user.dir"), "test.png"));
    }
}
