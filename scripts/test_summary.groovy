#!/usr/bin/env groovy

import groovy.util.XmlParser
import groovy.json.JsonOutput


def reportsDir = new File("target/surefire-reports")
def summary = [
        totalTests : 0,
        totalFailures : 0,
        totalErrors : 0,
        totalSkipped : 0,
        testsuites: []
]

if (!reportsDir.exists()) {
    println "No surefire reports found at ${reportsDir.absolutePath}"
    System.exit(0)
}

def parser = new XmlParser()
reportsDir.eachFileMatch(~/TEST-.*\.xml/) { file ->
    def suite = parser.parse(file)
    def suiteSummary = [
            name     : suite.'@name',
            tests    : suite.'@tests'.toInteger(),
            failures : suite.'@failures'.toInteger(),
            errors   : suite.'@errors'.toInteger(),
            skipped  : suite.'@skipped'.toInteger()
    ]

    summary.totalTests   += suiteSummary.tests
    summary.totalFailures += suiteSummary.failures
    summary.totalErrors   += suiteSummary.errors
    summary.totalSkipped  += suiteSummary.skipped
    summary.testsuites << suiteSummary
}

def outputFile = new File("reports/test_summary.json")
outputFile.parentFile.mkdirs()
outputFile.text = JsonOutput.prettyPrint(JsonOutput.toJson(summary))

println "Test summary written to ${outputFile.absolutePath}"
