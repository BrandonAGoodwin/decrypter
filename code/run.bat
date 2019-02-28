rem set path=%path%;C:\Program Files\Java\jdk1.8.0_101\bin

if not exist "%cd%/classes" mkdir classes

javac -d classes -cp classes src/com/bxg796/files/*.java
javac -d classes -cp classes src/com/bxg796/main/models/*.java
javac -d classes -cp classes src/com/bxg796/main/controllers/*.java
javac -d classes -cp classes src/com/bxg796/main/views/*.java
javac -d classes -cp classes src/com/bxg796/main/*.java
pause
java -cp classes com.bxg796.main.Main
pause
