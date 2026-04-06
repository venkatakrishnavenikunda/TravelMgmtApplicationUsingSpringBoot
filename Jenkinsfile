pipeline {
    agent any

    tools {
        maven 'Maven'   // Name configured in Jenkins (Global Tool Configuration)
        jdk 'JDK17'     // Your installed JDK name in Jenkins
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/venkatakrishnavenikunda/TravelMgmtApplicationUsingSpringBoot.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Run Application') {
            steps {
                bat 'java -jar target\\*.jar'
            }
        }
    }

    post {
        success {
            echo 'Build and Run Successful ✅'
        }
        failure {
            echo 'Build Failed ❌'
        }
    }
}