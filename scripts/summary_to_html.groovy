#!/usr/bin/env groovy

import groovy.json.JsonSlurper

def inputFile = new File("reports/test_summary.json")
def outputFile = new File("reports/test_summary.html")

if (!inputFile.exists()) {
    println "❌ Summary JSON not found: ${inputFile.absolutePath}"
    System.exit(1)
}

def summary = new JsonSlurper().parse(inputFile)

def html = new StringBuilder()
html << "<html><head><style>"
html << "table { border-collapse: collapse; } th, td { border: 1px solid #ccc; padding: 6px; }"
html << "</style></head><body>"
html << "<h2>Test Summary</h2><ul>"
html << "<li>Total Tests: ${summary.totalTests}</li>"
html << "<li>Failures: ${summary.totalFailures}</li>"
html << "<li>Errors: ${summary.totalErrors}</li>"
html << "<li>Skipped: ${summary.totalSkipped}</li>"
html << "</ul>"

html << "<h3>Test Suites</h3><table><tr><th>Name</th><th>Tests</th><th>Failures</th><th>Errors</th><th>Skipped</th></tr>"
summary.testsuites.each { suite ->
    html << "<tr><td>${suite.name}</td><td>${suite.tests}</td><td>${suite.failures}</td><td>${suite.errors}</td><td>${suite.skipped}</td></tr>"
}
html << "</table></body></html>"

outputFile.text = html.toString()
println "✅ HTML report generated: ${outputFile.absolutePath}"
