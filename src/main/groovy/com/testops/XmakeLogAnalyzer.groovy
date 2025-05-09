package com.testops

class XmakeLogAnalyzer {
    static void main(String[] args) {
        def logLines = [
                "ERROR: Plugin 'xmake-core' not found",
                "WARNING: Deprecated API used in build",
                "INFO: Build started at 2025-05-08 10:00:00",
                "ERROR: Failed to resolve dependency org.example:lib:1.0",
                "INFO: Build finished successfully"
        ]

        def categorized = categorizeErrors(logLines)

        println "Categorized Errors:"
        categorized.each { category, messages ->
            println "\n${category}:"
            messages.each { println "- ${it}"}
        }
    }

    static Map<String, List<String>> categorizeErrors(List<String> logLines) {
        def categories = [
                "Plugin Issues" : [],
                "Dependency Issues" : [],
                "General Warnings" : [],
                "Other Errors" : []
        ]

        logLines.each { line ->
            if (line.contains("Plugin") && line.contains("ERROR")) {
                categories["Plugin Issues"] << line
            } else if (line.contains("Failed to resolve")) {
                categories["Dependency Issues"] << line
            } else if (line.contains("WARNING")) {
                categories["General Warnings"] << line
            } else if (line.contains("ERROR")) {
                categories["Other Errors"] << line
            }
        }

        return categories
    }
}
