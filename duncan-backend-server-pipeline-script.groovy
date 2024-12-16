pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven'
        git 'git'
    }
    
    stages {
        stage('Checkout Master Branch') {
            steps {
                git branch: 'master', url: 'https://github.com/drasplundh/AngularProject.git'
            }
        }
        
        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'mvn clean install'
                }
            }
                
        }
        
        stage('Run Backend Server') {
            steps {
                dir('backend') {
                    sh 'nohup java -jar target/backend-0.0.1-SNAPSHOT.jar &'
                }
            }
        }
    }

}