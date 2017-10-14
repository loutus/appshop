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