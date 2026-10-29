@echo off
setlocal enabledelayedexpansion
title Member 1 (Thai Quang Huy) - Test Runner

cd /d "%~dp0"

echo ==================================================================
echo   COMPILING PROJECT (JDK 8 / UTF-8)...
echo ==================================================================
if not exist "PRO192Project\build\classes" mkdir "PRO192Project\build\classes"

javac -source 1.8 -target 1.8 -encoding UTF-8 -d PRO192Project\build\classes PRO192Project\src\pro192project\*.java
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Compilation failed. Please check Java installation.
    pause
    exit /b %ERRORLEVEL%
)
echo [OK] Compilation successful!
echo.

if not "%~1"=="" (
    java -cp PRO192Project\build\classes pro192project.Member1TestRunner %~1
    exit /b %ERRORLEVEL%
)

:MENU
cls
echo ==================================================================
echo   MEMBER 1 (THAI QUANG HUY - QS190001) - FEATURE TEST SUITE
echo   Dorm Utility Management System - SE21B - Group 1
echo ==================================================================
echo   [1] Test 1: Room Input Validation ^& Boundary Checks
echo   [2] Test 2: Polymorphism ^& Room Charge Calculation
echo   [3] Test 3: Resident Management ^& Vacancy Logic
echo   [4] Test 4: Dynamic Price Rate Updates ^& Snapshot Integrity
echo   [5] Test 5: Menu Workflow Simulation (Menu 1 ^& Menu 7)
echo   [A] Run ALL Member 1 Tests
echo   [0] Exit
echo ==================================================================
set /p CHOICE="Choose an option [1-5, A, 0]: "

if /i "%CHOICE%"=="0" goto EXIT
if /i "%CHOICE%"=="1" goto RUN_TEST
if /i "%CHOICE%"=="2" goto RUN_TEST
if /i "%CHOICE%"=="3" goto RUN_TEST
if /i "%CHOICE%"=="4" goto RUN_TEST
if /i "%CHOICE%"=="5" goto RUN_TEST
if /i "%CHOICE%"=="A" goto RUN_TEST
if /i "%CHOICE%"=="ALL" goto RUN_TEST

echo [ERROR] Invalid selection! Please enter 1, 2, 3, 4, 5, A, or 0.
timeout /t 2 >nul
goto MENU

:RUN_TEST
cls
java -cp PRO192Project\build\classes pro192project.Member1TestRunner %CHOICE%
echo.
echo Press any key to return to menu...
pause >nul
goto MENU

:EXIT
echo Exiting...
exit /b 0
