pipeline {
    agent any

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
                echo '3. Birim testleri çalıştırılıyor...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco(
                        execPattern: '**/target/jacoco.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: 'src/main/java',
                        exclusionPattern: '**/test/**'
                    )
                }
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
                echo '5. Docker konteynerları başlatılıyor...'
                sh 'docker-compose -f docker-compose.ci.yml up -d'
                // Uygulamanın başlaması için bekle
                sh 'sleep 30'
            }
        }

        stage('Selenium Tests - Login') {
            steps {
                echo '6.1. Giriş Testleri Çalıştırılıyor...'
                sh 'mvn test -Dtest=LoginSeleniumTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Selenium Tests - Home Page') {
            steps {
                echo '6.2. Ana Sayfa Testleri Çalıştırılıyor...'
                sh 'mvn test -Dtest=HomePageSeleniumTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Selenium Tests - Career Goals') {
            steps {
                echo '6.3. Kariyer Hedefi Testleri Çalıştırılıyor...'
                sh 'mvn test -Dtest=CareerGoalSeleniumTest'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo '5. Docker imajı oluşturuluyor...'
                sh 'docker-compose -f docker-compose.ci.yml build'
            }
        }

        stage('Run System Tests') {
            steps {
                echo '6. Selenium test senaryoları çalıştırılıyor...'
                script {
                    try {
                        // Uygulama ve bağımlılıkları başlat
                        sh 'docker-compose -f docker-compose.ci.yml up -d'
                        
                        // Uygulamanın başlamasını bekle
                        sh 'sleep 60'
                        
                        // Testleri çalıştır
                        sh 'mvn test -Dtest=*SeleniumTest'
                        
                    } finally {
                        // Test sonrası temizlik
                        sh 'docker-compose -f docker-compose.ci.yml down --remove-orphans'
                    }
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts '**/screenshots/*.png'
                }
            }
        }
    }
    
    post {
        always {
            // Sonuçları temizle
            cleanWs()
        }
    }
}
