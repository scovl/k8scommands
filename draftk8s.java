import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public static int main(String[] args) {
        // create namespace, clusterName, accountId,region, roleName variables
        String namespace = "chaos-mesh";
        String clusterName = "test-cluster";
        String accountId = "test-account";
        String region = "sa-east-1";
        String roleName = "test-role";

        // execute the command line: assume-role --role-arn arn:aws:iam:accountId:role/roleName --role-session-name eks-kubectl-session-name


        String[] ekscmd = new String[]{
                "aws sts assume-role --role-arn arn:aws:iam:" + accountId + ":role/" + roleName + " --role-session-name eks-kubectl-session-name",
                "aws sts get-caller-identity",
                "aws eks --region" + region + "update-kubeconfig --name " + clusterName
        };

        // Runtime exec three commands in ekscmd
        for (String cmd : ekscmd) {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }


        String get_namespace_cmd = "kubectl get -n " + namespace + " -o json | jq.status.phase -r";

        // runtime exec get_namespace_cmd
        try {
            Process process = Runtime.getRuntime().exec(get_namespace_cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // if get_namespace_cmd return "Active", then return get_namespace_cmd
            if (reader.readLine().equals("Active")) {
                // print reader.readLine()
                System.out.println(reader.readLine());
                return 1;
            } else {
                System.out.println(reader.readLine());
                return 0;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
