@ECHO OFF
ECHO Starting geckodriver: %0 %*
.\resources\Drivers\geckodriver.exe --log fatal %* > NUL 2>&1