import java.util.ArrayList;
import java.util.Arrays;

class Book {
    ArrayList<String> pages;
    int pageNumber;
    
    Book(String[] pages) {
        this.pages = new ArrayList<>(Arrays.asList(pages));
        pageNumber = 0;
    }

    String nextPage() {
        if (pages.size() - 1 == pageNumber) return pages.get(pageNumber);
        return pages.get(++pageNumber);
    }

    String prevPage() {
        if (pageNumber == 0) return pages.get(pageNumber);
        return pages.get(--pageNumber);
    }

    String gotoPage(int n) {
        if (n < 0 || n >= pages.size()) return pages.get(pageNumber);
        pageNumber = n - 1;
        return pages.get(pageNumber);
    }
}
