pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'Checking...'
        withGradle() {
          sh './gradlew assembleDebug'
        }

      }
    }

  }
}