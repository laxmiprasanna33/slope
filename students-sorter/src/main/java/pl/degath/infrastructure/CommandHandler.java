package pl.degath.infrastructure;

public interface CommandHandler<T extends Command, R> {

    R handle(T command);
}