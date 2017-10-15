package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_main_panel{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("main_baner").vw.setLeft((int)(0d));
views.get("main_baner").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("main_baner").vw.setHeight((int)((60d / 100 * width)));
views.get("main_baner").vw.setTop((int)(0d));
views.get("category_hscrollview").vw.setLeft((int)(0d));
views.get("category_hscrollview").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("category_hscrollview").vw.setHeight((int)((55d * scale)));
views.get("category_hscrollview").vw.setTop((int)((views.get("main_baner").vw.getTop() + views.get("main_baner").vw.getHeight())));
views.get("mostview_title").vw.setLeft((int)(0d));
views.get("mostview_title").vw.setWidth((int)((98d / 100 * width) - (0d)));
//BA.debugLineNum = 13;BA.debugLine="mostview_title.Height = 30dip"[main_panel/General script]
views.get("mostview_title").vw.setHeight((int)((30d * scale)));
//BA.debugLineNum = 14;BA.debugLine="mostview_title.Top = category_hscrollview.Bottom"[main_panel/General script]
views.get("mostview_title").vw.setTop((int)((views.get("category_hscrollview").vw.getTop() + views.get("category_hscrollview").vw.getHeight())));
//BA.debugLineNum = 16;BA.debugLine="mostview_hscrollview.SetLeftAndRight(0,100%x)"[main_panel/General script]
views.get("mostview_hscrollview").vw.setLeft((int)(0d));
views.get("mostview_hscrollview").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 17;BA.debugLine="mostview_hscrollview.Top =mostview_title.Bottom + 2dip"[main_panel/General script]
views.get("mostview_hscrollview").vw.setTop((int)((views.get("mostview_title").vw.getTop() + views.get("mostview_title").vw.getHeight())+(2d * scale)));
//BA.debugLineNum = 18;BA.debugLine="mostview_hscrollview.Height = 50%x"[main_panel/General script]
views.get("mostview_hscrollview").vw.setHeight((int)((50d / 100 * width)));
//BA.debugLineNum = 20;BA.debugLine="lastproduct_title.SetLeftAndRight(0,98%x)"[main_panel/General script]
views.get("lastproduct_title").vw.setLeft((int)(0d));
views.get("lastproduct_title").vw.setWidth((int)((98d / 100 * width) - (0d)));
//BA.debugLineNum = 21;BA.debugLine="lastproduct_title.Height = 30dip"[main_panel/General script]
views.get("lastproduct_title").vw.setHeight((int)((30d * scale)));
//BA.debugLineNum = 22;BA.debugLine="lastproduct_title.top = mostview_hscrollview.Bottom"[main_panel/General script]
views.get("lastproduct_title").vw.setTop((int)((views.get("mostview_hscrollview").vw.getTop() + views.get("mostview_hscrollview").vw.getHeight())));

}
}