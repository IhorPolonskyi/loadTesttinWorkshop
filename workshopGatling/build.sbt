
name := "load-service"

version := "0.1"

scalaVersion := "2.12.5"

enablePlugins(GatlingPlugin)

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.1"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.3.1"