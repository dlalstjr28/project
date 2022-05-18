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
### 따라서 해당 문제점을 해결하기 위해 다른 리전에서 실행도록 함.
