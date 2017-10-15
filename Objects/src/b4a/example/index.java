package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class index extends Activity implements B4AActivity{
	public static index mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.index");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (index).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.index");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.index", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (index) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (index) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return index.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (index) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (index) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ImageViewWrapper[] _imageview = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _header_logo = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _main_scrollview = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _category_hscrollview = null;
public anywheresoftware.b4a.objects.PanelWrapper _category_panel = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _main_baner = null;
public AndroidSlider.SliderLibrary.Slider _head_slider = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _mostview_hscrollview = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.extra _extra = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 26;BA.debugLine="header_logo.Bitmap = LoadBitmap(File.DirAssets,\"s";
mostCurrent._header_logo.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"setting/header_logo.png").getObject()));
 //BA.debugLineNum = 28;BA.debugLine="extra.load_category_main";
mostCurrent._extra._load_category_main(mostCurrent.activityBA);
 //BA.debugLineNum = 29;BA.debugLine="extra.load_headbaner_main";
mostCurrent._extra._load_headbaner_main(mostCurrent.activityBA);
 //BA.debugLineNum = 30;BA.debugLine="extra.load_lastproduct_main";
mostCurrent._extra._load_lastproduct_main(mostCurrent.activityBA);
 //BA.debugLineNum = 32;BA.debugLine="main_scrollview.Panel.LoadLayout(\"main_panel\")";
mostCurrent._main_scrollview.getPanel().LoadLayout("main_panel",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="main_baner.Bitmap = LoadBitmap(File.DirAssets,\"se";
mostCurrent._main_baner.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"setting/main_baner.jpg").getObject()));
 //BA.debugLineNum = 34;BA.debugLine="main_scrollview.Panel.Height = 1500dip";
mostCurrent._main_scrollview.getPanel().setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1500)));
 //BA.debugLineNum = 35;BA.debugLine="category_hscrollview.Panel.LoadLayout(\"category\")";
mostCurrent._category_hscrollview.getPanel().LoadLayout("category",mostCurrent.activityBA);
 //BA.debugLineNum = 36;BA.debugLine="mostview_hscrollview.Panel.LoadLayout(\"mostview\")";
mostCurrent._mostview_hscrollview.getPanel().LoadLayout("mostview",mostCurrent.activityBA);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim ImageView(6)  As ImageView";
mostCurrent._imageview = new anywheresoftware.b4a.objects.ImageViewWrapper[(int) (6)];
{
int d0 = mostCurrent._imageview.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._imageview[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 14;BA.debugLine="Private header_logo As ImageView";
mostCurrent._header_logo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private main_scrollview As ScrollView";
mostCurrent._main_scrollview = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private category_hscrollview As HorizontalScrollV";
mostCurrent._category_hscrollview = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private category_panel As Panel";
mostCurrent._category_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private main_baner As ImageView";
mostCurrent._main_baner = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim head_slider As Slider";
mostCurrent._head_slider = new AndroidSlider.SliderLibrary.Slider();
 //BA.debugLineNum = 20;BA.debugLine="Private mostview_hscrollview As HorizontalScrollV";
mostCurrent._mostview_hscrollview = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
int _index = 0;
String _name = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _outstream = null;
int _left = 0;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
anywheresoftware.b4a.objects.collections.Map _colroot = null;
String _image = "";
String _price = "";
String _model = "";
String _id = "";
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.LabelWrapper _lable = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvasgraph = null;
anywheresoftware.b4a.objects.LabelWrapper _lable2 = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
String _link = "";
String _text = "";
String _pic = "";
 //BA.debugLineNum = 48;BA.debugLine="Sub jobdone(job As HttpJob)";
 //BA.debugLineNum = 49;BA.debugLine="If job.Success = True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 50;BA.debugLine="Try";
try { //BA.debugLineNum = 51;BA.debugLine="If  job.JobName.SubString2(0,9)=\"imageview\" The";
if ((_job._jobname.substring((int) (0),(int) (9))).equals("imageview")) { 
 //BA.debugLineNum = 52;BA.debugLine="Dim index As Int";
_index = 0;
 //BA.debugLineNum = 53;BA.debugLine="Dim name As String";
_name = "";
 //BA.debugLineNum = 54;BA.debugLine="index = job.JobName.SubString2(job.JobName.Ind";
_index = (int)(Double.parseDouble(_job._jobname.substring((int) (_job._jobname.indexOf("*")+1),_job._jobname.lastIndexOf("*"))));
 //BA.debugLineNum = 55;BA.debugLine="name = job.JobName.SubString(job.JobName.LastI";
_name = _job._jobname.substring((int) (_job._jobname.lastIndexOf("/")+1));
 //BA.debugLineNum = 56;BA.debugLine="Dim OutStream As OutputStream";
_outstream = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 57;BA.debugLine="OutStream = File.OpenOutput(File.DirInternalCa";
_outstream = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternalCache(),_name,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 58;BA.debugLine="File.Copy2(job.GetInputStream,OutStream)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_outstream.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="OutStream.Close";
_outstream.Close();
 //BA.debugLineNum = 60;BA.debugLine="ImageView(index).Bitmap = LoadBitmap(File.DirI";
mostCurrent._imageview[_index].setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternalCache(),_name).getObject()));
 };
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); //BA.debugLineNum = 67;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 69;BA.debugLine="If job.JobName = \"load_lastproduct_main\" Then";
if ((_job._jobname).equals("load_lastproduct_main")) { 
 //BA.debugLineNum = 70;BA.debugLine="Dim left As Int=8dip";
_left = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8));
 //BA.debugLineNum = 71;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 72;BA.debugLine="parser.Initialize(job.GetString)";
_parser.Initialize(_job._getstring());
 //BA.debugLineNum = 73;BA.debugLine="Dim index As Int = 0";
_index = (int) (0);
 //BA.debugLineNum = 74;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 75;BA.debugLine="For Each colroot As Map In root";
_colroot = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group23 = _root;
final int groupLen23 = group23.getSize()
;int index23 = 0;
;
for (; index23 < groupLen23;index23++){
_colroot.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group23.Get(index23)));
 //BA.debugLineNum = 76;BA.debugLine="Dim image As String = colroot.Get(\"image\")";
_image = BA.ObjectToString(_colroot.Get((Object)("image")));
 //BA.debugLineNum = 77;BA.debugLine="Dim price As String = colroot.Get(\"price\")";
_price = BA.ObjectToString(_colroot.Get((Object)("price")));
 //BA.debugLineNum = 78;BA.debugLine="Dim model As String = colroot.Get(\"model\")";
_model = BA.ObjectToString(_colroot.Get((Object)("model")));
 //BA.debugLineNum = 79;BA.debugLine="Dim name As String = colroot.Get(\"name\")";
_name = BA.ObjectToString(_colroot.Get((Object)("name")));
 //BA.debugLineNum = 80;BA.debugLine="Dim id As String = colroot.Get(\"id\")";
_id = BA.ObjectToString(_colroot.Get((Object)("id")));
 //BA.debugLineNum = 83;BA.debugLine="Dim panel As Panel";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 84;BA.debugLine="panel.Initialize(\"panel\")";
_panel.Initialize(mostCurrent.activityBA,"panel");
 //BA.debugLineNum = 86;BA.debugLine="panel.Color = Colors.White";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 87;BA.debugLine="mostview_hscrollview.Panel.AddView(panel,left,";
mostCurrent._mostview_hscrollview.getPanel().AddView((android.view.View)(_panel.getObject()),_left,(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (160)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 88;BA.debugLine="extra.InitPanel(panel,1dip,Colors.White,Colors";
mostCurrent._extra._initpanel(mostCurrent.activityBA,_panel,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (207),(int) (204),(int) (204)));
 //BA.debugLineNum = 90;BA.debugLine="Dim lable As Label";
_lable = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 91;BA.debugLine="lable.Initialize(\"lable\")";
_lable.Initialize(mostCurrent.activityBA,"lable");
 //BA.debugLineNum = 92;BA.debugLine="lable.Color = Colors.White";
_lable.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 93;BA.debugLine="lable.TextColor = Colors.rgb(65, 65, 65)";
_lable.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (65),(int) (65),(int) (65)));
 //BA.debugLineNum = 94;BA.debugLine="lable.Gravity = Gravity.RIGHT";
_lable.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 95;BA.debugLine="lable.Typeface = Typeface.LoadFromAssets(\"yeka";
_lable.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("yekan.ttf"));
 //BA.debugLineNum = 96;BA.debugLine="lable.TextSize = \"16\"";
_lable.setTextSize((float)(Double.parseDouble("16")));
 //BA.debugLineNum = 97;BA.debugLine="lable.Text = model";
_lable.setText(BA.ObjectToCharSequence(_model));
 //BA.debugLineNum = 98;BA.debugLine="mostview_hscrollview.Panel.AddView(lable,left+";
mostCurrent._mostview_hscrollview.getPanel().AddView((android.view.View)(_lable.getObject()),(int) (_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (145)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (28)));
 //BA.debugLineNum = 100;BA.debugLine="ImageView(index).Initialize(\"ImageView\")";
mostCurrent._imageview[_index].Initialize(mostCurrent.activityBA,"ImageView");
 //BA.debugLineNum = 101;BA.debugLine="If File.Exists(File.DirInternalCache, image.Su";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternalCache(),_image.substring((int) (_image.lastIndexOf("/")+1),_image.length()))) { 
 //BA.debugLineNum = 102;BA.debugLine="ImageView(index).Bitmap = LoadBitmap(File.D";
mostCurrent._imageview[_index].setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternalCache(),_image.substring((int) (_image.lastIndexOf("/")+1),_image.length())).getObject()));
 //BA.debugLineNum = 103;BA.debugLine="Log(\"from cach\")";
anywheresoftware.b4a.keywords.Common.Log("from cach");
 }else {
 //BA.debugLineNum = 105;BA.debugLine="ImageView(index).Bitmap = LoadBitmap(File.Di";
mostCurrent._imageview[_index].setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"setting/main_defult_product.jpg").getObject()));
 //BA.debugLineNum = 106;BA.debugLine="extra.main_download_image(index,image)";
mostCurrent._extra._main_download_image(mostCurrent.activityBA,BA.NumberToString(_index),_image);
 };
 //BA.debugLineNum = 108;BA.debugLine="ImageView(index).Gravity = Gravity.FILL";
mostCurrent._imageview[_index].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 109;BA.debugLine="mostview_hscrollview.Panel.AddView(ImageView(i";
mostCurrent._mostview_hscrollview.getPanel().AddView((android.view.View)(mostCurrent._imageview[_index].getObject()),(int) (_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (48.5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (157)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88)));
 //BA.debugLineNum = 111;BA.debugLine="Dim CanvasGraph As Canvas";
_canvasgraph = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 112;BA.debugLine="CanvasGraph.Initialize(panel)";
_canvasgraph.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 114;BA.debugLine="CanvasGraph.DrawLine(0,35%x,160dip,35%x,Colors";
_canvasgraph.DrawLine((float) (0),(float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA)),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (160))),(float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (207),(int) (204),(int) (204)),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 117;BA.debugLine="Dim lable2 As Label";
_lable2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 118;BA.debugLine="lable2.Initialize(\"lable2\")";
_lable2.Initialize(mostCurrent.activityBA,"lable2");
 //BA.debugLineNum = 119;BA.debugLine="lable2.Color = Colors.White";
_lable2.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 120;BA.debugLine="lable2.TextColor = Colors.rgb(102, 187, 106)";
_lable2.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (187),(int) (106)));
 //BA.debugLineNum = 121;BA.debugLine="lable2.Typeface = Typeface.DEFAULT_BOLD";
_lable2.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 122;BA.debugLine="lable2.Gravity = Gravity.LEFT";
_lable2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
 //BA.debugLineNum = 123;BA.debugLine="lable2.Gravity = Gravity.CENTER_VERTICAL";
_lable2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 124;BA.debugLine="lable2.Typeface = Typeface.LoadFromAssets(\"yek";
_lable2.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("yekan.ttf"));
 //BA.debugLineNum = 125;BA.debugLine="lable2.TextSize = \"16\"";
_lable2.setTextSize((float)(Double.parseDouble("16")));
 //BA.debugLineNum = 126;BA.debugLine="lable2.Text = price";
_lable2.setText(BA.ObjectToCharSequence(_price));
 //BA.debugLineNum = 127;BA.debugLine="mostview_hscrollview.Panel.AddView(lable2,left";
mostCurrent._mostview_hscrollview.getPanel().AddView((android.view.View)(_lable2.getObject()),(int) (_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (125)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 138;BA.debugLine="left = left + 170dip";
_left = (int) (_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)));
 //BA.debugLineNum = 139;BA.debugLine="index= index + 1";
_index = (int) (_index+1);
 }
};
 //BA.debugLineNum = 141;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 142;BA.debugLine="r.Target = mostview_hscrollview";
_r.Target = (Object)(mostCurrent._mostview_hscrollview.getObject());
 //BA.debugLineNum = 143;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", F";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 144;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 145;BA.debugLine="mostview_hscrollview.Panel.Width = left";
mostCurrent._mostview_hscrollview.getPanel().setWidth(_left);
 };
 //BA.debugLineNum = 147;BA.debugLine="If job.JobName = \"load_headbaner_main\" Then";
if ((_job._jobname).equals("load_headbaner_main")) { 
 //BA.debugLineNum = 148;BA.debugLine="Try";
try { //BA.debugLineNum = 149;BA.debugLine="head_slider.Initialize(\"head_slider\")";
mostCurrent._head_slider.Initialize(mostCurrent.activityBA,"head_slider");
 //BA.debugLineNum = 150;BA.debugLine="main_scrollview.Panel.AddView(head_slider,0,0,";
mostCurrent._main_scrollview.getPanel().AddView((android.view.View)(mostCurrent._head_slider.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA));
 //BA.debugLineNum = 151;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 152;BA.debugLine="parser.Initialize(job.GetString)";
_parser.Initialize(_job._getstring());
 //BA.debugLineNum = 153;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 154;BA.debugLine="For Each colroot As Map In root";
_colroot = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group83 = _root;
final int groupLen83 = group83.getSize()
;int index83 = 0;
;
for (; index83 < groupLen83;index83++){
_colroot.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group83.Get(index83)));
 //BA.debugLineNum = 155;BA.debugLine="Dim link As String = colroot.Get(\"link\")";
_link = BA.ObjectToString(_colroot.Get((Object)("link")));
 //BA.debugLineNum = 156;BA.debugLine="Dim text As String = colroot.Get(\"text\")";
_text = BA.ObjectToString(_colroot.Get((Object)("text")));
 //BA.debugLineNum = 157;BA.debugLine="Dim pic As String = colroot.Get(\"pic\")";
_pic = BA.ObjectToString(_colroot.Get((Object)("pic")));
 //BA.debugLineNum = 158;BA.debugLine="head_slider.AddSlide(text,pic)";
mostCurrent._head_slider.AddSlide(_text,_pic);
 }
};
 //BA.debugLineNum = 160;BA.debugLine="main_baner.Visible = False";
mostCurrent._main_baner.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 161;BA.debugLine="head_slider.SetTransition(head_slider.SLIDER_T";
mostCurrent._head_slider.SetTransition(mostCurrent._head_slider.SLIDER_TRANSITION_Default);
 //BA.debugLineNum = 162;BA.debugLine="head_slider.Delay = 5000";
mostCurrent._head_slider.Delay = (int) (5000);
 //BA.debugLineNum = 164;BA.debugLine="head_slider.Start";
mostCurrent._head_slider.Start();
 } 
       catch (Exception e94) {
			processBA.setLastException(e94); //BA.debugLineNum = 166;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 };
 //BA.debugLineNum = 169;BA.debugLine="If job.JobName = \"load_category_main\" Then";
if ((_job._jobname).equals("load_category_main")) { 
 //BA.debugLineNum = 170;BA.debugLine="Dim left As Int=8dip";
_left = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8));
 //BA.debugLineNum = 171;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 172;BA.debugLine="parser.Initialize(job.GetString)";
_parser.Initialize(_job._getstring());
 //BA.debugLineNum = 173;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 174;BA.debugLine="For Each colroot As Map In root";
_colroot = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group102 = _root;
final int groupLen102 = group102.getSize()
;int index102 = 0;
;
for (; index102 < groupLen102;index102++){
_colroot.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group102.Get(index102)));
 //BA.debugLineNum = 175;BA.debugLine="Dim id As String = colroot.Get(\"id\")";
_id = BA.ObjectToString(_colroot.Get((Object)("id")));
 //BA.debugLineNum = 176;BA.debugLine="Dim text As String = colroot.Get(\"name\")";
_text = BA.ObjectToString(_colroot.Get((Object)("name")));
 //BA.debugLineNum = 177;BA.debugLine="Dim lable As Label";
_lable = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 178;BA.debugLine="lable.Initialize(\"lable\")";
_lable.Initialize(mostCurrent.activityBA,"lable");
 //BA.debugLineNum = 179;BA.debugLine="lable.Color = Colors.rgb(102, 187, 106)";
_lable.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (187),(int) (106)));
 //BA.debugLineNum = 180;BA.debugLine="lable.TextColor = Colors.White";
_lable.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 181;BA.debugLine="lable.Gravity = Gravity.CENTER";
_lable.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 182;BA.debugLine="lable.Typeface = Typeface.LoadFromAssets(\"yeka";
_lable.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("yekan.ttf"));
 //BA.debugLineNum = 183;BA.debugLine="lable.TextSize = \"20\"";
_lable.setTextSize((float)(Double.parseDouble("20")));
 //BA.debugLineNum = 184;BA.debugLine="lable.Text = text";
_lable.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 185;BA.debugLine="category_panel.AddView(lable,left,5dip,(text.L";
mostCurrent._category_panel.AddView((android.view.View)(_lable.getObject()),_left,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) ((_text.length()*30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 186;BA.debugLine="left =( text.Length * 30  ) + left +8dip";
_left = (int) ((_text.length()*30)+_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)));
 }
};
 //BA.debugLineNum = 188;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 189;BA.debugLine="r.Target = category_hscrollview";
_r.Target = (Object)(mostCurrent._category_hscrollview.getObject());
 //BA.debugLineNum = 190;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", F";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 191;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 192;BA.debugLine="category_panel.Width = left + 8dip";
mostCurrent._category_panel.setWidth((int) (_left+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))));
 //BA.debugLineNum = 193;BA.debugLine="category_hscrollview.Panel.Width = left";
mostCurrent._category_hscrollview.getPanel().setWidth(_left);
 //BA.debugLineNum = 194;BA.debugLine="category_hscrollview.FullScroll(True)";
mostCurrent._category_hscrollview.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 195;BA.debugLine="category_hscrollview.ScrollPosition = 50dip";
mostCurrent._category_hscrollview.setScrollPosition(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 };
 };
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
}
