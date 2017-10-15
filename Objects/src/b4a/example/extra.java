package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class extra {
private static extra mostCurrent = new extra();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static String _api = "";
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.index _index = null;
public static String  _initpanel(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _pnl,float _borderwidth,int _fillcolor,int _bordercolor) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rec = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1 = null;
float _borderwidth_2 = 0f;
 //BA.debugLineNum = 26;BA.debugLine="Sub InitPanel(pnl As Panel,BorderWidth As Float, F";
 //BA.debugLineNum = 27;BA.debugLine="Dim Rec As Rect";
_rec = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim Canvas1 As Canvas";
_canvas1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim BorderWidth_2 As Float";
_borderwidth_2 = 0f;
 //BA.debugLineNum = 30;BA.debugLine="BorderWidth_2=BorderWidth/2";
_borderwidth_2 = (float) (_borderwidth/(double)2);
 //BA.debugLineNum = 31;BA.debugLine="Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Wi";
_rec.Initialize((int) (_borderwidth_2),(int) (_borderwidth_2),(int) (_pnl.getWidth()-_borderwidth_2),(int) (_pnl.getHeight()-_borderwidth_2));
 //BA.debugLineNum = 32;BA.debugLine="Canvas1.Initialize(pnl)";
_canvas1.Initialize((android.view.View)(_pnl.getObject()));
 //BA.debugLineNum = 33;BA.debugLine="Canvas1.DrawRect(Rec,FillColor,True,FillColor)";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_fillcolor,anywheresoftware.b4a.keywords.Common.True,(float) (_fillcolor));
 //BA.debugLineNum = 34;BA.debugLine="Canvas1.DrawRect(Rec,BorderColor,False,BorderWidt";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_bordercolor,anywheresoftware.b4a.keywords.Common.False,_borderwidth);
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _load_category_main(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _load_category = null;
 //BA.debugLineNum = 8;BA.debugLine="Sub load_category_main()";
 //BA.debugLineNum = 9;BA.debugLine="Dim load_category As HttpJob";
_load_category = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 10;BA.debugLine="load_category.Initialize(\"load_category_main\",ind";
_load_category._initialize((_ba.processBA == null ? _ba : _ba.processBA),"load_category_main",(Object)(mostCurrent._index.getObject()));
 //BA.debugLineNum = 11;BA.debugLine="load_category.PostString(api,\"op=category\")";
_load_category._poststring(_api,"op=category");
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _load_headbaner_main(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _load_category = null;
 //BA.debugLineNum = 14;BA.debugLine="Sub load_headbaner_main()";
 //BA.debugLineNum = 15;BA.debugLine="Dim load_category As HttpJob";
_load_category = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 16;BA.debugLine="load_category.Initialize(\"load_headbaner_main\",in";
_load_category._initialize((_ba.processBA == null ? _ba : _ba.processBA),"load_headbaner_main",(Object)(mostCurrent._index.getObject()));
 //BA.debugLineNum = 17;BA.debugLine="load_category.PostString(api,\"op=headbaner\")";
_load_category._poststring(_api,"op=headbaner");
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _load_lastproduct_main(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _load_category = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub load_lastproduct_main()";
 //BA.debugLineNum = 21;BA.debugLine="Dim load_category As HttpJob";
_load_category = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 22;BA.debugLine="load_category.Initialize(\"load_lastproduct_main\",";
_load_category._initialize((_ba.processBA == null ? _ba : _ba.processBA),"load_lastproduct_main",(Object)(mostCurrent._index.getObject()));
 //BA.debugLineNum = 23;BA.debugLine="load_category.PostString(api,\"op=mostview\")";
_load_category._poststring(_api,"op=mostview");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _main_download_image(anywheresoftware.b4a.BA _ba,String _name,String _image) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _idownload = null;
 //BA.debugLineNum = 37;BA.debugLine="Sub main_download_image(name,image)";
 //BA.debugLineNum = 38;BA.debugLine="Dim idownload As HttpJob";
_idownload = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 39;BA.debugLine="idownload.Initialize(\"imageview*\" & name & \"*\" &";
_idownload._initialize((_ba.processBA == null ? _ba : _ba.processBA),"imageview*"+_name+"*"+_image,(Object)(mostCurrent._index.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="idownload.Download(image)";
_idownload._download(_image);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim api As String";
_api = "";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
