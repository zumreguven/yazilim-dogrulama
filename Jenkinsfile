pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 17'
    }

    environment {
        CHROME_BIN = '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome'
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
                echo '3. Birim testleri çalıştırılıyor...'
                sh 'mvn test -Dtest=*UnitTest'
                junit '**/target/surefire-reports/*.xml'
                jacoco(
                    execPattern: '**/target/jacoco.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src/main/java',
                    exclusionPattern: '**/target/generated-sources/**'
                )
            }
        }

        stage('Integration Tests') {
            steps {
                echo '4. Entegrasyon testleri çalıştırılıyor...'
                sh 'mvn verify -DskipUnitTests -Dtest=*IT'
                junit '**/target/failsafe-reports/*.xml'
            }
        }

        stage('Selenium Tests') {
            steps {
                echo '5. Selenium testleri çalıştırılıyor...'
                script {
                    try {
                        sh 'mvn test -Dtest=*SeleniumTest -Dselenium.headless=true'
                        junit '**/target/surefire-reports/*.xml'
                    } catch (Exception e) {
                        echo "Selenium testlerinde hata: ${e}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }

    post {
        always {
            echo '6. Temizlik yapılıyor...'
            cleanWs()
        }
        success {
            echo 'Tüm testler başarıyla tamamlandı!'
        }
        failure {
            echo 'Build başarısız oldu, lütfen logları kontrol edin.'
        }
    }
}