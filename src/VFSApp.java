import java.util.Scanner;

public class VFSApp {
    private String vfsName;
    private boolean isRunning;
    private Scanner scanner;

    public VFSApp(String vfsName) {
        this.vfsName = vfsName;
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcome();

        while (isRunning) {
            showPrompt();
            String input = readInput();

            if (!input.trim().isEmpty()) {
                processInput(input);
            }
        }

        cleanup();
    }

    private void printWelcome() {
        System.out.println("=== Virtual File System (VFS) ===");
        System.out.println("Type 'help' for available commands");
        System.out.println("Type 'exit' to quit");
        System.out.println("==================================");
    }

    private void showPrompt() {
        System.out.print(vfsName + "> ");
    }

    private String readInput() {
        return scanner.nextLine().trim();
    }

    private void processInput(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();
        String[] args = new String[parts.length - 1];

        if (args.length > 0) {
            System.arraycopy(parts, 1, args, 0, args.length);
        }

        executeCommand(command, args);
    }

    private void executeCommand(String command, String[] args) {
        switch (command) {
            case "ls":
                cmdLS(args);
                break;
            case "cd":
                cmdCD(args);
                break;
            case "exit":
                cmdExit(args);
                break;
            case "help":
                cmdHelp(args);
                break;
            default:
                System.out.println("Error: Unknown command '" + command + "'");
                System.out.println("Type 'help' for available commands");
                break;
        }
    }

    private void cmdLS(String[] args) {
        System.out.println("[LS] Command executed");
        System.out.println("Arguments: " + (args.length > 0 ? String.join(", ", args) : "none"));
        System.out.println("(This would list directory contents)");
    }

    private void cmdCD(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: CD requires a path argument");
            return;
        }
        if (args.length > 1) {
            System.out.println("Error: CD takes only one argument");
            return;
        }

        System.out.println("[CD] Changing directory to: " + args[0]);
        System.out.println("(This would change current directory)");
    }

    private void cmdExit(String[] args) {
        System.out.println("Shutting down VFS...");
        isRunning = false;
    }

    private void cmdHelp(String[] args) {
        System.out.println("Available commands:");
        System.out.println("  ls [path]    - List directory contents");
        System.out.println("  cd <path>    - Change directory");
        System.out.println("  exit         - Exit the application");
        System.out.println("  help         - Show this help");
    }

    private void cleanup() {
        scanner.close();
        System.out.println("VFS application terminated.");
    }

    public static void main(String[] args) {
        // Устанавливаем имя VFS (можно передать как аргумент)
        String vfsName = "myvfs";
        if (args.length > 0) {
            vfsName = args[0];
        }

        VFSApp app = new VFSApp(vfsName);
        app.start();
    }
}