package gpb.command.handler;


import gpb.command.command.Command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
