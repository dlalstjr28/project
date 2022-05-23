## 쿠버네티스 기본적인 설치 방법
### kubectl : https://subicura.com/k8s/prepare/kubectl-setup.html#%E1%84%89%E1%85%A5%E1%86%AF%E1%84%8E%E1%85%B5%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5
### minikube https://subicura.com/k8s/prepare/kubernetes-setup.html#minikube
### 단 , 주의할 점은 windows 환경에서 사용할 경우 가상화 환경이 구축되어 있어야 함. (나는 Docker Desktop을 이용하기로 해서 상관 X)

    C:\Users\dlals>minikube start
    * Microsoft Windows 10 Pro 10.0.19043 Build 19043 의 minikube v1.25.1
    * 기존 프로필에 기반하여 docker 드라이버를 사용하는 중
    * minikube 클러스터의 minikube 컨트롤 플레인 노드를 시작하는 중
    * 베이스 이미지를 다운받는 중 ...
    * Restarting existing docker container for "minikube" ...
    * 쿠버네티스 v1.23.1 을 Docker 20.10.12 런타임으로 설치하는 중
      - kubelet.housekeeping-interval=5m
      - 인증서 및 키를 생성하는 중 ...
      - 컨트롤 플레인이 부팅...
      - RBAC 규칙을 구성하는 중 ...
    * Kubernetes 구성 요소를 확인...
      - Using image gcr.io/k8s-minikube/storage-provisioner:v5
      - Using image kubernetesui/dashboard:v2.3.1
      - Using image kubernetesui/metrics-scraper:v1.0.7
    * 애드온 활성화 : storage-provisioner, dashboard, default-storageclass

    ! C:\Program Files\Docker\Docker\resources\bin\kubectl.exe is version 1.19.7, which may have incompatibilites with Kubernetes 1.23.1.
      - Want kubectl v1.23.1? Try 'minikube kubectl -- get pods -A'
    * 끝났습니다! kubectl이 "minikube" 클러스터와 "default" 네임스페이스를 기본적으로 사용하도록 구성되었습니다.

    C:\Users\dlals>minikube ip
    192.168.49.2
    C:\Users\dlals>docker ps
    CONTAINER ID   IMAGE                                 COMMAND                  CREATED        STATUS         PORTS                                                                                                                                  NAMES
    6baf8b978c29   gcr.io/k8s-minikube/kicbase:v0.0.29   "/usr/local/bin/entr…"   3 months ago   Up 4 minutes   127.0.0.1:56493->22/tcp, 127.0.0.1:56494->2376/tcp, 127.0.0.1:56496->5000/tcp, 127.0.0.1:56497->8443/tcp, 127.0.0.1:56495->32443/tcp   minikube


## wordpress.yml 접속하기 위해선 minikube 설치 후 minikube service 로 접속 (그 전에 kubectl get all 로 서비스명 확인)

![사진1](https://subicura.com/k8s/build/imgs/guide/index/wordpress.webp)

    kubectl apply -f https://subicura.com/k8s/code/guide/index/wordpress-k8s.yml
    kubectl delete wordpress-k8s.yml
