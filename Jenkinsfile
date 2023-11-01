pipeline {
    agent any
 
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'Oussemahmaied', url: 'https://github.com/Souhajaidi1/DevOps'
            }
        }
        stage ("Build"){
          steps{
               sh"mvn-version"
               bat"mvn clean package -DskipTests"
               }
             }
         stage("")
    }
}
