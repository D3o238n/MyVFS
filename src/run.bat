@echo off
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Java not found. Please install JDK first.
    pause
    exit /b 1
)

javac VFSApp.java
java VFSApp %*
pause