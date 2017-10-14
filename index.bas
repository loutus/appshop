﻿Type=Activity
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
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("main")
	header_logo.Bitmap = LoadBitmap(File.DirAssets,"setting/header_logo.png")
	
	extra.load_category_main
		
	main_scrollview.Panel.LoadLayout("main_panel")
	category_hscrollview.Panel.LoadLayout("category")
	category_hscrollview.Panel.Width = 800dip
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub jobdone(job As HttpJob)
	If job.Success = True Then
		If job.JobName = "load_category_main" Then
			Dim left As Int=20dip
			Dim parser As JSONParser
			parser.Initialize(job.GetString)
			Dim root As List = parser.NextArray
			For Each colroot As Map In root
				Dim id As String = colroot.Get("id")
				Dim text As String = colroot.Get("text")
				Dim lable As Label
				lable.Initialize("lable")
				lable.Color = Colors.Black
				lable.Text = text
				category_panel.AddView(lable,left,5dip,(text.Length * 40 ),45dip)
				left = left +50dip
			Next
		End If
	End If
End Sub
 