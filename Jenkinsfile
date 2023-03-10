 //codigo do jenkins file pendente para ser inserido aqui >by Fransualdo Lopes
pipeline {
    agent any
 tools{
     marven 'local_maven' 
 }
 
    stages {
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post{
                success{
                  echo"Arquivando os Artefatos"
                  archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage('Deploy para o servidor Tomcat') {
            steps {
                deploy adapters: [tomcat9(credentialsId: '859434f7-cdc4-43d1-9c9b-050215adf628', path: '', url: 'http://34.85.142.248/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
