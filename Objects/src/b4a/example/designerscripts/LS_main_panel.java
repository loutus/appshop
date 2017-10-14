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
views.get("category_hscrollview").vw.setHeight((int)((65d * scale)));
views.get("category_hscrollview").vw.setTop((int)((views.get("main_baner").vw.getTop() + views.get("main_baner").vw.getHeight())));

}
}