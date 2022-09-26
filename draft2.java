import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class K8sconnect {
   // exec "echo hello world" and ls -a using ProcessBuilder
    public static void main(String[] args) {
        String[] cmd = new String[]{
                "echo hello world",
                "ls -a",
                "ls -l"
        };

        String cmd2 = "echo carai mermao";

        for (String command : cmd) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", command);
                Process process = processBuilder.start();
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

        // exec cmd2
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", cmd2);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("mermao")) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            }
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}