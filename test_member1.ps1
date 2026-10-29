<#
.SYNOPSIS
    Test script for Member 1 (Thái Quang Huy - QS190001) in SE21B - Group 1.
.DESCRIPTION
    Runs isolated and automated unit tests for:
    - Test 1: Room Input Validation & Boundaries (Empty ID, Whitespace, Capacity, Price limits)
    - Test 2: Polymorphic Room Charge Calculation (StandardRoom vs ServiceRoom)
    - Test 3: Resident Management & Vacancy Logic (Add, Remove, Full room, Duplicates)
    - Test 4: Dynamic Price Rate Updates & Snapshot Integrity
    - Test 5: Console Menu Integration Simulation (Menu 1 Room List & Menu 7 Rates)
    - All: Run all 5 tests
.EXAMPLE
    .\test_member1.ps1
    .\test_member1.ps1 -Test 1
    .\test_member1.ps1 all
#>

[CmdletBinding()]
param(
    [Parameter(Position = 0, Mandatory = $false)]
    [string]$Test = ""
)

$rootDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $rootDir

Write-Host "==================================================================" -ForegroundColor Cyan
Write-Host "  COMPILING PROJECT (JDK 8 / UTF-8)..." -ForegroundColor Cyan
Write-Host "==================================================================" -ForegroundColor Cyan

$buildDir = Join-Path $rootDir "PRO192Project\build\classes"
if (-not (Test-Path $buildDir)) {
    New-Item -ItemType Directory -Path $buildDir -Force | Out-Null
}

$javaSources = Get-ChildItem -Path "PRO192Project\src\pro192project\*.java" | ForEach-Object { $_.FullName }
& javac -source 1.8 -target 1.8 -encoding UTF-8 -d $buildDir $javaSources

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed. Please verify your JDK installation." -ForegroundColor Red
    exit $LASTEXITCODE
}
Write-Host "[OK] Compilation successful!`n" -ForegroundColor Green

function Invoke-Runner([string]$target) {
    Write-Host "`nRunning Test Selection: $target" -ForegroundColor Yellow
    & java -cp $buildDir pro192project.Member1TestRunner $target
}

if ($Test -ne "") {
    Invoke-Runner $Test
    exit $LASTEXITCODE
}

# Interactive Menu Mode
while ($true) {
    Write-Host "`n==================================================================" -ForegroundColor Cyan
    Write-Host "  MEMBER 1 (THÁI QUANG HUY - QS190001) - FEATURE TEST SUITE" -ForegroundColor Cyan
    Write-Host "  Dorm Utility Management System - SE21B - Group 1" -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "  [1] Test 1: Room Input Validation & Boundary Checks" -ForegroundColor White
    Write-Host "  [2] Test 2: Polymorphism & Room Charge Calculation" -ForegroundColor White
    Write-Host "  [3] Test 3: Resident Management & Vacancy Logic" -ForegroundColor White
    Write-Host "  [4] Test 4: Dynamic Price Rate Updates & Snapshot Integrity" -ForegroundColor White
    Write-Host "  [5] Test 5: Menu Workflow Simulation (Menu 1 & Menu 7)" -ForegroundColor White
    Write-Host "  [A] Run ALL Member 1 Tests" -ForegroundColor Green
    Write-Host "  [0] Exit" -ForegroundColor Gray
    Write-Host "==================================================================" -ForegroundColor Cyan

    $choice = Read-Host "Select an option [1-5, A, 0]"
    $choice = $choice.Trim()

    if ($choice -eq "0" -or $choice.ToLower() -eq "exit") {
        Write-Host "Exiting. Goodbye!" -ForegroundColor Yellow
        break
    } elseif ($choice -match '^[1-5aA]$' -or $choice.ToLower() -eq "all") {
        Invoke-Runner $choice
    } else {
        Write-Host "Invalid choice '$choice'. Please select 1, 2, 3, 4, 5, A, or 0." -ForegroundColor Red
    }
}
