def label = "jenkins-slave"
//def label = "devops-${UUID.randomUUID().toString()}"

podTemplate(
        label: label, 
        containers: [
            containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'gradle', image: 'gradle:5.6-jdk8', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.15.3', command: 'cat', ttyEnabled: true)
        ],
        volumes: [ 
            hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
        ]) {
    node(label) {
        def githubRepository = "https://github.com/Starwars2020/spring-boot-api-03.git"
        def githubCredential = "github-api-token"
        def dockerRegistry = "localhost:5000"
        def dockerRegistryCredential = ""
        def dockerImageName = "localhost:5000/spring-boot-api-03-app"
        def dockerImageTags = "1.0"
        def nameSpace = "default"

        stage('Clone repository') {
            container('git') {
                checkout scm
            }
        }
	
        stage('Build a gradle project'){
            container('gradle'){
                sh 'chmod 755 gradlew'
                sh './gradlew clean build'
            }
        }

        stage('Build docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "${dockerRegistryCredential}", url: "http://${dockerRegistry}" ]) {
                    sh "docker build -t ${dockerImageName}:${dockerImageTags} -f ./Dockerfile ."
                }
            }
        }

        stage('Push docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "${dockerRegistryCredential}", url: "http://${dockerRegistry}" ]) {
                    docker.image("${dockerImageName}:${dockerImageTags}").push()
                }
            }
        }
		
        stage('Run kubectl') {
            container('kubectl') {
                sh "kubectl apply -f spring-boot-api-03-app-deployment.yaml -n ${nameSpace}"
                sh "kubectl apply -f spring-boot-api-03-app-service.yaml -n ${nameSpace}"
                sh "kubectl apply -f spring-boot-api-03-app-ingress.yaml -n ${nameSpace}"
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 510312b03be0e08eeb0ed281c1aedbf283711462
