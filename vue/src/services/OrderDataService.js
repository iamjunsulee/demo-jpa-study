import http from "../http-commons";

class OrderDataService {
    createOrder(data) {
        return http.post("/api/orders", data);
    }
}
export default new OrderDataService();