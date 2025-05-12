#!/usr/bin/env groovy

import groovy.json.*

def secretsFile = new File("secrets/secrets.json")
if (!secretsFile.exists()) {
    println "❌ Secrets file not found at ${secretsFile.absolutePath}"
    System.exit(1)
}

def slurper = new JsonSlurper()
def data = slurper.parse(secretsFile)

data.accounts.each { account ->
    def newPassword = UUID.randomUUID().toString().replaceAll("-","").take(12)
    println "🔄 Rotated password for '${account.name}' → ********"
    account.password = newPassword
}

// Write back to file
secretsFile.text = JsonOutput.prettyPrint(JsonOutput.toJson(data))
println "✅ Secrets updated at: ${secretsFile.absolutePath}"