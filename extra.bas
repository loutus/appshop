Type=StaticCode
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim api As String
End Sub
Sub load_category_main()
	Dim load_category As HttpJob
	load_category.Initialize("load_category_main",index)
	load_category.PostString(api,"op=category")
End Sub

Sub load_headbaner_main()
	Dim load_category As HttpJob
	load_category.Initialize("load_headbaner_main",index)
	load_category.PostString(api,"op=headbaner")
End Sub

Sub load_lastproduct_main()
	Dim load_category As HttpJob
	load_category.Initialize("load_lastproduct_main",index)
	load_category.PostString(api,"op=mostview")
End Sub

Sub InitPanel(pnl As Panel,BorderWidth As Float, FillColor As Int, BorderColor As Int)
	Dim Rec As Rect
	Dim Canvas1 As Canvas
	Dim BorderWidth_2 As Float
	BorderWidth_2=BorderWidth/2
	Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Width-BorderWidth_2,pnl.Height-BorderWidth_2)
	Canvas1.Initialize(pnl)
	Canvas1.DrawRect(Rec,FillColor,True,FillColor)
	Canvas1.DrawRect(Rec,BorderColor,False,BorderWidth)
End Sub

Sub main_download_image(name,image)
	Dim idownload As HttpJob
	idownload.Initialize("imageview*" & name & "*" & image,index)
	idownload.Download(image)
End Sub