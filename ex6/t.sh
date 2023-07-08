c() {
    rm *.class
}
trap c EXIT
javac *.java
