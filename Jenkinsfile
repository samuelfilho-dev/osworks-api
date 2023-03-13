pipeline {
    agent any
    
    tools {
        // Adicione as ferramentas necessárias para o pipeline
        maven 'local_maven'
        nodejs 'local_node'
    }

    stages {
        stage('Build Frontend') {
            // Defina as etapas necessárias para a construção do frontend
            steps {
                // Clone o repositório do frontend
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Fransualdo-Lopes/albino-tecnologia.git']]])
                // Instale as dependências do Node.js
                sh 'npm install'
                // Execute o script de construção do frontend
                sh 'npm run build'
            }
            post {
                success {
                    // Arquive os artefatos do frontend
                    archiveArtifacts artifacts: 'build/**'
                }
            }
        }

        stage('Build Backend') {
            // Defina as etapas necessárias para a construção do backend
            steps {
                // Clone o repositório do backend
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Fransualdo-Lopes/osworks-api.git']]])
                // Execute o comando Maven inserindo o caminho do pom.xml para construir o backend 
                sh 'mvn -f osworks-api/osworks/pom.xml clean package'
            }
            post {
                success {
                    // Arquive os artefatos do backend
                    archiveArtifacts artifacts: 'target/*.jar'
                }
            }
        }

        stage('Deploy') {
            // Defina as etapas necessárias para o deploy
            steps {
                // Faça o deploy dos artefatos do frontend e do backend
                deploy adapters: [tomcat9(credentialsId: '38c2ac6a-f478-4051-9145-9f04b7c4f304', path: '', url: 'http://34.16.131.174/')], contextPath: null, war: 'backend/target/*.war'
                deploy adapters: [tomcat9(credentialsId: '38c2ac6a-f478-4051-9145-9f04b7c4f304', path: '', url: 'http://34.16.131.174/')], contextPath: 'frontend', war: 'frontend/build/*.war'
            }
        }
    }
}
