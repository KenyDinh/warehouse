@echo off
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c%%a%%b)
For /f "tokens=1-2 delims=/:" %%a in ("%TIME%") do (set mytime=%%a%%b)
set name=backup/warehouse_%mydate%_%mytime%_backup.sql
mysqldump -uroot -pkenydinh2609 warehouse > %name%
