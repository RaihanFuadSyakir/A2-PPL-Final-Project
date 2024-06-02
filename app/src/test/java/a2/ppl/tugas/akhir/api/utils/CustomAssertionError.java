package a2.ppl.tugas.akhir.api.utils;

import java.util.List;

public class CustomAssertionError extends AssertionError {
    private StringBuilder messageBuilder;

    public CustomAssertionError() {
        this.messageBuilder = new StringBuilder();
    }

    public CustomAssertionError(String message) {
        super(message);
        this.messageBuilder = new StringBuilder(message);
    }

    // Method to append messages
    public CustomAssertionError appendMessage(String message) {
        messageBuilder.append(message);
        messageBuilder.append("\n");
        return this;
    }

    // Method to append messages from a list
    public CustomAssertionError appendMessages(List<String> messages) {
        for (String message : messages) {
            messageBuilder.append(message);
            messageBuilder.append("\n");
        }
        return this;
    }

    // Override getMessage() to return the accumulated message
    @Override
    public String getMessage() {
        return messageBuilder.toString();
    }
}