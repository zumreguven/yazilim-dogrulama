pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 23'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                echo '1. GitHub\'dan kodlar çekiliyor...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '2. Kodlar derleniyor...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test'
            }
        }

        stage('Integration Tests') {
            steps {
                echo '4. Entegrasyon testleri çalıştırılıyor...'
                sh 'mvn verify -DskipUnitTests'
            }
            post {
                always {
                    junit '**/target/failsafe-reports/*.xml'
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                echo '5. Docker Compose başlatılıyor...'
                sh 'docker compose -f docker-compose.ci.yml up -d'
                sh 'sleep 30' // Uygulamanın başlaması için bekle
            }
        }

        stage('Selenium Tests') {
            steps {
                echo '6. Selenium testleri çalıştırılıyor...'
                sh 'mvn test -Dtest=LoginSeleniumTest,HomePageSeleniumTest,CareerGoalSeleniumTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        always {
            echo '7. Temizlik yapılıyor...'
            sh 'docker compose -f docker-compose.ci.yml down'
        }
    }
}