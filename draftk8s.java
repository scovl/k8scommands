import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {
    public static int main(String[] args) {
        // create namespace, clusterName, accountId,region, roleName variables
        String namespace = "chaos-mesh";
        String clusterName = "test-cluster";
        String accountId = "test-account";
        String region = "sa-east-1";
        String roleName = "test-role";

        // execute the command line: aws sts assume-role --role-arn arn:aws:iam:accountId:role/roleName --role-session-name eks-kubectl-session-name
        String assume_role_cmd = "aws sts assume-role --role-arn arn:aws:iam:accountId:role/roleName --role-session-name eks-kubectl-session-name";
        Runtime run = Runtime.getRuntime();
        Process p = run.exec(assume_role_cmd);
        p.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream));
        


        // execute the command line: aws sts get-caller-identity
        String get_caller_identity_cmd = "aws sts get-caller-identity";

        // execute the command line: aws eks --region region update-kubeconfig --name clusterName
        String update_kubeconfig_cmd = "aws eks --region region update-kubeconfig --name " + clusterName;

        // execute the command line: kubectl get -n namespace -o json | jq .status.phase -r
        String get_namespace_cmd = "kubectl get -n " + namespace + " -o json | jq.status.phase -r";



        // if get_namespace_cmd == "Active", then return 1
        if (get_namespace_cmd == "Active") {
            return 1;
        } else {
            return 0;
            }



    }



}
