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

	Private header_logo As ImageView
	Private main_scrollview As ScrollView
	Private category_hscrollview As HorizontalScrollView
	Private category_panel As Panel
	Private main_baner As ImageView
	Dim head_slider As Slider
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("main")
	header_logo.Bitmap = LoadBitmap(File.DirAssets,"setting/header_logo.png")

	extra.load_category_main
	extra.load_headbaner_main
	
	main_scrollview.Panel.LoadLayout("main_panel")
	main_baner.Bitmap = LoadBitmap(File.DirAssets,"setting/main_baner.jpg")
	main_scrollview.Panel.Height = 1500dip
	category_hscrollview.Panel.LoadLayout("category")
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub jobdone(job As HttpJob)
	If job.Success = True Then
		If job.JobName = "load_headbaner_main" Then
			Try
				
				Log(job.GetString)
				head_slider.Initialize("head_slider")
				main_scrollview.Panel.AddView(head_slider,0,0,100%x,60%x)
				Dim parser As JSONParser
				parser.Initialize(job.GetString)
				Dim root As List = parser.NextArray
				For Each colroot As Map In root
					Dim id As String = colroot.Get("id")
					Dim text As String = colroot.Get("text")
					Dim pic As String = colroot.Get("pic")
					head_slider.AddSlide(text,pic)
				Next
				main_baner.Visible = False
				head_slider.SetTransition(head_slider.SLIDER_TRANSITION_Fade)
				head_slider.Delay = 5000
				head_slider.Start
			Catch
				Log(LastException)
			End Try
		End If
		If job.JobName = "load_category_main" Then
			Dim left As Int=20dip
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
			category_panel.Width = left + 10dip
			category_hscrollview.Panel.Width = left + 8dip
			category_hscrollview.FullScroll(True)
			category_hscrollview.ScrollPosition = 50dip
		End If
	End If
End Sub
 