import http from "../http-commons";

class ItemDataService {
    addBook(data) {
        return http.post("/api/addBook", data);
    }
}
export default new ItemDataService();