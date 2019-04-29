call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Opening runcrud.bat has errors
goto fail

:runbrowser
START firefox.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot show Tasks list

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished