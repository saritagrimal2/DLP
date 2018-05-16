
cls
cd %~dp0
cd src
java -cp JFlex.jar JFlex.Main -d lexico lexico\lexico.jflex
pause
cd..
cd tools\byaccj
yacc.exe -J -v -Jpackage=sintactico -Jsemantic=Object "../../src/sintactico/sintactico.y"
move Parser.java ../../src/sintactico
move y.output ../../src/sintactico
pause