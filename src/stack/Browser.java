package stack;

/**
 * Demonstrates the usability of stacks in a browser (navigation)
 */
public class Browser {

    private String currentPage;

    private final MyStack<String> previousPages;
    private final MyStack<String> nextPages;

    public Browser() {
        this.previousPages = new MyStack<>();
        this.nextPages = new MyStack<>();
    }

    public void goToPage(String url) {
        if (this.currentPage != null)
            previousPages.push(currentPage);
        this.currentPage = url;
        this.nextPages.clear();
    }

    public void goForward() {
        if (this.currentPage == null || nextPages.isEmpty())
            return;
        previousPages.push(this.currentPage);
        this.currentPage = nextPages.pop();
    }

    public void goBackward() {
        if (this.currentPage == null || previousPages.isEmpty())
            return;
        nextPages.push(this.currentPage);
        this.currentPage = previousPages.pop();
    }

    public String getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public String toString() {
        return this.currentPage;
    }
}
