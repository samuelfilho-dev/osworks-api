 //codigo do jenkins file pendente para ser inserido aqui >by Fransualdo Lopes
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'scp target/myapp.war user@server:/opt/tomcat/webapps'// falta defimir o no servidor tomcat juntamente com o proxy reverso do apache2
            }
        }
    }
}
