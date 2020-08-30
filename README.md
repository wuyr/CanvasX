## 一个能让你在普通Java程序上正常使用Android中的Canvas、Paint、Bitmap、Path等用于绘制图形的类的jar包，无须Android模拟器
>（注意，项目代码需要用Android Studio的爸爸——IntelliJ IDEA打开）

<br/>

### 博客详情：敬请期待。。。

<br/>

### 原理：
其实我也只是盗用了ADT的成果而已，主要依赖一个叫`layoutlib`的jar包，只看名字可能会觉得陌生，网上搜一下你会发现有好多【Eclipse/AS如何导入`layoutlib.jar`】的相关问题。。。没错，Eclipse和AndroidStudio能不运行Android程序直接看到xml的布局效果，也是依赖这个神奇的`layoutlib.jar`来实现的。
其中最重要的一个类就是*Bitmap_Delegate*了，是它将Java的BufferedImage与Android的Bitmap对象 “融合” 在了一起，让这两个本来毫不相干的类实现 “数据共享”，这就是能把Canvas的内容在Java程序中保存为图片文件的最直接原因。
当然了，我也知道弄这个东西没什么实际作用，如果是Android项目的话，对于这些绘制的代码，直接在AS自带的DesignerEditor就能看到效果了，如果是Java项目也可以直接用awt包下的Canvas来画。
唯一的意义应该就是能让同学们学习到相关原理了吧。

<br/>

### 使用方式：
很简单，把项目中*fonts*文件夹还有*libs*下的`CanvasX.jar`复制到你的Java项目中并添加上jar包依赖就能使用了。
### 示例代码：
>注意，Canvas对象必须使用CanvasX的实例，直接实例化Canvas会出错：
```java
public class Test {
    public static void main(String[] s) throws Exception {
        //创建指定尺寸的Canvas对象
        CanvasX canvas = new CanvasX(1080, 1920);
        //当然了，也可以指定屏幕密度（类似真实Android设备屏幕）
        CanvasX canvas = new CanvasX(1080, 1920, Density.DPI_360);

        ///////////////////////////////////////////////////////////////////////////
        // 接下来就可以正常操作Canvas了，可以与Android项目中的代码通用
        ///////////////////////////////////////////////////////////////////////////

        //初始化Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setTextSize(100);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        //画绿色背景
        canvas.drawColor(Color.GREEN);

        //红色字体
        paint.setColor(Color.RED);
        //文字测试
        canvas.drawText("落花滿天蔽月光", 50, 200, paint);
        canvas.drawText("借一杯附薦鳳臺上", 50, 300, paint);
        canvas.drawText("asb5465we.()", 50, 400, paint);
        canvas.drawText("126./!@#%%+_", 50, 500, paint);

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
```
运行完毕，会在项目根目录下看到一个`test.png`文件：

<img src="https://github.com/wuyr/CanvasX/raw/master/test.png" width="320" height="auto"/>

<br/>

### 局限性：
 1. 虽然jar包里面也有View、Context、LayoutInflater等类，但我没有对这些类做兼容（太复杂了，不然也不至于只有Canvas能用），所以直接使用这些类的话，会发现基本上没有能正常使用的API；
 
 2. 因为对字体包做了精简（完整的太大了，200多M），现只保留了基本的*DEFAULT*、*DEFAULT_BOLD*、*SANS_SERIF*、*SERIF*、*MONOSPACE*五种，所以在绘制的时候如果用到了前面列出的五种以外的字体，就会出错。当然了，你也可以手动把*AndroidStudio/plugins/android/lib/layoutlib/data/fonts*下的字体文件全部复制过来，这样就支持所有自带字体了；
 
 3. 毕竟不是运行在完整Android环境下的Paint，在功能上会有些限制，已知限制：不支持`setShadowLayer`（阴影效果。问题不大，如需实现可以手动`draw`一次）、`getTextPath`（获取文字对应的Path。一般用于做文字的特效动画）； 


<br/>

### `CanvasX.jar`源码地址：<https://github.com/ifxcyr/CanvasX>