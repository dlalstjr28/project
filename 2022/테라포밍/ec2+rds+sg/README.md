# web_infra.tf 를 테라포밍 했을 경우 문제점 발생
## sg 과 rds 설정 시 VPC 에 대한 고려가 안되어 있다 오류 발생
    aws_security_group.ssh: Creating...
    aws_db_instance.web_db: Creating...
    ╷
    │ Error: error creating Security Group (allow_ssh_from_all): VPCIdNotSpecified: No default VPC for this user
    │       status code: 400, request id: d71d9c22-f0ae-4d34-9f11-b13389c03927
    │
    │   with aws_security_group.ssh,
    │   on web_infra.tf line 6, in resource "aws_security_group" "ssh":
    │    6: resource "aws_security_group" "ssh" {
    │
    ╵
    ╷
    │ Error: Error creating DB Instance: InvalidSubnet: No default subnet detected in VPC. Please contact AWS Support to recreate default Subnets.
    │       status code: 400, request id: af3596a0-24fa-4c24-add9-db1661080be7
    │
    │   with aws_db_instance.web_db,
    │   on web_infra.tf line 31, in resource "aws_db_instance" "web_db":
    │   31: resource "aws_db_instance" "web_db" {
    │

### 발생하게 된 이유는 서울 리전에서는 application 이라는 내가 임의로 만든 VPC가 운영중이기 때문.
### 따라서 해당 문제점을 해결하기 위해 다른 리전에서 실행도록 함. (us-east-2 / 오하이오) 
### 현재, web_infra.tf 는 EC2의 AMI 버젼 및 RDS(mysql) 버젼을 지정해줘야 하므로 CSP 에서 제공하는 버젼에 맞추지 않으면 오류 발생함. 

    Do you want to perform these actions?
      Terraform will perform the actions described above.
      Only 'yes' will be accepted to approve.

      Enter a value: yes

    aws_key_pair.web_admin: Creating...
    aws_security_group.ssh: Creating...
    aws_db_instance.web_db: Creating...
    aws_key_pair.web_admin: Creation complete after 1s [id=web_admin]
    aws_security_group.ssh: Creation complete after 4s [id=sg-06d3fb08e38cae840]
    aws_instance.web: Creating...
    aws_db_instance.web_db: Still creating... [10s elapsed]
    aws_instance.web: Still creating... [10s elapsed]
    aws_db_instance.web_db: Still creating... [20s elapsed]
    aws_instance.web: Still creating... [20s elapsed]
    aws_db_instance.web_db: Still creating... [30s elapsed]
    aws_instance.web: Still creating... [30s elapsed]
    aws_instance.web: Creation complete after 33s [id=i-048098fa0d5662a86]
    aws_db_instance.web_db: Still creating... [40s elapsed]
    aws_db_instance.web_db: Still creating... [50s elapsed]
    aws_db_instance.web_db: Still creating... [1m0s elapsed]
    aws_db_instance.web_db: Still creating... [1m10s elapsed]
    aws_db_instance.web_db: Still creating... [1m20s elapsed]
    aws_db_instance.web_db: Still creating... [1m30s elapsed]
    aws_db_instance.web_db: Still creating... [1m40s elapsed]
    aws_db_instance.web_db: Still creating... [1m50s elapsed]
    aws_db_instance.web_db: Still creating... [2m0s elapsed]
    aws_db_instance.web_db: Still creating... [2m10s elapsed]
    aws_db_instance.web_db: Still creating... [2m20s elapsed]
    aws_db_instance.web_db: Still creating... [2m30s elapsed]
    aws_db_instance.web_db: Still creating... [2m40s elapsed]
    aws_db_instance.web_db: Still creating... [2m50s elapsed]
    aws_db_instance.web_db: Still creating... [3m0s elapsed]
    aws_db_instance.web_db: Still creating... [3m10s elapsed]
    aws_db_instance.web_db: Still creating... [3m20s elapsed]
    aws_db_instance.web_db: Still creating... [3m30s elapsed]
    aws_db_instance.web_db: Still creating... [3m40s elapsed]
    aws_db_instance.web_db: Creation complete after 3m50s [id=terraform-20220518151550267100000001]

    Apply complete! Resources: 4 added, 0 changed, 0 destroyed.

    root@DESKTOP-KP28MS0:/home/dlalstjr28/terraform# terraform console
    > aws_db_instance.web_db.endpoint
    "terraform-20220518151550267100000001.cvk7xkn5oziq.us-east-2.rds.amazonaws.com:3306"
##  테라포밍을 통해 배포 완료 성공 후 destroy 를 통해 제거! 위에 있는 RDS Password 값은 tfstate에 임시로 저장, 원래는 저런 config는 관리를 진행해야함. 또한 서비스 별로 분리시키는 것도 방법중 하나
