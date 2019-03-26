name := "native_mysql"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.15"
)

assemblyJarName in assembly := "fat.jar"
