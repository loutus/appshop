Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle:  False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
End Sub
Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim ImageView(6)  As ImageView
	Private header_logo As ImageView
	Private main_scrollview As ScrollView
	Private category_hscrollview As HorizontalScrollView
	Private category_panel As Panel
	Private main_baner As ImageView
	Dim head_slider As Slider
	Private mostview_hscrollview As HorizontalScrollView
	Private lastproduct__hscrollview As HorizontalScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("main")
	header_logo.Bitmap = LoadBitmap(File.DirAssets,"setting/header_logo.png")

	extra.load_category_main
	extra.load_headbaner_main
	extra.load_lastproduct_main
	
	main_scrollview.Panel.LoadLayout("main_panel")
	main_baner.Bitmap = LoadBitmap(File.DirAssets,"setting/main_baner.jpg")
	main_scrollview.Panel.Height = 1500dip
	category_hscrollview.Panel.LoadLayout("category")
	mostview_hscrollview.Panel.LoadLayout("mostview")
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub jobdone(job As HttpJob)
	If job.Success = True Then
		Try
			If  job.JobName.SubString2(0,9)="imageview" Then
				Dim index As Int
				Dim name As String
				index = job.JobName.SubString2(job.JobName.IndexOf("*")+1,job.JobName.LastIndexOf("*"))
				name = job.JobName.SubString(job.JobName.LastIndexOf("/")+1)
				Dim OutStream As OutputStream
				OutStream = File.OpenOutput(File.DirInternalCache, name, False)
				File.Copy2(job.GetInputStream,OutStream)
				OutStream.Close
				ImageView(index).Bitmap = LoadBitmap(File.DirInternalCache, name)
			End If
'			If  = "load_lastproduct_main" Then
'				
'				
'			End If
		Catch
			Log(LastException)
		End Try
		If job.JobName = "load_lastproduct_main" Then
			Dim left As Int=8dip
			Dim parser As JSONParser
			parser.Initialize(job.GetString)
			Dim index As Int = 0
			Dim root As List = parser.NextArray
			For Each colroot As Map In root
				Dim image As String = colroot.Get("image")
				Dim price As String = colroot.Get("price")
				Dim model As String = colroot.Get("model")
				Dim name As String = colroot.Get("name")
				Dim id As String = colroot.Get("id")
				

				Dim panel As Panel
				panel.Initialize("panel")
				
				panel.Color = Colors.White
				mostview_hscrollview.Panel.AddView(panel,left,0,160dip,50%x-2dip)
				extra.InitPanel(panel,1dip,Colors.White,Colors.rgb(207, 204, 204))
			
				Dim lable As Label
				lable.Initialize("lable")
				lable.Color = Colors.White
				lable.TextColor = Colors.rgb(65, 65, 65)
				lable.Gravity = Gravity.RIGHT
				lable.Typeface = Typeface.LoadFromAssets("yekan.ttf")
				lable.TextSize = "16"
				lable.Text = model
				mostview_hscrollview.Panel.AddView(lable,left+2dip,35%x+5dip,145dip,28dip)
				
				ImageView(index).Initialize("ImageView")
				If File.Exists(File.DirInternalCache, image.SubString2(image.LastIndexOf("/")+1,image.Length)) Then 
					 	ImageView(index).Bitmap = LoadBitmap(File.DirInternalCache, image.SubString2(image.LastIndexOf("/")+1,image.Length))
						Log("from cach")
					Else
						ImageView(index).Bitmap = LoadBitmap(File.DirAssets,"setting/main_defult_product.jpg")
						extra.main_download_image(index,image)
				End If
				ImageView(index).Gravity = Gravity.FILL
				mostview_hscrollview.Panel.AddView(ImageView(index),left+1dip,48.5dip,157dip,88dip)
				
				Dim CanvasGraph As Canvas
				CanvasGraph.Initialize(panel)
				'CanvasGraph.DrawColor(Colors.White)
				CanvasGraph.DrawLine(0,35%x,160dip,35%x,Colors.rgb(207, 204, 204),1dip)
				
				
				Dim lable2 As Label
				lable2.Initialize("lable2")
				lable2.Color = Colors.White
				lable2.TextColor = Colors.rgb(102, 187, 106)
				lable2.Typeface = Typeface.DEFAULT_BOLD
				lable2.Gravity = Gravity.LEFT
				lable2.Gravity = Gravity.CENTER_VERTICAL
				lable2.Typeface = Typeface.LoadFromAssets("yekan.ttf")
				lable2.TextSize = "16"
				lable2.Text = price
				mostview_hscrollview.Panel.AddView(lable2,left+15dip,35%x+30dip,125dip,40dip)
				
'				Dim lable3 As Label
'				lable3.Initialize("lable2")
'				lable3.Color = Colors.White
'				lable3.TextColor = Colors.Green
'				lable3.Gravity = Gravity.LEFT
'				lable3.Typeface = Typeface.LoadFromAssets("yekan.ttf")
'				lable3.TextSize = "16"
'				lable3.Text = price
'				mostview_hscrollview.Panel.AddView(lable3,left+15dip,lable2.Top+lable2.Height + 2dip,125dip,20dip)
				left = left + 170dip
				index= index + 1
			Next
			Dim r As Reflector
			r.Target = mostview_hscrollview
			r.RunMethod2("setHorizontalScrollBarEnabled", False, "java.lang.boolean")
			r.RunMethod2("setOverScrollMode", 2, "java.lang.int" )
			mostview_hscrollview.Panel.Width = left 
		End If
		If job.JobName = "load_headbaner_main" Then
			Try
				head_slider.Initialize("head_slider")
				main_scrollview.Panel.AddView(head_slider,0,0,100%x,60%x)
				Dim parser As JSONParser
				parser.Initialize(job.GetString)
				Dim root As List = parser.NextArray
				For Each colroot As Map In root
					Dim link As String = colroot.Get("link")
					Dim text As String = colroot.Get("text")
					Dim pic As String = colroot.Get("pic")
					head_slider.AddSlide(text,pic)
				Next
				main_baner.Visible = False
				head_slider.SetTransition(head_slider.SLIDER_TRANSITION_Default)
				head_slider.Delay = 5000

				head_slider.Start
			Catch
				Log(LastException)
			End Try
		End If
		If job.JobName = "load_category_main" Then
			Dim left As Int=8dip
			Dim parser As JSONParser
			parser.Initialize(job.GetString)
			Dim root As List = parser.NextArray
			For Each colroot As Map In root
				Dim id As String = colroot.Get("id")
				Dim text As String = colroot.Get("name")
				Dim lable As Label
				lable.Initialize("lable")
				lable.Color = Colors.rgb(102, 187, 106)
				lable.TextColor = Colors.White
				lable.Gravity = Gravity.CENTER
				lable.Typeface = Typeface.LoadFromAssets("yekan.ttf")
				lable.TextSize = "20"
				lable.Text = text
				category_panel.AddView(lable,left,5dip,(text.Length * 30 ),45dip)
				left =( text.Length * 30  ) + left +8dip
			Next
			Dim r As Reflector
			r.Target = category_hscrollview
			r.RunMethod2("setHorizontalScrollBarEnabled", False, "java.lang.boolean")
			r.RunMethod2("setOverScrollMode", 2, "java.lang.int" )
			category_panel.Width = left + 8dip
			category_hscrollview.Panel.Width = left 
			category_hscrollview.FullScroll(True)
			category_hscrollview.ScrollPosition = 50dip
		End If
	End If
End Sub
 