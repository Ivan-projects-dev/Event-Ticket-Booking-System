package controller;

/**
 * Central exception handler for the presentation layer.
 * Wraps exceptions into user-friendly messages so controllers
 * don't need to repeat try/catch logic.
 */
public class GlobalExceptionHandler {

    /**
     * Handles any exception and returns a readable error message string.
     */
    public String handle(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return "Bad request: " + e.getMessage();
        }

        if (e instanceof IllegalStateException) {
            return "Operation not allowed: " + e.getMessage();
        }

        // Log unexpected errors
        System.err.println("Unexpected error: " + e.getMessage());
        e.printStackTrace();

        return "Internal error. Please try again later.";
    }

    /**
     * Convenience method: runs a Runnable and returns null on success,
     * or an error message string on failure.
     */
    public String tryRun(Runnable action) {
        try {
            action.run();
            return null;
        } catch (Exception e) {
            return handle(e);
        }
    }
}
