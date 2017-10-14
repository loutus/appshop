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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim api As String";
_api = "";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
