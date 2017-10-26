    @echo off  
    set /p outFile=please input the  name of the output file:  
    set /p fList=please input the file list:  
    for /f %%i in (%fList%) do type %%i >> %outFile%  
    java -jar D:\yuicompressor\build\yuicompressor-2.4.8.jar --type js --charset utf-8  %outFile%  -o %outFile%  