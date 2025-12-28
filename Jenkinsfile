pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }



        stage('Unit Tests') {
            steps {
                echo '3. Birim testleri çalıştırılıyor...'
                sh 'mvn test -Dtest=*Test'
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
