variable "aws_region" {
  default = "sa-east-1"
}

provider "aws" {
  region = "${var.aws_region}"
}

data "archive_file" "lambda_zip" {
  type = "zip"
  source_file = "lambda_function.py"
  output_path = "lambda_function.zip"
}
data "aws_iam_role" "lambda-role" {
  name = "lambda-role"
}

resource "aws_lambda_function" "test_lambda" {
  filename = "lambda_function.zip"
  function_name = "lambda_handler"
  role = "${data.aws_iam_role.lambda-role.arn}"
  handler = "lambda_function.lambda_handler"
  source_code_hash = "${data.archive_file.lambda_zip.output_base64sha256}"
  runtime = "java11"
  timeout = 300
 
    vpc_config {
      subnet_ids = ["", ""]
      security_group_ids = [""]
    }
}