@echo off
::Use the -d flag to run the bot in detached mode.

call gradlew.bat --no-daemon build
if "%1"=="-d" (start javaw -jar "build\libs\beo.jar") else (java -jar "build\libs\beo.jar")