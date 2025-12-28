pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 17'  // Java 17'ye geçiyoruz
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-credentials')
        // Chrome için gerekli ortam değişkenleri
        CHROME_BIN = '/usr/bin/google-chrome'
        CHROME_DRIVER = '/usr/local/bin/chromedriver'
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
                sh 'mvn test -Dspring.profiles.active=test -Dtest=*UnitTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                echo '4. Entegrasyon testleri çalıştırılıyor...'
                sh 'mvn verify -DskipUnitTests -Dgroups=integration'
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
                script {
                    try {
                        // Chrome'un kurulu olduğunu kontrol et
                        sh 'which google-chrome || echo "Chrome bulunamadı"'
                        sh 'chromedriver --version || echo "ChromeDriver bulunamadı"'

                        // Testleri çalıştır
                        sh 'mvn test -Dtest=*SeleniumTest -Dselenium.headless=true'
                    } catch (Exception e) {
                        echo "Selenium testlerinde hata: ${e}"
                        // Hata durumunda pipeline'ı durdurma, devam et
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
            post {
                always {
                    // Selenium test sonuçlarını topla
                    junit '**/target/surefire-reports/*.xml'
                    // Ekran görüntülerini arşivle (eğer varsa)
                    archiveArtifacts '**/screenshots/*.png'
                }
            }
        }
    }

    post {
        always {
            echo '7. Temizlik yapılıyor...'
            script {
                try {
                    sh 'docker compose -f docker-compose.ci.yml down'
                } catch (Exception e) {
                    echo "Docker temizleme hatası: ${e}"
                }
                // Workspace'i temizle
                cleanWs()
            }
        }
    }
}