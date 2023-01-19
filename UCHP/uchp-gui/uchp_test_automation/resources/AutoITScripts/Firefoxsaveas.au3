; wait for 8 seconds to appear download and save dialog. Used class property of download dialog.
WinWait("[CLASS:#MozillaDialogClass]","",8)

; Perform keyboard ALT key down + s + ALT key Up action to select Save File Radio button using keyboard sortcut.
Send("{ALTDOWN}s{ALTUP}")


Sleep(500)

; Press Keyboard ENTER button.
Send("{ENTER}")

Sleep(500)

Send("{ESCAPE}")