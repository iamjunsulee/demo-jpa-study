import http from "../http-commons";

class ItemDataService {
    addBook(data) {
        return http.post("/api/addBook", data);
    }

    findAllItems() {
        return http.get("/api/books");
    }
}
export default new ItemDataService();