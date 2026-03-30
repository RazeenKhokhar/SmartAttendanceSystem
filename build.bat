@echo off
echo Cleaning classes directory...
rmdir /s /q classes 2>nul
mkdir classes

echo Compiling...
javac -cp "libs/*" -d classes -sourcepath src src/Main.java src/ui/*.java src/model/*.java src/util/*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Build successful! Run with: java -cp "libs/*;classes" ui.MainFrame
pause
