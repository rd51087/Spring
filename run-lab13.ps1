$ErrorActionPreference = 'Stop'

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$backend = Join-Path $root 'backend'
$frontend = Join-Path $root 'frontend'

Start-Process -FilePath 'cmd.exe' -ArgumentList '/c', 'mvnw spring-boot:run', '-f', $backend -WorkingDirectory $backend
Start-Process -FilePath 'cmd.exe' -ArgumentList '/c', 'npm install', '-d', $frontend -WorkingDirectory $frontend
Start-Process -FilePath 'cmd.exe' -ArgumentList '/c', 'npm start', '-d', $frontend -WorkingDirectory $frontend
