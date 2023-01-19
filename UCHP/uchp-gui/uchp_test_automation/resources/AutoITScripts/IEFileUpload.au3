;Local $pathToSave="C:\Prod_GIT\uchp-gui\uchp_test_automation\resources\DownloadLocation\listOutstandingClaimsDocumentsDealer.pdf"
Local $pathOfFileTobeUploaded=$CmdLine[1];
sleep(300)
WinWaitActive("Choose File to Upload")
Send($pathOfFileTobeUploaded)
Send("{ENTER}")