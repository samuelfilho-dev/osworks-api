pipeline {
    agent any
    tools {
        maven 'local_maven' 
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
                          userRemoteConfigs: [[url: 'https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git']]])
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -f pom.xml clean install'
            }
            post {
                success {
                    echo "Arquivando os Artefatos"
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage('Deploy para o servidor Tomcat') {
            steps {
                deploy adapters: [tomcat9(credentialsId: '38c2ac6a-f478-4051-9145-9f04b7c4f304', path: '', url: 'http://34.16.131.174/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
