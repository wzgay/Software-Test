#!groovy
pipeline {
    agent any
    environment {
        GITURL = 'https://gitee.com/linge365/springboot-demo.git'
        SERVER = 'test'
        REMOTEDIR = '/test'
        SOURCE_FILE = 'target/springboot-demo-0.1.jar'
        PREFIX = 'target'
        COMPLETE_EXEC_SHELL = '/etc/init.d/springboot-demo.sh restart'
    }
    tools {
        maven "mvn"
        jdk 'jdk8'
    }
    stages {
//        stage('获取代码') {
//            steps {
//                // 拉取代码
//                git branch: 'master',
//                        //这个credentialsId在系统管理--credentials中获取（根据实际填写）。如果git仓库开源无密码。这行注释
//                        //credentialsId: 'd9866d18-d6df-4ffb-b3c0-b3e384157826',
//                        url: env.GITURL
//            }
//        }
        stage('打印环境变量') {
            steps {
                sh 'printenv'
            }
        }
        stage('编译代码') {
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('应用分发') {
            steps {
                sshPublisher(
                        publishers: [
                                sshPublisherDesc(
                                        configName: env.SERVER,
                                        transfers: [
                                                sshTransfer(
                                                        execCommand: env.COMPLETE_EXEC_SHELL,
                                                        execTimeout: 120000,
                                                        flatten: false,
                                                        remoteDirectory: env.REMOTEDIR,
                                                        removePrefix: env.PREFIX,
                                                        sourceFiles: env.SOURCE_FILE
                                                )
                                        ]
                                )
                        ]
                )
            }
        }
    }
}
