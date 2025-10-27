def login() {
  withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'username', passwordVariable: 'password')]) {
    sh "docker login -u ${username} -p ${password}"
  }
}

def build(String tag) {
  sh "docker build -t ${tag} ./"
}

def push(String tag) {
  sh "docker push ${tag}"
}
