pipeline {
    agent any

    environment {
        TESTCONTAINERS_RYUK_DISABLED = 'true'
        TESTCONTAINERS_HOST_OVERRIDE = 'host.docker.internal'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
    }
}