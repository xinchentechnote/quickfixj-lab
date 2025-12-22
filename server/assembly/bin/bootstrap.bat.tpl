@echo off
setlocal ENABLEDELAYEDEXPANSION

REM ############################################################
REM Appassembler startup script (Windows)
REM ############################################################

set APP_NAME=@APP_NAME@

REM Resolve APP_HOME
set SCRIPT_DIR=%~dp0
set APP_HOME=%SCRIPT_DIR%\..
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

if "%BASEDIR%"=="" (
    set BASEDIR=%APP_HOME%
)

set LOG_DIR=%BASEDIR%\logs
set PID_FILE=%BASEDIR%\%APP_NAME%.pid

if "%REPO%"=="" (
    set REPO=%BASEDIR%\lib
)

set JAVA_CMD=java
set JAVA_OPTS=
set MAIN_CLASS=@MAINCLASS@
set CLASSPATH=@CLASSPATH@

if not exist "%LOG_DIR%" (
    mkdir "%LOG_DIR%"
)

REM ------------------------------------------------------------
REM Functions
REM ------------------------------------------------------------

:is_running
if exist "%PID_FILE%" (
    set /p PID=<"%PID_FILE%"
    tasklist /FI "PID eq %PID%" | find "%PID%" >nul
    if %ERRORLEVEL%==0 (
        exit /b 0
    )
)
exit /b 1

:start
call :is_running
if %ERRORLEVEL%==0 (
    echo %APP_NAME% already running (PID found)
    exit /b 0
)

echo Starting %APP_NAME%...
echo JAVA_CMD   = %JAVA_CMD%
echo MAIN_CLASS = %MAIN_CLASS%
echo CLASSPATH  = %CLASSPATH%

start "" /b %JAVA_CMD% %JAVA_OPTS% ^
    -cp "%CLASSPATH%" ^
    %MAIN_CLASS% ^
    > "%LOG_DIR%\stdout.log" 2>&1

for /f "tokens=2 delims=," %%p in (
    'tasklist /FI "IMAGENAME eq java.exe" /FO CSV ^| find "java.exe"'
) do (
    set PID=%%~p
)

echo !PID! > "%PID_FILE%"
echo %APP_NAME% started (PID=!PID!)
exit /b 0

:stop
call :is_running
if not %ERRORLEVEL%==0 (
    echo %APP_NAME% not running
    exit /b 0
)

set /p PID=<"%PID_FILE%"
echo Stopping %APP_NAME% (PID=%PID%)...
taskkill /PID %PID% /T /F >nul 2>&1

del "%PID_FILE%"
echo Stopped
exit /b 0

:status
call :is_running
if %ERRORLEVEL%==0 (
    set /p PID=<"%PID_FILE%"
    echo %APP_NAME% is running (PID=%PID%)
) else (
    echo %APP_NAME% is stopped
)
exit /b 0

:restart
call :s
