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
                deploy adapters: [tomcat9(credentialsId: '38c2ac6a-f478-4051-9145-9f04b7c4f304', path: '', url: 'http://34.16.131.174/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
