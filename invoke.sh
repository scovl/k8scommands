# This script directly executes the function on AWS cloud
# and has no bearing with the code here (except until it is deployed)

aws lambda invoke \
--invocation-type RequestResponse \
--function-name lambdaHandler \
--region sa-east-1 
