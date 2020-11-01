$Env:classpath += ";$(Get-Location)\target\classes"
echo "$(Get-Location)\target\classes added to classpath"
$Env:PORT = 3000
$Env:APIKEY = "656fc6ced8b49f7b5d51f8e68d78e400"