   WinWait("Save As")
	  WinActivate("Save As")
	  ControlFocus("Save As", "","ToolbarWindow324")
	  Send("C:\MasterCodeClone\uchp-gui\uchp_test_automation\resources\DownloadLocation\")
	  ControlSetText("Save As","","ToolbarWindow324","C:\MasterCodeClone\uchp-gui\uchp_test_automation\resources\DownloadLocation\")

	  ControlClick("Save As","","Button2")


