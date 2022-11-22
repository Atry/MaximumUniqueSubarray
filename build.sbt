scalaVersion := "2.13.7"

run / fork := true

run / javaOptions := Seq("-Xms5m", "-Xmx5m", "-Xss1m")
