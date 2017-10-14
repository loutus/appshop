package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[main/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="header.SetLeftAndRight(0,100%x)"[main/General script]
views.get("header").vw.setLeft((int)(0d));
views.get("header").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 4;BA.debugLine="header.SetTopAndBottom(0,50dip)"[main/General script]
views.get("header").vw.setTop((int)(0d));
views.get("header").vw.setHeight((int)((50d * scale) - (0d)));
//BA.debugLineNum = 6;BA.debugLine="header_logo.Height = 40dip"[main/General script]
views.get("header_logo").vw.setHeight((int)((40d * scale)));
//BA.debugLineNum = 7;BA.debugLine="header_logo.Top = 5dip"[main/General script]
views.get("header_logo").vw.setTop((int)((5d * scale)));
//BA.debugLineNum = 8;BA.debugLine="header_logo.Width = 112dip"[main/General script]
views.get("header_logo").vw.setWidth((int)((112d * scale)));
//BA.debugLineNum = 9;BA.debugLine="header_logo.Right = 85%x"[main/General script]
views.get("header_logo").vw.setLeft((int)((85d / 100 * width) - (views.get("header_logo").vw.getWidth())));
//BA.debugLineNum = 11;BA.debugLine="main_scrollview.SetLeftAndRight(0,100%x)"[main/General script]
views.get("main_scrollview").vw.setLeft((int)(0d));
views.get("main_scrollview").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 12;BA.debugLine="main_scrollview.SetTopAndBottom(50dip,100%y)"[main/General script]
views.get("main_scrollview").vw.setTop((int)((50d * scale)));
views.get("main_scrollview").vw.setHeight((int)((100d / 100 * height) - ((50d * scale))));

}
}