package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_welcome{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("bg").vw.setLeft((int)(0d));
views.get("bg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("bg").vw.setTop((int)(0d));
views.get("bg").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("welcome_logo").vw.setWidth((int)((50d / 100 * width)));
views.get("welcome_logo").vw.setHeight((int)((25d / 100 * width)));
views.get("welcome_logo").vw.setTop((int)((20d / 100 * height)));
views.get("welcome_logo").vw.setLeft((int)((25d / 100 * width)));
views.get("welcome_text").vw.setLeft((int)(0d));
views.get("welcome_text").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("welcome_text").vw.setHeight((int)((45d * scale)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("welcome_text").vw).setTextSize((float)((12d * scale)));
views.get("welcome_text").vw.setTop((int)((95d / 100 * height) - (views.get("welcome_text").vw.getHeight())));

}
}