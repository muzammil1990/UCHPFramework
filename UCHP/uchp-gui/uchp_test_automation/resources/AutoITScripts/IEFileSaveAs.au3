
;Local $pathToSave="C:\Prod_GIT\	-gui\uchp_test_automation\resources\DownloadLocation\listOutstandingClaimsDocumentsDealer.pdf"
Local $pathToSave=$CmdLine[1];

; get the handle of main window
Local $windHandle=WinGetHandle("[Class:IEFrame]", "")
Local $winTitle = "[HANDLE:" & $windHandle & "]";
;get coordinates of default HWND
Local $ctlText=ControlGetPos ($winTitle, "", "[Class:DirectUIHWND;INSTANCE:1]")

; wait till the notification bar is displayed
Local $color= PixelGetColor ($ctlText[0],$ctlText[1])
while $color <> 0
sleep(500)
$ctlText=ControlGetPos ($winTitle, "", "[Class:DirectUIHWND;INSTANCE:1]")
$color= PixelGetColor ($ctlText[0],$ctlText[1])
wend

; Select save as option
WinActivate ($winTitle, "")
Send("{F6}")
sleep(500)
;Send("{TAB}")
;sleep(500)
Send("{DOWN}")
sleep(300)
Send("a")

; wait for Save As window
WinWait("Save As")
; activate Save As window
WinActivate("Save As")
; path to save the file is passed as command line arugment
ControlFocus("Save As","","[CLASS:Edit;INSTANCE:1]")
Send($pathToSave)
;click on save button
WinWait("Save As")
;Send("{ENTER}")
sleep(1000)
ControlClick("Save As","","[CLASS:Button;INSTANCE:1]")

; wait till the download completes
Local $sAttribute = FileGetAttrib($pathToSave);
while $sAttribute = ""
sleep(700)
$sAttribute = FileGetAttrib($pathToSave)
wend
sleep(2000)
;close the notification bar
WinActivate ($winTitle, "")
Send("{F6}")
sleep(300)
Send("{TAB}")
sleep(300)
Send("{TAB}")
sleep(300)
Send("{TAB}")
sleep(500)
Send("{ENTER}")
