@echo off
chcp 65001 >nul
cls
echo ========================================
echo    EJECUTANDO PRACTICA DE PROGRAMACION
echo ========================================
echo.

REM Comprueba si Java esta disponible en el equipo
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] No se encuentra Java instalado en este equipo.
    echo Asegurate de tener Java instalado para ejecutar este programa.
    echo.
    pause
    exit /b
)

echo [OK] Compilando el codigo fuente...
javac Practica.java

if errorlevel 1 (
    echo.
    echo [ERROR] La compilacion ha fallado. Revisa el codigo.
    pause
    exit /b
)

echo.
echo [OK] Ejecutando el programa...
echo ----------------------------------------
echo.

REM Ejecuta el programa compilado
java Practica

echo.
echo ----------------------------------------
echo [FIN] Programa finalizado.
pause